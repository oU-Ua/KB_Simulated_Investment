package mvc.view;

import java.util.Scanner;

import mvc.controller.StockController;
import mvc.exception.PeriodInexOutException;

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


            	int period =0;
            	int balance;
            	while(true) {
            		try {
            			balance = inputSeedMoney();
            			break;
            		} catch (NumberFormatException e) {
            			System.out.println("시드머니는 숫자로만 설정할 수 있습니다. 다시 입력해주세요.");
            		}
            	}

            	while(true) {
            		try {
            			period = inputPeriod();
            			break;
            		} catch (NumberFormatException e) {
            			System.out.println("기간은 숫자로만 설정할 수 있습니다. 다시 입력해주세요.");
            		} catch (PeriodInexOutException e) {
            			FailView.errorMessage(e.getMessage());
            		}
            	}
                //투자기간에 대한 제한 ?
            	System.out.println();
                MenuView.period = period;
                MenuView.seedmoney = balance;
                
                System.out.println();
            	 MenuView mv = new MenuView(balance); // 전역변수 초기화, 생성자 호출 
                 mv.printMenu();
                 break;
            }
            else {
            	System.out.println("잘못된 입력입니다. Y 혹은 N으로 입력해주세요.");
            	System.out.println("**************다시 시작합니다 **************\n\n");
            }
    	}

        
    }

    public static int inputPeriod() throws PeriodInexOutException{
    	System.out.println("\n투자기간을 입력하세요. (단위 : 일)");
    	int  period = Integer.parseInt(sc.nextLine());
    	if(period > 30 || period <1) {
    		throw new PeriodInexOutException();
    	}
    	return period;

    }

    public static int inputSeedMoney() {
    	String str;
    	while(true) {
    		System.out.println("시드머니를 입력하세요. (단위 : 원) ");
    		str = (sc.nextLine());
    		if(Integer.MAX_VALUE<Long.parseLong(str)) {
    			System.out.print("숫자를 너무 크게 입력했습니다. 줄여주세요\n다시");
    			continue;
    		}else if(1>Integer.parseInt(str)) {
    			System.out.print("0 이하는 입력할 수 없습니다. \n다시 ");
    			continue;
    		}

    		int balance = Integer.parseInt(str);

    		return balance;
    	}
    }

    
}
