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
	public MenuView(int period) {
		this.period = period;
	}
    Scanner sc= new Scanner(System.in);
	StockController controller = new StockController(); // 전역변수 초기화, 생성자 호출 
	
	

	/**
	  전체 메뉴를 출력하는 메소드
	*/
	public void printMenu(){
		int today =0;
        
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
                 System.out.println("매수/매도를 하시겠습니까? 1. 매수   2. 매도  3. 뒤로  ");
                 if(sc.nextInt()==1)
                	 this.inputBuy();
                 else if(sc.nextInt()==2)
                	 this.inputSell();
                 else if(sc.nextInt()==3) //뭘 넣어야뒤로갈까
                 break;
			   case "2" :
	                 controller.stockUser();
	                 System.out.println("매수/매도를 하시겠습니까? 1. 매수   2. 매도  3. 뒤로  ");
	                 if(sc.nextInt()==1)
	                	 this.inputBuy();
	                 else if(sc.nextInt()==2)
	                	 this.inputSell();
			   break;
			   case "3" : 
	                 controller.finMarket();
	                 today++;
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







//	/**
//	   처음 사용자 등록할때 키보드 입력을 처리하는 메소드
//	*/
//	public void inputUser(){
//        System.out.print("사용자 이름을 입력하세요. ");
//        String userName = sc.nextLine();
//
//		System.out.print("초기 예산을 입력하세요. ");
//        int balance = Integer.parseInt(sc.nextLine());
//
//		System.out.print("모의투자를 진행할 기간을 입력하세요. ");
////        day = Integer.parseInt(sc.nextLine());
//
//
//		controller.user( userName, balance);
// 
//
//	}
//	
//	/**
//	  모델번호에 해당하는 설명을 수정하기 위해 키보드 입력처리하는 메소드
//	*/
//    public void inputInfo(){
//        System.out.print("정보를 확인하고 싶은 종목 번호를 입력하세요. 1. ~ 2. ~ 3.~ 4.~ ... 20. ~");
//         int stockSeq = Integer.parseInt(sc.nextLine());
//         	controller.infoSearch(stockSeq);
//    }



	/**
	  모델번호에 해당하는 설명을 수정하기 위해 키보드 입력처리하는 메소드
	*/
	public void inputBuy(){
        System.out.print("매수하려는 종목을 입력하세요. ");
        String stockName = sc.nextLine();
        

		System.out.print("얼마나 매수하시겠습니까?");
		int amountBuy = Integer.parseInt(sc.nextLine());
        	controller.buy( new Stock(stockName , amountBuy) );
        
		

	}
	
	
	/**
	  모델번호에 해당하는 설명을 수정하기 위해 키보드 입력처리하는 메소드
	*/
	public void inputSell(){
        System.out.print("매도하려는 종목을 입력하세요. ");
        String stockName = sc.nextLine();
        

		System.out.print("얼마나 매도하시겠습니까?");
		int amountSell = Integer.parseInt(sc.nextLine());
        	controller.sell( new Stock(stockName , amountSell) );
        
		

	}
 

}