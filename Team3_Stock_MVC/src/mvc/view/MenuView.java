package mvc.view;

import java.util.Scanner;

import mvc.controller.StockController;
import mvc.dto.Stock;

/**
   사용자의 요청을 키보드로 입력받는 클래스 
*/
public class MenuView{
    Scanner sc= new Scanner(System.in);
	StockController controller = new StockController(); // 전역변수 초기화, 생성자 호출 

	/**
	  전체 메뉴를 출력하는 메소드
	*/
	public void printMenu(){
        
	     while(true){
           System.out.println("----------------------------------------------------------------------------------");
           System.out.println("1. 전체검색    2. 모델번호검색     3.등록     4. 수정   5. 삭제  6.정렬  7.log확인  9. 종료");
		   System.out.println("----------------------------------------------------------------------------------");
		   System.out.print("메뉴선택 > ");

		   String  menu = sc.nextLine();
		   switch(menu){
               case "1" : 
                 controller.selectAll();
			   break;
			   case "2" : 
                 this.inputSearch();
			   break;
			   case "3" : 
                 this.inputInsert();
			   break;
			   case "4" : 
                 this.inputUpdate();
			   break;
			   case "5" : 
	                 this.inputDelete();
				   break;
			   case "6" : 
	                 this.inputsort();
				   break;
			   case "7" : 
	                 controller.log();
				   break;
			   case "9" : 
                 System.exit(0);
			   default:
				   System.out.println("메뉴를 다시 선택해주세요!!!!");

		   }//switch문끝

		 }//while문끝

	}//메소드끝







	/**
	   등록할때 키보드 입력을 처리하는 메소드
	*/
	public void inputInsert(){
        System.out.print("모델번호는? ");
        int modelNo = Integer.parseInt(sc.nextLine());

		System.out.print("모델이름은? ");
        String modelName = sc.nextLine();

		System.out.print("모델가격은? ");
        int modelPrice = Integer.parseInt(sc.nextLine());

		System.out.print("모델설명은? ");
        String modelDetail = sc.nextLine();


		controller.insert( new Stock(modelNo, modelName, modelPrice, modelDetail) );
 

	}

	/**
	  모델번호에 해당하는 전자제품 검색하기 위해서 모델번호 키보드입력 처리하는 메소드
	*/
    public void inputSearch(){
       System.out.print("찾을 전자제품 모델번호는? ");
        int modelNo = Integer.parseInt(sc.nextLine());
        	controller.searchByModelNo(modelNo);
        

		
   
	}


	/**
	  모델번호에 해당하는 설명을 수정하기 위해 키보드 입력처리하는 메소드
	*/
	public void inputUpdate(){
        System.out.print("수정하려는 전자제품 모델번호는?? ");
        int modelNo = Integer.parseInt(sc.nextLine());

		System.out.print("변경하려는 모델설명은? ");
        String modelDetail = sc.nextLine();
        	controller.update( new Stock(modelNo , modelDetail) );
        
		

	}
	
	/**
	  모델번호에 해당하는 전자제품 삭제 위해서 모델번호 키보드입력 처리하는 메소드
	*/
  public void inputDelete(){
     System.out.print("삭제 할 전자제품 모델번호는? ");
      int modelNo = Integer.parseInt(sc.nextLine());
    	  controller.deleteModelNo(modelNo);
		
 
	}
  public void inputsort() {
	  System.out.print("정렬할 기준을 선택하세요. \n 1.모델번호  2.모델이름   3.모델가격 ");
      int compare = Integer.parseInt(sc.nextLine());
    	  controller.sort(compare);
		
	}

}