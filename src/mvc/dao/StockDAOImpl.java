package mvc.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mvc.common.DBManager;
import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.DMLException;

public class StockDAOImpl implements StockDAO{
	
	private static StockDAO instance = new StockDAOImpl();
	public static StockDAO getInstance() {
		return instance;
		
	}
	private StockDAOImpl() {}
	@Override
	public int updateAvgPrice(int updatePrice, UserStock us) {
		Connection con = null;
		PreparedStatement ps = null;
		int result =0;
		String sql = "update UserStock set avg_price =? where stock_seq =?";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			//?가 있다면 set 설정 필요 
			ps.setInt(1, updatePrice);
			ps.setInt(2, us.getStockSeq());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DMLException("게시물 수정에 오류가 발생했습니다.");
		}finally {
			DBManager.releaseConnection(con, ps);
		}
		return result;
	}
	
	@Override
	public int updateAmountBuy(int updateAmount, UserStock us) {
		Connection con = null;
		PreparedStatement ps = null;
		int result =0;
		String sql = "update UserStock set avg_price =? where stock_seq =?";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			//?가 있다면 set 설정 필요 
			ps.setInt(1, updateAmount);
			ps.setInt(2, us.getStockSeq());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DMLException("게시물 수정에 오류가 발생했습니다.");
		}finally {
			DBManager.releaseConnection(con, ps);
		}
		return result;
	}
	@Override
	public int insertUserstock(Stock buyStock, int amountBuy) {
		Connection con = null;
		PreparedStatement ps = null;
		int result =0;
		String sql = "insert into User_Stock values (?,?,?)";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			//?가 있다면 set 설정 필요 
			ps.setInt(1, buyStock.getStockSeq());
			ps.setInt(2, amountBuy);
			ps.setInt(3, buyStock.getPrice());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DMLException("게시물 수정에 오류가 발생했습니다.");
		}finally {
			DBManager.releaseConnection(con, ps);
		}
		return result;
	};


}
