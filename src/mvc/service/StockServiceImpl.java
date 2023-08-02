package mvc.service;

import java.util.ArrayList;
import java.util.List;

import mvc.controller.StockController;
import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.BuyingBalanceException;
import mvc.exception.SearchNotFoundException;
import mvc.exception.SellingAmountException;


/**
 * 주식에 관련된 기능을 담당할 클래스
 */

public class StockServiceImpl implements StockService {
	
	String [][] data = new String [][]{
		{"100","삼성","35000","5000000"},
		{"200","lg","55000","5000"},
		{"300","apple","5500000","4000"},
		{"400","테슬라","800000","2000"},
		{"500","주식","9000","400"}
	}; // 최초의 초기치 데이터를 준비!! electronics에 저장 
	
	
	private static StockService instance = new StockServiceImpl(); 
    private static final int MAX_SIZE=10; // 매수할 수 있는 주식의 한계를 정할까요 ?
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
    
    private StockServiceImpl() {
        for(int i=0;i<data.length;i++) {
        	list.add(new Stock(Integer.parseInt(data[i][0]),data[i][1],Integer.parseInt(data[i][2]),Integer.parseInt(data[i][3])));
        }
      
    }
	@Override
	public List<Stock> stockAll() {
		
		return list;
	}
	@Override
	public List<UserStock> stockUserAll() {
		
		return userlist;
	}
	
	/**
	 * 입력한 종목을 매수
	 */
	@Override
	public void stockBuy(Stock stock, int balance) throws BuyingBalanceException, SearchNotFoundException {
		//1.예외처리 : 매수하려는 양이 현재 잔고보다 많을때 
		Stock buyStock = searchBystockName(stock.getStockName()); // 구매하려는 주식의 가격을 알기 위해서 주식list에서 주식 찾기
		
		if(buyStock.getPrice()*stock.getAmount() > balance) {
			System.out.println(buyStock.getPrice()*stock.getAmount());
			System.out.println(balance);
			throw new BuyingBalanceException();}

		//2.이미 userstock에 있는경우 : amount값만 증가.
		for(UserStock us : userlist) {
			if(us.getStockSeq()==stock.getStockSeq()) {
				us.setAvgprice((us.getAvgprice()*us.getAmountBuy()+buyStock.getPrice()*stock.getAmount()) / (us.getAmountBuy()+stock.getAmount()));
				us.setAmountBuy(us.getAmountBuy()+stock.getAmount());
				StockController.balance = balance - stock.getPrice()*stock.getAmount();
				return;
			}

		}
		//3. userstock에 없는 경우 : userstock에 새로 추가 
		userlist.add(new UserStock(buyStock.getStockSeq(), buyStock.getStockName(), stock.getAmount(),stock.getPrice()));
		balance = balance -buyStock.getPrice()*stock.getAmount();	
		
	}
	
	/**
	 * 입력한 종목이 있는 종목인지 확인
	 * @param stock_seq
	 * @return
	 * @throws SearchNotFoundException
	 */
	public Stock searchBystockName(String stock_name) throws SearchNotFoundException {
		for(Stock st : list) {
			if(st.getStockName().equals(stock_name))
				return st;
		}
		
		throw new SearchNotFoundException(stock_name+"는 없는 종목입니다.");

	}
	
	/**
	 * 입력한 종목이 있는 종목인지 확인
	 * @param stock_seq
	 * @return
	 * @throws SearchNotFoundException
	 */
	public UserStock searchByUserstockName(String stock_name) throws SearchNotFoundException {
		for(UserStock us : userlist) {
			if(us.getStockName().equals(stock_name))
				return us;
		}
		
		throw new SearchNotFoundException(stock_name+"는 매수하지 않은 종목입니다.");

	}

	@Override
	public void stockSell(Stock stock, int balance) throws SellingAmountException, SearchNotFoundException {
			UserStock sellStock = searchByUserstockName(stock.getStockName());
		//1.예외처리 
		if(stock.getAmount() > sellStock.getAmountBuy())
			throw new SellingAmountException();
		
		//2.매도하려는 주식량 <갖고있는 주식량 : amount값만 감소.
		if(stock.getAmount()< sellStock.getAmountBuy()) 
				sellStock.setAmountBuy(sellStock.getAmountBuy()-stock.getAmount());
		
		//3. 매도하려는 주식량 = 갖고있는 주식량 : user에서 삭제
		else if(stock.getAmount() == sellStock.getAmountBuy())
			userlist.remove(userlist.indexOf(sellStock));
		Stock select = searchBystockName(stock.getStockName());
		StockController.balance = balance + select.getPrice()*stock.getAmount();		
		
		
	}

//	@Override
//	public Stock stockDetail(String stockName) throws SearchNotFoundException {
//		Stock select = new Stock();
//		select = searchBystockName(stockName);
//		return select;
//		// TODO Auto-generated method stub
//		
//	}



    
} // 클래스 끝 