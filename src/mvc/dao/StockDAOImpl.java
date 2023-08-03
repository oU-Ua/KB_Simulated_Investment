package mvc.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mvc.common.DBManager;
import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.SearchNotFoundException;
import mvc.view.MenuView;

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
	}
	@Override
	public int updatePrice() throws SearchNotFoundException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update (select a.price, b.D" + MenuView.today + " from stock a, stock_price b "
				+ "where a.stock_seq = b.stock_seq) set price = D" + MenuView.today;
		int result = 0;
		
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			
			result = ps.executeUpdate();
		}catch(SQLException e) {
//			e.printStackTrace();
			throw new SearchNotFoundException("종목가 업데이트에 실패했습니다.");
		}finally {
			DBManager.releaseConnection(con, ps);
		}
		
		return result;
	};


}
