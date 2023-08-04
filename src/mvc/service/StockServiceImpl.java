package mvc.service;

import java.util.ArrayList;
import java.util.List;

import mvc.dao.StockDAO;
import mvc.dao.StockDAOImpl;
import mvc.dto.Headline;
import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.BuyingBalanceException;
import mvc.exception.DMLException;
import mvc.exception.SearchNotFoundException;
import mvc.exception.SellingAmountException;


/**
 * 주식에 관련된 기능을 담당할 클래스
 */

public class StockServiceImpl implements StockService {
    private StockServiceImpl() {
        
    }

	
	private static StockService instance = new StockServiceImpl(); 
	private StockDAO stockDao = StockDAOImpl.getInstance();
    List<Stock> list = new ArrayList<Stock>();
//    List<Stock> list = stockDao.selectAll();
    List<UserStock> userlist = new ArrayList<UserStock>();
//  List<UserStock> user = userStockDao.selectUserAll();    
    
    
    
    
    /** 
     * 외부에서 객체 생성안됨. 
     * 생성자안에서 위에 2차원 배열의 데이터를 List에 추가하여
     * 초기치 데이터를 만든다.
     * 
     */
    public static StockService getInstance() {
		return instance;
	}
    

	/**
	 * 1. 종목조회 클릭시
	 * select * from Stock
	 * @return
	 */
	@Override
	public List<Stock> stockAll() throws SearchNotFoundException {
		list =this.stockDao.stockAll();
		return list;
	}

	/**
	 * 2. 보유주식 조회 클릭시
	 * select * from user_stock join stock using (stock_seq)
	 * @return
	 */
	@Override
	public List<UserStock> stockUserAll() throws SearchNotFoundException {
		userlist =this.stockDao.stockUserAll();

		if(userlist.isEmpty())
			throw new SearchNotFoundException("\n매수한 주식이 없습니다.");
		else return userlist;
	}
	
	/**
	 * 3. 입력한 종목을 매수
	 */
	@Override
	public int stockBuy(String stockName, int amountBuy, int balance) throws BuyingBalanceException, SearchNotFoundException {

		//1.예외처리 : 매수하려는 양이 현재 잔고보다 많을때 
		Stock buyStock = searchBystockName(stockName); // 구매하려는 주식의 가격을 알기 위해서 주식list에서 주식 찾기

		if(buyStock.getPrice()*amountBuy > balance) {

			throw new BuyingBalanceException();}
		//2.이미 userstock에 있는경우 : amount값만 증가.
		//이 부분 dao => price, amount update
		/**
		 * update UserStock set avg_price =? , amount_buy =? where stock_seq =?
		 */

		UserStock us = stockDao.searchByUserstockName(stockName);
		if(us.getStockSeq()!=0) {

			int updatePrice = (us.getAvgprice()*us.getAmountBuy()+buyStock.getPrice()*amountBuy) / (us.getAmountBuy()+amountBuy);

			int result = stockDao.updateAvgPrice(updatePrice, stockName);
			if(result == 0){
				throw new BuyingBalanceException(stockName+"주식의 평균가가 수정되지 않았습니다. ");
			}
			int updateAmount = us.getAmountBuy()+amountBuy;
			result = stockDao.updateAmountBuy(updateAmount, us);
			if(result == 0){
				throw new BuyingBalanceException(stockName+"주식의 매수량이 수정되지 않았습니다. ");
			}
			balance = balance - buyStock.getPrice()*amountBuy;
			return balance;
		}else {
			//3. userstock에 없는 경우 : userstock에 새로 추가
			//이부분 dao => insert UserStock.
			/**
			 * insert into User_Stock values (?,?,?)
			 * stock_seq, amount_buy, avg_price
			 */
			//userlist.add(new UserStock(buyStock.getStockSeq(), buyStock.getStockSeq() ,buyStock.getStockName(), amountBuy , buyStock.getPrice()));
			int result = stockDao.insertUserstock(buyStock, amountBuy);

			if(result == 0){
				throw new BuyingBalanceException(buyStock.getStockName()+"주식의 매수량이 수정되지 않았습니다. ");
			}


		}

		balance = balance -(buyStock.getPrice()*amountBuy);	
		return balance;
	}

