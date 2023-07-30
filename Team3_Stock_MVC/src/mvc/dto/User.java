package mvc.dto;

public class User {
	private int userSeq;
	private String userName;
	private int balance;
	private int ratio;
	public User() {}
	
	/**
	 * 사용자 등록을 위한 생성자 
	 * @param userName
	 * @param balance
	 */
	
	public User(String userName, int balance) {
		this.userName = userName;
		this.balance = balance;
	}

	public User(int userSeq, String userName, int balance, int ratio) {
		super();
		this.userSeq = userSeq;
		this.userName = userName;
		this.balance = balance;
		this.ratio = ratio;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "User [userSeq=" + userSeq + ", userName=" + userName + ", balance=" + balance + ", ratio=" + ratio
				+ "]";
	}
	

}
