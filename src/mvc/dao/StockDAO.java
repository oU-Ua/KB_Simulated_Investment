package mvc.dao;

import java.util.List;

import mvc.dto.Headline;
import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.DMLException;
import mvc.exception.SearchNotFoundException;
import mvc.exception.SellingAmountException;

public interface StockDAO {

	/**
	 * stock 테이블 전체검색
	 * @return
	 * @throws SearchNotFoundException
	 */
    List<Stock> stockAll() throws SearchNotFoundException;

    /**
     * user_stock 테이블 전체검색
     * @return
     * @throws SearchNotFoundException
     */
    List<UserStock> stockUserAll() throws SearchNotFoundException;

    /**
     * stock_name에 해당하는 price 수정하기
     * @param updatePrice
     * @param stockname
     * @return
     * @throws SearchNotFoundException
     */
	int updateAvgPrice(int updatePrice, String stockname) throws SearchNotFoundException;

	/**
	 * stock_name에 해당하는 Amount_Buy 수정
	 * @param updateAmount
	 * @param us
	 * @return
	 * @throws SearchNotFoundException
	 */
	int updateAmountBuy(int updateAmount, UserStock us) throws SearchNotFoundException;

	/**
	 * stock_name에 해당하는 종목 insert
	 * @param buyStock
	 * @param amountBuy
	 * @return
	 * @throws SearchNotFoundException
	 */
	int insertUserstock(Stock buyStock, int amountBuy) throws SearchNotFoundException;
	
	/**
	 * stock 테이블에서 stock_name에 해당하는 레코드 검색
	 * @param stock_name
	 * @return
	 * @throws SearchNotFoundException
	 */
	Stock searchBystockName(String stock_name) throws SearchNotFoundException;
	
	/**
	 * user_stock 테이블에서 stock_name에 해당하는 레코드 검색
	 * @param stock_name
	 * @return
	 * @throws SearchNotFoundException
	 */
	UserStock searchByUserstockName(String stock_name) throws SearchNotFoundException;
	
	/**
	 * stock_name에 해당하는 amount_buy 값 수정
	 * @param amountSell
	 * @param stockName
	 * @return
	 * @throws SellingAmountException
	 */
	int sellStockMinusAmount(int amountSell, String stockName) throws SellingAmountException;
	
	/**
	 * stock_name에 해당하는 레코드 삭제
	 * @param stockName
	 * @return
	 * @throws SellingAmountException
	 */
	int sellStockDeleteUser(String stockName) throws SellingAmountException;
	
	/**
	 * day에 해당하는 price 값 수정
	 * @param today
	 * @return
	 * @throws SearchNotFoundException
	 */
	int updatePrice(int today) throws SearchNotFoundException;

	/**
	 * user_stock 테이블 값 전체 삭제
	 * @return
	 * @throws DMLException
	 */
	int deleteAll() throws DMLException;
	
	/**
	 * day에 해당하는 레코드 조회
	 * @param day
	 * @return
	 * @throws SearchNotFoundException
	 */
	List<Headline> getHeadline(int day) throws SearchNotFoundException;
	
}
