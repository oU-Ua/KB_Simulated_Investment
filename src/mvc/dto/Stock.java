package mvc.dto;
/**
 * 전자제품의 속성을 관리하는 객체 
 */

public class Stock {
    private int stockSeq;
    private String stockCode;
    private String stockName;
    private int price;
	private String related;
    private int evaluation;
    
    public Stock() {};

    
	public Stock(String stockCode, String stockName, int price) {
		super();
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.price = price;

	}
	

	public Stock(int stockSeq, String stockCode, String stockName, int price, String related, int evaluation) {
		super();
		this.stockSeq = stockSeq;
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.price = price;
		this.related = related;
		this.evaluation = evaluation;
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


	public String getRelated() {
		return related;
	}


	public void setRelated(String related) {
		this.related = related;
	}


	public int getEvaluation() {
		return evaluation;
	}


	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}


	@Override
	public String toString() {
		String s = "";
		for(int i=0; i<evaluation ;i++)
			s +="★";
		for(int i=0; i<10-evaluation ;i++)
			s +="☆";
		return "종목코드 : " +stockCode + " | 종목명 : " + stockName + " | 현재가 : ₩" + price + " | 테마 : "  + related+ " | 종목평가 : "+s;
		
	}
	public String toString(int i) {
		return " 종목코드 : " + stockCode + " | 종목명 : " + stockName + " | 현재가 : ₩"+ price ;
	}



    
}
