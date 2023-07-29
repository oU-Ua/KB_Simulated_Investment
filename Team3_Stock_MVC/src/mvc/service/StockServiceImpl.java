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
    
    private StockServiceImpl() {
        for(int i=0;i<data.length;i++) {
        	list.add(new Stock(Integer.parseInt(data[i][0]),data[i][1],Integer.parseInt(data[i][2]),data[i][3]));
        }
      
    }

	@Override
	public void insert(Stock electronics) throws ElectronicsArrayBoundsException{
			if(list.size()==MAX_SIZE)
				throw new ElectronicsArrayBoundsException("배열의 길이를 벗어나 더이상 등록 할수 없습니다.");
			try {
				searchByModelNo(electronics.getModelNo());
				throw new ElectronicsArrayBoundsException("모델번호 오류로 등록할 수 없어요.");
				
			}catch (SearchNotFoundException e) {
				list.add(electronics);

			}
			

		
	}

	@Override
	public List<Stock> selectAll() {
		
		return list;
	}

	@Override
	public Stock searchByModelNo(int modelNo) throws SearchNotFoundException {
		for(Stock e : list) {
			if(e.getModelNo()==modelNo)
				return e;
		}
		
		throw new SearchNotFoundException(modelNo+"는 없는 모델번호로 검색할수 없습니다.");

	}

	@Override
	public void update(Stock electronics) throws SearchNotFoundException {
		for(Stock e : list) {
			if(e.getModelNo()==electronics.getModelNo()) {
				e.setModelDetail(electronics.getModelDetail());
				return;
			}
		}
		throw new SearchNotFoundException(electronics.getModelNo()+"는 없는 모델번호로 수정할 수 없습니다.");
	}

	@Override
	public void delete(int modelNo) throws SearchNotFoundException {
		// TODO Auto-generated method stub
		for(Stock e : list) {
			if(e.getModelNo()== modelNo) {
				list.remove(list.indexOf(e));
				return;
			}
		}
		throw new SearchNotFoundException(modelNo+"는 없는 모델번호로 삭제할 수 없습니다.");
		
		
	}

	@Override
	public void sort(int i) {
		switch(i) {
		case 1 : Collections.sort(list, new Comparator<Stock>() {

			@Override
			public int compare(Stock o1, Stock o2) {
				return o1.getModelNo()-o2.getModelNo();
			}
			});break;
		case 2 : Collections.sort(list, new Comparator<Stock>() {

			@Override
			public int compare(Stock o1, Stock o2) {
				return o1.getModelName().compareTo(o2.getModelName());
			}
			});break;
		case 3 : Collections.sort(list, new Comparator<Stock>() {

			@Override
			public int compare(Stock o1, Stock o2) {
				return o1.getModelPrice()-o2.getModelPrice();
			}
			});break;
		}
		
		
		
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}
    
} // 클래스 끝 