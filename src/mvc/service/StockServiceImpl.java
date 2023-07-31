package mvc.service;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.BuyingBalanceException;
import mvc.exception.SellingAmountException;


/**
 * 전자제품에 관련된 기능을 담당할 클래스
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
    private static final int MAX_SIZE=10;
    List<Stock> list = new ArrayList<Stock>();
    List<UserStock> user = new ArrayList<UserStock>();
    
    
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
		
		return user;
	}
	@Override
	public void stockBuy(Stock stock) throws BuyingBalanceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stockSell(Stock stock) throws SellingAmountException {
		// TODO Auto-generated method stub
		
	}



    
} // 클래스 끝 