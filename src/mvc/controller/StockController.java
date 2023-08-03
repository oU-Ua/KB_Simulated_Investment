package mvc.controller;

import mvc.dto.Stock;
import mvc.exception.BuyingBalanceException;
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
	public static int seedmoney;
	public static int balance = seedmoney;

    /**
     * 종목 조회 - 전체 주식 목록 출력 
     */
    public void stockAll() {
		service.stockAll();
		SuccessView.printAll(service.stockAll());
    }
	public void stockUser() {
		service.stockUserAll();
		if(service.stockUserAll().isEmpty())
			FailView.errorMessage("매수한 주식이 없습니다.");  // exception처리를 하는게 좋을까요?
		else
		SuccessView.printUser(service.stockUserAll(),balance);
		
	}
 
     

    /**
     * 주식 매수하기 
     * @param stock
     * @throws SellingAmountException 
     */
    public void buy(Stock stock) {
    	try {
    		System.out.println(balance);
    		service.stockBuy(stock, balance);
    		SuccessView.printMessage("매수하였습니다.");
    	}catch( BuyingBalanceException e) {
        	FailView.errorMessage(e.getMessage());
        	new BuySellView(stock.getStockName());
        } catch (SearchNotFoundException e) {
			// TODO Auto-generated catch block
        	FailView.errorMessage(e.getMessage());
        	detail(stock.getStockName());
		}
    	
    }
    
    /**
     * 주식 매도하기 
     * @param stock
     * @throws SearchNotFoundException 
     * @throws SellingAmountException 
     */
    public void sell(Stock stock)  {
    	try {
    		System.out.println(balance);
    		service.stockSell(stock, balance);
    		SuccessView.printMessage("매도하였습니다.");
    	}catch(SearchNotFoundException e) {
    		FailView.errorMessage(e.getMessage());
    		new BuySellView(stock.getStockName());
    	}catch(SellingAmountException e) {
        	FailView.errorMessage(e.getMessage());
        	new BuySellView(stock.getStockName());
        }
    	
    }
    
    /**
     * 장 종료하기
     */
	public void finMarket() {
		SuccessView.printMessage("오늘의 장이 종료되었습니다.");
		//장이 종료되었을 때 그냥 종료만 띄울것인지 오늘 하루 log도 띄울지
		MenuView.today++;
	}



	public void headline(int day) {
		// TODO Auto-generated method stub
		
	}
	public void detail(String stockName) {
		try {
			Stock stock = service.searchBystockName(stockName);
			SuccessView.printDetail(stock);
			new BuySellView(stockName);
		}catch(SearchNotFoundException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
    
}











