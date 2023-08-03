package mvc.service;

import java.util.List;

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
    public List<Stock> stockAll() ;
    
    
    /**
     * 등록된 이용자의 주식 종목 조회 
     * @return
     */
    public List<UserStock> stockUserAll();


    /**
     * 모델번호에 해당하는 전자제품 수정하기 
     * (설명만 수정한다)
     * @param electronics
     *  : 수정전에 모델번호에 해당하는 정보가 있는지를 찾고 없으면
     *     SearchNotFoundException 예외발생
     *     있으면 전자제품 모델번호에 해당하는 설명을 수정한다.
     */
    public int stockBuy(String stockName, int amountBuy, int balance) throws BuyingBalanceException,SearchNotFoundException;
    
    
    /**
     * 모델번호에 해당하는 전자제품 삭제하기
     * 
     *
     * @param 모델번호
     *  :삭제전에 모델번호에 해당하는 정보가 있는지를 찾고 없으면
     *     SearchNotFoundException 예외발생
     *     있으면 전자제품 모델번호에 해당하는 정보를 삭제한다.
     */
    public int stockSell(String stockName, int amountSell, int balance) throws SellingAmountException,SearchNotFoundException;


//	public Stock stockDetail(String stockName) throws SearchNotFoundException;


	public Stock searchBystockName(String stockName) throws SearchNotFoundException;
	
	
	public UserStock searchByUserstockName(String stock_name) throws SearchNotFoundException;


	public void updatePrice() throws SearchNotFoundException;


	
    
    

}



