package mvc.dao;


import mvc.common.DBManager;
import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.SearchNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public List<Stock> stockAll() throws SearchNotFoundException {
		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		List<Stock> stockList = new ArrayList<>();
		String sql ="select * from Stock";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Stock dto = new Stock(rs.getInt(1), rs.getString(2), rs.getInt(3));
				stockList.add(dto);
			}
		} catch (SQLException e) {
			throw new SearchNotFoundException("전체검색에 예외가 발생했습니다. 다시 조회해주세요.");
		}finally {
			DBManager.releaseConnection(con,ps,rs);
		}
		return stockList;
	}

	@Override
	public List<UserStock> stockUserAll() throws SearchNotFoundException {
		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		List<UserStock> userStockList = new ArrayList<>();
		String sql ="select * from user_stock join stock using (stock_seq)";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				UserStock dto = new UserStock(rs.getInt("stockSeq"),rs.getInt("stockCode"),rs.getString("stockName"),rs.getInt("amountBuy"),rs.getInt("avgprice"));

				userStockList.add(dto);
			}
	} catch (SQLException e) {
			throw new SearchNotFoundException("보유주식 검색에 예외가 발생했습니다. 다시 조회해주세요.");
		}finally {
			DBManager.releaseConnection(con,ps,rs);
		}
		return userStockList;
		}


	}
