package mvc.view;

import java.util.Scanner;
import mvc.dto.Stock;
import mvc.controller.StockController;

public class BuySellView {
	Scanner sc= new Scanner(System.in);
	StockController controller = new StockController();
	public BuySellView() {
		 System.out.println("매수/매도를 하시겠습니까? 1. 매수   2. 매도  3. 뒤로  4. 장종료 ");
		 int menu = sc.nextInt();
         if(menu ==1)
        	 this.inputBuy();
         else if(menu==2)
        	 this.inputSell();
         else if(menu==3) {
        	 this.back();
         }
         else if(menu ==4) {
        	 controller.finMarket();
        	 MenuView.today++;
        	 
         }
	}
	public void back() {
		System.out.println("3입니다.");
		MenuView mv = new MenuView();
		mv.printMenu();
		
	}
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
