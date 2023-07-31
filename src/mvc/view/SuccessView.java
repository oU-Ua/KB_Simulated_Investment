package mvc.view;

import java.util.List;

import mvc.dto.Stock;
import mvc.dto.User;
import mvc.dto.UserStock;

/**
 * 사용자 요청에 해당하는 결과를 모니터에 출력하는 클래스 
 */

public class SuccessView {

    /**
     * 전달된 배열을 출력(전체 검색 결과)
     * @param list
     */
    public static void printAll(List<Stock> list){
		System.out.println("******  총 ("+list.size()+")개의 주식 종목이 있습니다. *****************");
        for(Stock st : list) {
           System.out.println(st);
        }

		System.out.println();
    }
    /**
     * 전달된 배열을 출력 (사용자의 주식 검색 결과)
     * @param user
     */
    public static void printUser(List<UserStock> user){
		System.out.println("****** 현재 보유하고 있는 주식은 ("+user.size()+")개입니다. *****************");
        for(UserStock st : user) {
           System.out.println(st);
        }
        System.out.println("남아있는 잔고는 "+User.balance+"입니다.");

		System.out.println();
    }

    
    public static void printSearchByModelNo(Stock electronics) {
        System.out.println(electronics +"\n");
    }

    /**
     * 성공에 관련된 메세지 출력 
     * @param message
     */
    public static void printMessage(String message) {
         System.out.println(message+"\n");
    }

    
}
