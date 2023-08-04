package mvc.view;

import java.io.IOException;
import java.util.Scanner;


import mvc.controller.StockController;
import mvc.dto.Stock;

/**
   사용자의 요청을 키보드로 입력받는 클래스 
*/
public class MenuView{
	
	

	public MenuView() {}
	public MenuView(int balance) {
		this.balance = balance;
		
	}
	public int balance;
	public static int period;
    public static int seedmoney = 0;
	Scanner sc= new Scanner(System.in);
	StockController controller = new StockController(); // 전역변수 초기화, 생성자 호출 
	public static int today =1;
	public static boolean statement = true;

	/**
	  전체 메뉴를 출력하는 메소드
	 * @throws 
	*/
	public void printMenu() throws IOException{
		
        
	     while(today < period+1){
	    	 System.out.println("\n");
	    	 System.out.println(today+"일차 장 \n");
	    	 System.out.println("사용자 잔고 : "+ balance);
	    	 if(statement) controller.headline(today);
	    	 statement=false;
//	    	 System.out.pirntln(controller.headline(today)); headline을 어떻게 보여줄 지 모르겠어서 일단 함수형태로 생각해봤어요 
           System.out.println("------------------------------------- Menu -----------------------------------------");
           System.out.println("      1. 종목 조회    2. 보유주식 조회    3.매수/매도     4. 장 종료     9. 프로그램 종료");
		   System.out.println("------------------------------------------------------------------------------------");
		   System.out.print("\n메뉴선택 > ");

		   String  menu = sc.nextLine();
		   switch(menu){
			   case "1" : 
				   controller.stockAll();
				   break;
			   case "2" :
	                 controller.stockUser(balance);
	                 System.out.println("메뉴로 돌아갑니다.");
			   break;
			   case "3" : 
	                 this.inputDetail(balance);
				   break;
			   case "4" : 
	                 controller.finMarket();
				   break;
			   case "9" : 
				   System.out.println("지금 " + today +"일차입니다. 정말로 프로그램을 종료하시겠습니까? (Y/N)");
				   String exitMenu = sc.nextLine();
				   if(exitMenu.equals("Y")||exitMenu.equals("y")) {
					   controller.fin(balance, seedmoney);
					   System.exit(0);
					   }
				   
				   else 
					   continue;
			   default:
				   System.out.println("해당하는 메뉴가 없습니다. 메뉴를 다시 선택해주세요.");
		   }//switch문끝
		   

		 }//while문끝
	     if(today == period+1) {
	    	 
	    	 controller.fin(balance, seedmoney);
	    	 System.exit(0);
	     }

	}//메소드끝
	public void inputDetail(int balance) throws IOException {
		System.out.println("선택하려는 종목의 이름을 입력하세요. ");
		String stockName = sc.nextLine();
		controller.detail(stockName, balance);

	}








 

}