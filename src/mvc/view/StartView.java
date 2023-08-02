package mvc.view;

import java.util.Scanner;

import mvc.controller.StockController;

class StartView {
	static Scanner sc= new Scanner(System.in);
	
    public static void main(String []args) {
    	System.out.println("===== KB 모의 투자 프로그램을 시작합니다. =====");
    	while(true) {
           
            System.out.println("시작하시겠습니까? (Y/N) ");
            String  start = sc.nextLine();
            if(start.equals("N")||start.equals("n")) {
            	System.out.println("프로그램을 종료합니다.");
            	System.exit(0);
            }
            else if(start.equals("Y")||start.equals("y")) {
            	int seedMoney = 0;
            	int period =0;
            	try {
            		seedMoney = inputSeedMoney();
				} catch (NumberFormatException e) {
					System.out.println("시드머니는 숫자로만 설정할 수 있습니다. 다시 입력해주세요.");
					inputSeedMoney();
				}
            	
            	try {
            		period = inputPeriod();
				} catch (NumberFormatException e) {
					System.out.println("period"+e.getMessage());
					System.out.println("기간은 숫자로만 설정할 수 있습니다. 다시 입력해주세요.");
					inputPeriod();
				}
               
                //투자기간에 대한 제한 ?
                MenuView.period = period;
                StockController.seedmoney = seedMoney;
            	 MenuView mv = new MenuView(); // 전역변수 초기화, 생성자 호출 
                 mv.printMenu();
                 break;
            }
            else {
            	System.out.println("잘못된 입력입니다. Y 혹은 N으로 입력해주세요.");
            	System.out.println("**************다시 시작합니다 **************\n\n");
            }
    	}

        
    }

	public static int inputPeriod() {
		 System.out.println("\n투자기간을 입력하세요. (단위 : 일)");
         int  period = Integer.parseInt(sc.nextLine());
		if(period > 30 && period <1) {
			
		}
		return period;
		
	}

	public static int inputSeedMoney() {
		System.out.println("시드머니를 입력하세요. (단위 : 원) ");
        int  balance = Integer.parseInt(sc.nextLine());
		return balance;
	}
    
    
}
