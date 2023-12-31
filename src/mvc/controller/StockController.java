package mvc.controller;

import java.io.IOException;
import java.util.List;

import mvc.dto.Headline;
import mvc.dto.Stock;
import mvc.exception.BuyingBalanceException;
import mvc.exception.DMLException;
import mvc.exception.SearchNotFoundException;
import mvc.exception.SellingAmountException;
import mvc.service.StockService;
import mvc.service.StockServiceImpl;
import mvc.view.BuySellView;
import mvc.view.FailView;
import mvc.view.MenuView;
import mvc.view.SuccessView;


/**
 * View와 Model 사이에서 중간 역할 
 *  : 사용자의 요청을 받아서 그에 해당하는 서비스를 호출하고
 *    호출한 결과를 받아서 결과값에 따라 결과 뷰를 호출해준다.
 */

public class StockController {
    private StockService service = StockServiceImpl.getInstance();
    

    /**
     * 종목 조회 - 전체 주식 목록 출력 
     */
    public void stockAll() {
		try {
			List<Stock> stockList = service.stockAll();
			SuccessView.printAll(stockList);
		} catch (SearchNotFoundException e) {
			FailView.errorMessage(e.getMessage());	}
	}
    
    /**
     * 보유 주식 조회
     * @param balance
     */
	public void stockUser(int balance) {
		try {
			SuccessView.printUser(service.stockUserAll(),balance);

		}catch (SearchNotFoundException e){
			FailView.errorMessage(e.getMessage());
		}
	}
 
    /**
     * 주식 매수하기 
     * @param stock
     * @throws IOException 
     * @throws SellingAmountException 
     */
    public void buy(String stockName, int amountBuy, int balance) throws IOException {
    	try {
    		balance = service.stockBuy(stockName,amountBuy, balance);
    		SuccessView.printMessage("매수하였습니다.");
    		MenuView mv = new MenuView(balance);
    		mv.printMenu();
    	}catch( BuyingBalanceException e) {
        	FailView.errorMessage(e.getMessage());
        	new BuySellView(stockName, balance);
        } catch (SearchNotFoundException e) {
			// TODO Auto-generated catch block
        	FailView.errorMessage(e.getMessage());
        	detail(stockName,balance);
		}
    	
    }
    
    /**
     * 주식 매도하기 
     * @param stock
     * @throws IOException 
     * @throws SearchNotFoundException 
     * @throws SellingAmountException 
     */
    public void sell(String stockName, int amountSell, int balance) throws IOException  {
    	try {
    		balance = service.stockSell(stockName, amountSell, balance);
    		SuccessView.printMessage("매도하였습니다.");
    		MenuView mv = new MenuView(balance);
    		mv.printMenu();
    	}catch(SearchNotFoundException e) {
    		FailView.errorMessage(e.getMessage());
    		new BuySellView(stockName,balance);
    	}catch(SellingAmountException e) {
        	FailView.errorMessage(e.getMessage());
        	new BuySellView(stockName,balance);
        }
    	
    }
    
    /**
     * 장 종료하기
     */
	public void finMarket() {
		SuccessView.printMessage("오늘의 장이 종료되었습니다.");
		//장이 종료되었을 때 그냥 종료만 띄울것인지 오늘 하루 log도 띄울지
		MenuView.today++;
		MenuView.statement = true;
		try {
			service.updatePrice(MenuView.today);
		} catch (SearchNotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 뉴스 헤드라인 노출
	 * @param day
	 */
	public void headline(int day) {
		try{
			List<Headline> list = service.headLine(MenuView.today);
			SuccessView.printHeadline(list);
		}catch (SearchNotFoundException e){
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 주식 상세정보 출력
	 * @param stockName
	 * @param balance
	 * @throws IOException
	 */
	public void detail(String stockName, int balance) throws IOException {
		try {
			Stock stock = service.searchBystockName(stockName);
			SuccessView.printDetail(stock);
			new BuySellView(stockName,balance);
		}catch(SearchNotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 프로그램 종료
	 * @param balance
	 * @param seedmoney
	 * @throws IOException
	 */
	public void fin(int balance, int seedmoney) throws IOException {
		SuccessView.printFin(balance, seedmoney);
		MenuView.today=1;
		try {
			service.updatePrice(MenuView.today);
			service.finish();
		} catch (DMLException | SearchNotFoundException e ) {
		}
		
		
	}
    
}











