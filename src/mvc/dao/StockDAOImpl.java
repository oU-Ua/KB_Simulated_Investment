package mvc.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.common.DBManager;
import mvc.dto.Headline;
import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.SearchNotFoundException;
import mvc.exception.SellingAmountException;
import mvc.view.MenuView;

public class StockDAOImpl implements StockDAO{
	
	private static StockDAO instance = new StockDAOImpl();
	public static StockDAO getInstance() {
		return instance;
		
	}
	private StockDAOImpl() {}
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
				Stock dto = new Stock(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getInt(6));
				stockList.add(dto);
			}
		} catch (SQLException e) {
			
			throw new SearchNotFoundException("전체주식 검색에 예외가 발생했습니다. 다시 조회해주세요.");
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
				UserStock dto = new UserStock(rs.getInt("stock_Seq"),rs.getString("stock_Code"),rs.getString("stock_Name"),rs.getInt("amount_Buy"),rs.getInt("avg_price"));

				userStockList.add(dto);
			}
	} catch (SQLException e) {
		e.printStackTrace();
			throw new SearchNotFoundException("보유주식 검색에 예외가 발생했습니다. 다시 조회해주세요.");
			
		}finally {
			DBManager.releaseConnection(con,ps,rs);
		}
		return userStockList;
		}


	@Override
	public int updateAvgPrice(int updatePrice, String stockname) throws SearchNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		int result =0;
		String sql = "update user_stock set avg_price =? where stock_seq = (select stock_seq from stock where stock_name = ?)";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			System.out.println(updatePrice);
			System.out.println(stockname);
			//?가 있다면 set 설정 필요 
			ps.setInt(1, updatePrice);
			ps.setString(2, stockname);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchNotFoundException("");
		}finally {
			DBManager.releaseConnection(con, ps);
		}
		return result;
	}
	
	@Override
	public int updateAmountBuy(int updateAmount, UserStock us) throws SearchNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		int result =0;
		String sql = "update user_stock set amount_buy =? where stock_seq =?";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			//?가 있다면 set 설정 필요 
			ps.setInt(1, updateAmount);
			ps.setInt(2, us.getStockSeq());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchNotFoundException("오류가 발생했습니다.");
		}finally {
			DBManager.releaseConnection(con, ps);
		}
		return result;
	}
	@Override
	public int insertUserstock(Stock buyStock, int amountBuy) throws SearchNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		int result =0;
		String sql = "insert into User_Stock (stock_seq,AMOUNT_BUY,AVG_PRICE)values (?,?,?)";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			//?가 있다면 set 설정 필요 
			ps.setInt(1, buyStock.getStockSeq());
			ps.setInt(2, amountBuy);
			ps.setInt(3, buyStock.getPrice());
			result = ps.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchNotFoundException("게시물 수정에 오류가 발생했습니다.");
		}finally {
			DBManager.releaseConnection(con, ps);
		}
		return result;
	}
	@Override
	public Stock searchBystockName(String stock_name) throws SearchNotFoundException {
		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		Stock stock = new Stock();
		String sql ="select * from Stock where stock_name =?";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, stock_name);
			rs = ps.executeQuery();
			
			if(rs.next()){
				stock = new Stock(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getInt(6));
				
			}
		} catch (SQLException e) {
			
			throw new SearchNotFoundException("전체주식 검색에 예외가 발생했습니다. 다시 조회해주세요.");
		}finally {
			DBManager.releaseConnection(con,ps,rs);
		}
		return stock;
		
	}
	@Override
	public UserStock searchByUserstockName(String stock_name) throws SearchNotFoundException {
		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		UserStock userstock = new UserStock();
		String sql ="select * from User_Stock where stock_seq = (select stock_seq from stock where stock_name = ?)";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, stock_name);
			rs = ps.executeQuery();
			
			if(rs.next()){
				userstock = new UserStock(rs.getInt(1),rs.getInt(2),rs.getInt(3));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchNotFoundException("유저보유주식 검색에 예외가 발생했습니다. 다시 조회해주세요.");
		}finally {
			DBManager.releaseConnection(con,ps,rs);
		}
		return userstock;

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
	}

	@Override
	public List<Headline> getHeadline(int day) throws SearchNotFoundException {

		Connection con =null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		List<Headline> list = new ArrayList<>();
		String sql ="select * from headline where day = ?";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, day);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Headline(rs.getInt("day"), rs.getString("info")));
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new SearchNotFoundException("헤드라인 조회에 오류가 발생했습니다");

		}finally {
			DBManager.releaseConnection(con,ps,rs);
		}

		return list;
	}

}
