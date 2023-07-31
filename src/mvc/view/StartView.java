package mvc.view;

import java.util.Scanner;
import mvc.dto.User;

class StartView {
    
    public static void main(String []args) {
    	
    	Scanner sc= new Scanner(System.in);
        System.out.println("===== KB 모의 투자 프로그램을 시작합니다. =====");
        System.out.println("시작하시겠습니까? (Y/N) ");
        String  start = sc.nextLine();
        if(start.equals("N")||start.equals("n")) {
        	System.out.println("프로그램을 종료합니다.");
        	System.exit(0);
        }
        else if(start.equals("Y")||start.equals("y")) {
        	System.out.println("시드머니를 입력하세요.");
            int  balance = Integer.parseInt(sc.nextLine());
            System.out.println("투자기간을 입력하세요.");
            int  period = Integer.parseInt(sc.nextLine());
            User.balance = balance;
            
        	 MenuView mv = new MenuView(period,balance); // 전역변수 초기화, 생성자 호출 
             mv.printMenu();
        }
    }
    
    
}
