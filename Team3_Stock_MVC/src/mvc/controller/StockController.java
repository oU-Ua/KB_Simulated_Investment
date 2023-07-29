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
    public StringBuilder sb = new StringBuilder();

    /**
     * 전체검색
     */
    public void selectAll() {
		service.selectAll();
		SuccessView.printAll(service.selectAll());
		sb.append("전체검색 \n");
		for(Stock elec : service.selectAll()) {
	          sb.append(elec+"\n");
	        }
		sb.append("\n");
    }
 

	 /**
     * 전자제품 등록 
	 * @throws SearchNotFoundException 
	 * @throws ElectronicsArrayBoundsException 
     */
   
    public void insert(Stock electronics) {
    	try {
    		service.insert(electronics);
    		
    		SuccessView.printMessage("등록되었습니다.");
    		sb.append("등록 \n");
    		sb.append(electronics);
    		sb.append("\n");
    		
    	}catch(ElectronicsArrayBoundsException e) {
        	FailView.errorMessage(e.getMessage());
        	sb.append("등록실패 \n");
    		sb.append(e.getMessage());
    		sb.append("\n");
        }
       
    }
    
    

    /**
     * 모델번호에 해당하는 전자제품 검색
     * @param modelNo
     * @throws SearchNotFoundException 
     */
    public void searchByModelNo(int modelNo) {
    	try {
    		Stock ele = service.searchByModelNo(modelNo);
    		SuccessView.printSearchByModelNo(ele);
    		sb.append("등록 \n");
    		sb.append(ele);
    		sb.append("\n");
    	}catch(SearchNotFoundException e) {
        	FailView.errorMessage(e.getMessage());
        	sb.append("검색실패 \n");
    		sb.append(e.getMessage());
    		sb.append("\n");
        }
    	
    } 

    /**
     * 모델번호에 해당하는 전자제품 수정하기 
     * @param electronics
     * @throws SearchNotFoundException 
     */
    public void update(Stock electronics) {
    	try {
    		service.update(electronics);
    		SuccessView.printMessage("수정되었습니다.");
    		sb.append("수정 \n");
    		sb.append(electronics);
    		sb.append("\n");
    	}catch(SearchNotFoundException e) {
        	FailView.errorMessage(e.getMessage());
        	sb.append("수정실패 \n");
    		sb.append(e.getMessage());
    		sb.append("\n");
        }
    	
    }
    
    /**
     * 모델번호에 해당하는 전자제품 삭제하기 
     * @param electronics
     * @throws SearchNotFoundException 
     */
	public void deleteModelNo(int modelNo){
		try {
			service.delete(modelNo);
			Stock ele = service.searchByModelNo(modelNo);
			SuccessView.printMessage("삭제되었습니다.");
			sb.append("삭제되었습니다. \n");
    		sb.append(ele);
    		sb.append("\n");
		}catch(SearchNotFoundException e) {
	      	FailView.errorMessage(e.getMessage());
        	sb.append("삭제실패 \n");
    		sb.append(e.getMessage());
    		sb.append("\n");
	      }
		
	}
	public void sort(int i) {
		service.sort(i);
		SuccessView.printAll(service.selectAll());
		sb.append("정렬 \n");
		for(Stock elec : service.selectAll()) {
	          sb.append(elec+"\n");
	        }
		sb.append("\n");
	}
	public void log() {
		service.log();
		SuccessView.printMessage(sb.toString());
	}
    
}











