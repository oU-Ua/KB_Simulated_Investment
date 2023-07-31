package mvc.dto;

public class User {
	private int balance;
	private int ratio;
	private int userStock;
	private int period;
	public User(int balance, int period) {
		this.balance = balance;
		this.period = period;
	}
	public User(int balance, int ratio, int userStock, int period) {
		super();
		this.balance = balance;
		this.ratio = ratio;
		this.userStock = userStock;
		this.period = period;
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
	public int getUserStock() {
		return userStock;
	}
	public void setUserStock(int userStock) {
		this.userStock = userStock;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	
	
	
}