	/**
	 * 입력한 종목이 있는 종목인지 확인
	 * @param stock_seq
	 * @return
	 * @throws SearchNotFoundException
	 */

	/**
	 *select * from Stock where stock_name =?
 	 */

	public Stock searchBystockName(String stock_name) throws SearchNotFoundException {
		
		Stock stock = stockDao.searchBystockName(stock_name);
		if(stock == null)
			throw new SearchNotFoundException(stock_name+"은(는) 없는 종목입니다.");
		return stock;

	}
	
	/**
	 * 입력한 종목이 있는 종목인지 확인
	 * @param stock_seq
	 * @return
	 * @throws SearchNotFoundException
	 *select * from User_Stock where stock_seq = (select stock_seq from stock where stock_name = ?)
	 */
	public UserStock searchByUserstockName(String stock_name) throws SearchNotFoundException {
		for(UserStock us : userlist) {
			if(us.getStockName().equals(stock_name))
				return us;
		}
		
		throw new SearchNotFoundException(stock_name+"은(는) 매수하지 않은 종목입니다.");

	}

	@Override
	public int stockSell(String stockName, int amountSell, int balance) throws SellingAmountException, SearchNotFoundException {
			UserStock sellStock = searchByUserstockName(stockName);

		//1.예외처리

		if(amountSell > sellStock.getAmountBuy())
			throw new SellingAmountException("매도를 할만큼 주식을 가지고 있지 않습니다.");


		/**
		 *update User_Stock set amount_buy =? where stock_seq = (select stock_seq from stock where stock_name = ?)
		 */
		//2.매도하려는 주식량 <갖고있는 주식량 : amount값만 감소.
		if(amountSell< sellStock.getAmountBuy()){
			int amount_sell = sellStock.getAmountBuy()-amountSell;
			int res = stockDao.sellStockMinusAmount(amount_sell, stockName);
			if(res==0) throw new SellingAmountException("매도에 실패했습니다.");
		}

		/**
		 *delete from User_Stock where stock_seq = (select stock_seq from stock where stock_name = ?)
		 */

		//3. 매도하려는 주식량 = 갖고있는 주식량 : user에서 삭제
		else if(amountSell == sellStock.getAmountBuy()){
			int res = stockDao.sellStockDeleteUser(stockName);
			if(res==0) throw new SellingAmountException("매도에 실패했습니다.");
		}

		Stock select = searchBystockName(stockName);
		balance = balance + select.getPrice()*amountSell;		
		return balance;
		
	}

	/**
	 * 요일이 바뀔때마다 주가를 변경한다.
	 * update Stock set price = (select d?)
	 */
	@Override
	public void updatePrice(int today) throws SearchNotFoundException {

		int result = stockDao.updatePrice(today);
		if(result == 0) throw new SearchNotFoundException("가격이 바뀐 종목이 없습니다.");
		
	}


	@Override
	public void finish() throws DMLException{
		int result = stockDao.deleteAll();
		if(result == 0) throw new DMLException("가격이 바뀐 종목이 없습니다.");
		
	}

	/**
	 * 요일이 바뀔 때마다 헤드라인 노출
	 * select * from headline where day = ?
	 */
	@Override
	public List<Headline> headLine(int day) throws SearchNotFoundException {
		List<Headline> list = stockDao.getHeadline(day);
		return list;
	}


}

//	@Override
//	public Stock stockDetail(String stockName) throws SearchNotFoundException {
//		Stock select = new Stock();
//		select = searchBystockName(stockName);
//		return select;
//		// TODO Auto-generated method stub
//		
//	}



    
 // 클래스 끝 