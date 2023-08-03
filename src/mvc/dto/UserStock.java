package mvc.dto;

public class UserStock {
	private int stockSeq;
	private String stockCode;
	private String stockName;
	private int amountBuy;
	private int avgprice;
	public UserStock(int stockSeq) {
		this.stockSeq = stockSeq;
	}
	public UserStock() {
		// TODO Auto-generated constructor stub
	}

	public UserStock(int stockSeq, String stockName, int amountBuy) {
		super();
		this.stockSeq = stockSeq;
		this.stockName = stockName;
		this.amountBuy = amountBuy;
	}
	


	public UserStock(int stockSeq, String stockCode, String stockName, int amountBuy, int avgprice) {
		super();
		this.stockSeq = stockSeq;
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.amountBuy = amountBuy;
		this.avgprice = avgprice;
	}





	public UserStock(int stockSeq, int amountBuy, int avgprice) {
		this.stockSeq = stockSeq;
		this.amountBuy = amountBuy;
		this.avgprice = avgprice;
	}
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
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
	
	
	public int getAvgprice() {
		return avgprice;
	}

	public void setAvgprice(int avgprice) {
		this.avgprice = avgprice;
	}

	@Override
	public String toString() {
		return " | 종목코드 : " + stockCode + " | 종목명 : " + stockName + "| 매수량 : " + amountBuy + "| 평균가 : " + avgprice ;
	}
	

}
