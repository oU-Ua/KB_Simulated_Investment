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
	public int getStockSeq() {
		return stockSeq;
	}
	public void setStockSeq(int stockSeq) {
		this.stockSeq = stockSeq;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getAmountBuy() {
		return amountBuy;
	}
	public void setAmountBuy(int amountBuy) {
		this.amountBuy = amountBuy;
	}
	@Override
	public String toString() {
		return "UserStock [stockSeq=" + stockSeq + ", stockName=" + stockName + ", amountBuy=" + amountBuy + "]";
	}
	

}
