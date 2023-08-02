package mvc.dto;
/**
 * 전자제품의 속성을 관리하는 객체 
 */

public class Stock {
    private int stockSeq;
    private String stockCode;
    private String stockName;
    private int price;
    private int amount;
	private String related;
	private int periodRatio;
    
    
    public Stock() {};
    public Stock(String stockName, int amount) {
    	this.stockName =stockName;
    	this.amount = amount;
    }
    
	public Stock(int stockSeq, String stockName, int price, int amount) {
		super();
		this.stockSeq = stockSeq;
		this.stockName = stockName;
		this.price = price;
		this.amount = amount;
	}

	public int getStockSeq() {
		return stockSeq;
	}
	public void setStockSeq(int stockSeq) {
		this.stockSeq = stockSeq;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getRelated() {
		return related;
	}
	public void setRelated(String related) {
		this.related = related;
	}
	public int getPeriodRatio() {
		return periodRatio;
	}
	public void setPeriodRatio(int periodRatio) {
		this.periodRatio = periodRatio;
	}
	@Override
	public String toString() {
		return stockCode + " | " + stockName + "|" + price + "|" + "|" + amount + "|" + related+ "|" + periodRatio;
	}
	public String toString(int i) {
		return stockCode + " | " + stockName + "|" + price ;
	}



    
}
