package mvc.dao;


import mvc.dto.Stock;
import mvc.dto.UserStock;

public class StockDAOImpl implements StockDAO{
	
	private static StockDAO instance = new StockDAOImpl();
	public static StockDAO getInstance() {
		return instance;
		
	}
	private StockDAOImpl() {}
	@Override
	public int updateAvgPrice(UserStock us, Stock buyStock) {
		// TODO Auto-generated method stub
		return 0;
	};


}
