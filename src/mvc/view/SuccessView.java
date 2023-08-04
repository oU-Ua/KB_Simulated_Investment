package mvc.view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import mvc.dto.Headline;
import mvc.dto.Stock;
import mvc.dto.UserStock;

/**
 * 사용자 요청에 해당하는 결과를 모니터에 출력하는 클래스 
 */

public class SuccessView {
	

	public static final double AVG_RATIO = -13.40;

    /**
     * 전달된 배열을 출력(전체 검색 결과)
     * @param list
     */
    public static void printAll(List<Stock> list){
		System.out.println("\n-------"+list.size()+"개의 주식 종목이 있습니다. -------\n");
        for(Stock st : list) {
           System.out.println(st.toString(0));
        }
        System.out.println();
    }
	public static void printDetail(Stock stock) {
		System.out.println(stock.toString());
		System.out.println();
	}

	
    /**
     * 전달된 배열을 출력 (사용자의 주식 검색 결과)
     * @param user
     */
    public static void printUser(List<UserStock> user,int balance){
		System.out.println("\n------- 현재 보유하고 있는 주식은 "+user.size()+"개입니다. -------\n");
        for(UserStock st : user) {
           System.out.println(st.toString());
        }
        System.out.println("\n남아있는 잔고는 ₩"+balance+" 입니다.");

		System.out.println();
    }

    
    public static void printFin(int balance, int seedmoney) throws IOException {
        System.out.println("모의투자가 종료되었습니다.\n");

       double userRatio = ((double)(balance - seedmoney) / (double)seedmoney)*100;
        System.out.printf("사용자의 수익률은 %.2f"+"%% 입니다.\n",userRatio);
        if(userRatio > AVG_RATIO) 
        	System.out.println("\n작년 개인투자자 수익률은 평균 13.4%였습니다.\n\n당신은 👑투자왕👑. \n\nKB와 함께 👑투자킹왕짱👑으로 성장하세요.");
        if(userRatio < AVG_RATIO) 
        	System.out.println("분발 ~");
        
    	BufferedReader buff = new BufferedReader(new FileReader("src/kb.txt"));
    	String str;
    	while((str = buff.readLine()) != null) {
    		System.out.println(str);
    	}
        
        
    }
    public static void printHeadline(List<Headline> list){
        System.out.println("\n\n--------------------------------- " + MenuView.today +" 일자 주요 뉴스 --------------------------------------\n");
        for(Headline h : list) {
        	System.out.println("- "+ h);
        	System.out.println();
        }
     
        System.out.println();
        System.out.println();
    }


    /**
     * 성공에 관련된 메세지 출력 
     * @param message
     */
    public static void printMessage(String message) {
         System.out.println(message+"\n");
    }
    /**
     * detail 출출력 
     * @param stockDetail
     */


    
}
