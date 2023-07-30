package mvc.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import mvc.dto.Stock;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;


/**
 * 전자제품에 관련된 기능을 담당할 클래스
 */

public class StockServiceImpl implements StockService {
	
	String [][] data = new String [][]{
		{"100","선풍기","35000","삼성 선풍기"},
		{"200","전자렌지","55000","잘 데워져요.."},
		{"300","에어컨","5500000","무풍 에어컨 너무 시원해"},
		{"400","냉장고","800000","LG 냉장고"},
		{"500","드라이기","9000","LG 드라이기"}
	}; // 최초의 초기치 데이터를 준비!! electronics에 저장 
	
	
	private static StockService instance = new StockServiceImpl(); 
    private static final int MAX_SIZE=10;
    List<Stock> list = new ArrayList<Stock>();
    
    /** 
     * 외부에서 객체 생성안됨. 
     * 생성자안에서 위에 2차원 배열의 데이터를 List에 추가하여
     * 초기치 데이터를 만든다.
     * 
     */
    public static StockService getInstance() {
		return instance;
	}
    
//    private StockServiceImpl() {
//        for(int i=0;i<data.length;i++) {
//        	list.add(new Stock(Integer.parseInt(data[i][0]),data[i][1],Integer.parseInt(data[i][2]),data[i][3]));
//        }
//      
//    }

	@Override
	public void user(String userNmae, int balance) throws ElectronicsArrayBoundsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stock> stockAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock infosearch(int stockSeq) throws SearchNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stockBuy(Stock stock) throws SearchNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stockSell(Stock stock) throws SearchNotFoundException {
		// TODO Auto-generated method stub
		
	}

    
} // 클래스 끝 