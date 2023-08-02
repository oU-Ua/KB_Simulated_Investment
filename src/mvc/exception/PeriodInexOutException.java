package mvc.exception;

/**
 * 검색 결과가 존재하지 않을때 처리 할 예외 클래스 
 * */
public class PeriodInexOutException extends Exception {
	public PeriodInexOutException() {
		super("투자기간은 최소 1일부터 최대 30일까지 설정할 수 있습니다. 다시 입력해주세요.");
	}
	public PeriodInexOutException(String message) {
		super(message);
	}

}
