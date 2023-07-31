package mvc.dto;

public class User {
	public static int balance;
	private int ratio;

	public User(int balance) {
		this.balance = balance;
	}


	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	
	
}
