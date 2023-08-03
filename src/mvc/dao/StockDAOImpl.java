package mvc.dao;


import mvc.common.DBManager;
import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.SellingAmountException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	public int sellStockMinusAmount(int amountSell, String stockName) throws SellingAmountException {

		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		String sql = "update User_Stock set amount_buy =? where stock_seq = (select stock_seq from stock where stock_name = ?)";

		try{
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, amountSell);
			ps.setString(2, stockName);
			res = ps.executeUpdate();
		}catch (SQLException e){
			// e.printStackTrace();
			throw new SellingAmountException("매도 과정에서 오류가 발생했습니다.");
		}finally {
			DBManager.releaseConnection(con, ps);
		}

		return res;
	}

	@Override
	public int sellStockDeleteUser(String stockName) throws SellingAmountException {

		Connection con = null;
		PreparedStatement ps = null;
		int res = 0;
		String sql = "delete from User_Stock where stock_seq = (select stock_seq from stock where stock_name = ?)";

		try{
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, stockName);
			res = ps.executeUpdate();
		}catch (SQLException e){
			// e.printStackTrace();
			throw new SellingAmountException("매도 과정에서 오류가 발생했습니다.");
		}finally {
			DBManager.releaseConnection(con, ps);
		}

		return res;
	}

	;


}
