package mvc.view;

import java.util.List;

import mvc.dto.Headline;
import mvc.dto.Stock;
import mvc.dto.UserStock;

/**
 * 사용자 요청에 해당하는 결과를 모니터에 출력하는 클래스 
 */

public class SuccessView {
	public static final double AVG_RATIO = -100.00;

    /**
     * 전달된 배열을 출력(전체 검색 결과)
     * @param list
     */
    public static void printAll(List<Stock> list){
		System.out.println("**********"+list.size()+"개의 주식 종목이 있습니다. **********");
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
		System.out.println("****** 현재 보유하고 있는 주식은 ("+user.size()+")개입니다. *****************");
        for(UserStock st : user) {
           System.out.println(st.toString());
        }
        System.out.println("남아있는 잔고는 "+balance+"입니다.");

		System.out.println();
    }

    
    public static void printFin(int balance, int seedmoney) {
        System.out.println("모의투자가 종료되었습니다.");
        System.out.println(balance);
        System.out.println(seedmoney);

       double userRatio = ((double)(balance - seedmoney) / (double)seedmoney)*100;
        System.out.printf("사용자의 수익률은 %.2f"+"%% 입니다.",userRatio);
        if(userRatio > AVG_RATIO) 
        	System.out.println("투자왕");
        if(userRatio < AVG_RATIO) 
        	System.out.println("분발 ~");
    }

    public static void printHeadline(List<Headline> list){
        System.out.println("*******" + MenuView.today +"일자 주요 뉴스 *******");
        for(Headline h : list) System.out.println(h);
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
