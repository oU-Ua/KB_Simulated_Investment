package mvc.exception;

/**
 * Electronics배열의 저장공간을 벗어 났을때 처리 할 예외 클래스 
 * */
public class BuyingBalanceException extends Exception {
	public BuyingBalanceException() {
		super("매수하기에 잔고가 부족합니다.");
	}
	public BuyingBalanceException(String message) {
		super(message);
	}

}
