package mvc.dto;

public class UserStock {
	private int stockSeq;
	private String stockName;
	private int amountBuy;
	public UserStock(int stockSeq, String stockName, int amountBuy) {
		super();
		this.stockSeq = stockSeq;
		this.stockName = stockName;
		this.amountBuy = amountBuy;
	}
	

}
