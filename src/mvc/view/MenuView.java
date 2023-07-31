package mvc.view;

import java.util.Scanner;


import mvc.controller.StockController;
import mvc.dto.Stock;

/**
   사용자의 요청을 키보드로 입력받는 클래스 
*/
public class MenuView{
	int period;
	public MenuView() {}
	public MenuView(int period,int balance) {
		this.period = period;
		this.balance = balance;
	}
    Scanner sc= new Scanner(System.in);
	StockController controller = new StockController(); // 전역변수 초기화, 생성자 호출 
	public static int today =1;
	public static int balance;

	/**
	  전체 메뉴를 출력하는 메소드
	*/
	public void printMenu(){
		
        
	     while(today < period+1){
	    	 System.out.println(today+"일차 시작합니다.");
//	    	 System.out.pirntln(controller.headline(today)); headline을 어떻게 보여줄 지 모르겠어서 일단 함수형태로 생각해봤어요 
           System.out.println("----------------------------------------------------------------------------------");
           System.out.println("1. 종목 조회    2. 보유주식 조회     3. 장 종료      9. 프로그램 종료");
		   System.out.println("----------------------------------------------------------------------------------");
		   System.out.print("메뉴선택 > ");

		   String  menu = sc.nextLine();
		   switch(menu){
			   case "1" : 
				   controller.stockAll();
				   BuySellView bs = new BuySellView();
				   break;
			   case "2" :
	                 controller.stockUser();
	                 BuySellView bs1 = new BuySellView();
			   break;
			   case "3" : 
	                 controller.finMarket();
				   break;
			   case "9" : 
				   System.out.println("지금 " + today +"일차입니다. 정말로 프로그램을 종료하시겠습니까? (Y/N)");
				   if(sc.nextLine().equals("Y")||sc.nextLine().equals("y"))
					   System.exit(0);
				   else 
					   continue;
			   default:
				   System.out.println("메뉴를 다시 선택해주세요!!!!");

		   }//switch문끝
		   

		 }//while문끝
	     if(today == period) {
	    	 SuccessView.printMessage(null);
	     }

	}//메소드끝








 

}