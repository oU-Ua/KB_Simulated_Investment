package mvc.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO{
	private static StockDAO instance = new StockDAOImpl();
	private StockDAOImpl() {};

	public static StockDAO getInstance() {
		return instance;
		
	}
}
