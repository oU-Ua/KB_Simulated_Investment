package mvc.controller;

import java.util.ArrayList;
import java.util.List;

import mvc.dto.Stock;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;
import mvc.service.StockService;
import mvc.service.StockServiceImpl;
import mvc.view.FailView;
import mvc.view.SuccessView;



/**
 * View와 Model 사이에서 중간 역할 
 *  : 사용자의 요청을 받아서 그에 해당하는 서비스를 호출하고
 *    호출한 결과를 받아서 결과값에 따라 결과 뷰를 호출해준다.
 */

public class StockController {
    private StockService service = StockServiceImpl.getInstance();

    /**
     * 주식 전체 출력
     */
    public void stockAll() {
		service.stockAll();
		SuccessView.printAll(service.stockAll());
    }
 

	 /**
     * 유저 등록 
	 * @throws SearchNotFoundException 
	 * @throws ElectronicsArrayBoundsException 
     */
   
    public void user(String userName, int balance) {
    	try {
    		service.user(userName, balance);
    		
    		SuccessView.printMessage("등록되었습니다.");
    	}catch(ElectronicsArrayBoundsException e) {
        	FailView.errorMessage(e.getMessage());
        }
       
    }
    
    

    /**
     * 모델번호에 해당하는 전자제품 검색
     * @param modelNo
     * @throws SearchNotFoundException 
     */
    public void infoSearch(int stockSeq) {
    	try {
    		Stock st = service.infosearch(stockSeq);
    		SuccessView.printSearchByModelNo(st);

    	}catch(SearchNotFoundException e) {
        	FailView.errorMessage(e.getMessage());
        }
    	
    } 

    /**
     * 주식 매수하기 
     * @param stock
     * @throws SearchNotFoundException 
     */
    public void buy(Stock stock) {
    	try {
    		service.stockBuy(stock);
    		SuccessView.printMessage("매수하였습니다.");
    	}catch(SearchNotFoundException e) {
        	FailView.errorMessage(e.getMessage());
        }
    	
    }
    
    /**
     * 주식 매도하기 
     * @param stock
     * @throws SearchNotFoundException 
     */
    public void sell(Stock stock) {
    	try {
    		service.stockSell(stock);
    		SuccessView.printMessage("매도하였습니다.");
    	}catch(SearchNotFoundException e) {
        	FailView.errorMessage(e.getMessage());
        }
    	
    }
    
    /**
     * 장 종료하기
     */
	public void finMarket() {
		SuccessView.printMessage("오늘의 장이 종료되었습니다.");
		
	}
    
}











