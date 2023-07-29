package mvc.service;

import java.util.ArrayList;
import java.util.List;

import mvc.dto.Stock;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;

public interface StockService {

    /**
     * 전자제품 등록
     *
     * @param electronics
     * 
     * : 최대 List에 저장된 객체의 개수가 MAX-SIZE를 벗어나면 
     *    ElectronicsArrayBoundsException 예외발생
     *      - 예외메시지 : 배열의 길이를 벗어나 더이상 등록 할수 없습니다.
     * @throws ElectronicsArrayBoundsException 
     * @throws SearchNotFoundException 
     * 
     */
    public void insert(Stock electronics) throws ElectronicsArrayBoundsException ;

    /**
     * 등록된 전체 전자제품 검색 
     * @return
     */
    public List<Stock> selectAll() ;
    
    /**
     * 모델번호에 해당하는 전자제품 검색 
     * @param modelNo
     * @return
     *   : 만약 찾는 정보가 없으면 
     *      SearchNotFoundException 예외발생
     *       -예외메시지 : modelNo+"는 없는 모델번호로 검색할수 없습니다."
     */
    public Stock searchByModelNo(int modelNo)throws SearchNotFoundException ;


    /**
     * 모델번호에 해당하는 전자제품 수정하기 
     * (설명만 수정한다)
     * @param electronics
     *  : 수정전에 모델번호에 해당하는 정보가 있는지를 찾고 없으면
     *     SearchNotFoundException 예외발생
     *     있으면 전자제품 모델번호에 해당하는 설명을 수정한다.
     */
    public void update(Stock electronics) throws SearchNotFoundException;
    
    
    /**
     * 모델번호에 해당하는 전자제품 삭제하기
     * 
     *
     * @param 모델번호
     *  :삭제전에 모델번호에 해당하는 정보가 있는지를 찾고 없으면
     *     SearchNotFoundException 예외발생
     *     있으면 전자제품 모델번호에 해당하는 정보를 삭제한다.
     */
    public void delete(int modelNo) throws SearchNotFoundException;
    
    
    /**
     * 입력받은 정렬기준으로 정렬하기
     * @param 정렬기준 번호
     * : 1 : 모델번호 2: 모델이름 3: 모델 가격 입력받은 숫자에 따라 정렬한다. 
     */
    public void sort(int i);
   
    /**
     * 지금까지 변화 출력
     */
    public void log();

}



