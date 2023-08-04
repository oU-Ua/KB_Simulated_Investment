package mvc.view;

import java.io.IOException;
import java.util.Scanner;
import mvc.dto.Stock;
import mvc.controller.StockController;

public class BuySellView {
	Scanner sc= new Scanner(System.in);
	StockController controller = new StockController();
	public BuySellView(String stockName, int balance) throws IOException {
		System.out.println("매수/매도를 하시겠습니까?\n1. 매수   2. 매도   3. 뒤로 ");
		int menu = sc.nextInt();
		if(menu ==1)
			this.inputBuy(stockName, balance);
		else if(menu==2)
			this.inputSell(stockName, balance);
		else if(menu==3) 
			this.back(balance);
		else {
			System.out.println("잘못된 입력입니다. 다시 입력하세요.\n");
			new BuySellView(stockName, balance);
		}
			
	}
	public void back(int balance) throws IOException {
		System.out.println("메뉴로 돌아갑니다.");
		MenuView mv = new MenuView(balance);
		mv.printMenu();

	}
	/**
	  매수하기 위해 매수하려는 종목을 키보드 입력처리하는 메소드
	 * @throws IOException 
	 */
	public void inputBuy(String stockName, int balance) throws IOException{
		System.out.println("얼마나 매수하시겠습니까?");
		int amountBuy = sc.nextInt();
		controller.buy(stockName,amountBuy, balance);



	}


	/**
	  모델번호에 해당하는 설명을 수정하기 위해 키보드 입력처리하는 메소드
	 * @throws IOException 
	 */
	public void inputSell(String stockName, int balance) throws IOException{


		System.out.print("얼마나 매도하시겠습니까?");
		int amountSell = sc.nextInt();
		controller.sell( stockName , amountSell,balance );
	}

}
