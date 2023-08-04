package mvc.service;

import java.util.List;

import mvc.dto.Headline;
import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.BuyingBalanceException;
import mvc.exception.SearchNotFoundException;
import mvc.exception.SellingAmountException;

public interface StockService {

	/**
     * 등록된 전체 주식 종목 조회 
     * @return
     */
    public List<Stock> stockAll() throws SearchNotFoundException;
    
    
    /**
     * 등록된 이용자의 주식 종목 조회
     * @return
     */
    public List<UserStock> stockUserAll()throws SearchNotFoundException;


    /**
     *  종목이름에 해당하는 주식이 있는지를 찾고 없으면
     *  SearchNotFoundException 예외발생
     *  잔고 부족하면 BuyingBalanceException 예외발생
     *  있으면 userstock에서 amount update 또는 insert
     */
    public int stockBuy(String stockName, int amountBuy, int balance) throws BuyingBalanceException,SearchNotFoundException;
    
    
    /**
     * 	종목이름에 해당하는 주식이 없으면
     *     SearchNotFoundException 예외발생
     *     있으면 userstock에서 amount update 또는 delete
     */
    public int stockSell(String stockName, int amountSell, int balance) throws SellingAmountException,SearchNotFoundException;

    
    /**
     * 
     * @param stockName
     * @return
     * @throws SearchNotFoundException
     */
	public Stock searchBystockName(String stockName) throws SearchNotFoundException;
	
	/**
	 * 입력한 종목이 있는 종목인지 확인
	 * @param stock_seq
	 * @return
	 * @throws SearchNotFoundException
	 *select * from User_Stock where stock_seq = (select stock_seq from stock where stock_name = ?)
	 */
	public UserStock searchByUserstockName(String stock_name) throws SearchNotFoundException;


	/**
	 * 요일이 바뀔때마다 주가를 변경한다.
	 * update Stock set price = (select d?)
	 */
	public void updatePrice(int today)throws SearchNotFoundException;

	/**
	 * 프로그램 종료 시 userstock 정보 삭제
	 */
	public void finish();
	
	/**
	 * 요일이 바뀔 때마다 헤드라인 노출
	 * select * from headline where day = ?
	 */
	public List<Headline> headLine(int day) throws SearchNotFoundException;
}



