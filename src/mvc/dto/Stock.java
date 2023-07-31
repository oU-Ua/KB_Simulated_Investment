package mvc.dto;
/**
 * 전자제품의 속성을 관리하는 객체 
 */

public class Stock {
    private int stockSeq;
    private String stockName;
    private int price;
    private int amount;
    
    
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
	@Override
	public String toString() {
		return "Stock [stockSeq=" + stockSeq + ", stockName=" + stockName + ", price=" + price + ", amount=" + amount
				+ "]";
	}



    
}
