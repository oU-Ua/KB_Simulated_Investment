package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.SearchNotFoundException;

public interface StockDAO {

    List<Stock> stockAll() throws SearchNotFoundException;


    List<UserStock> stockUserAll() throws SearchNotFoundException;

	int updateAvgPrice(int updatePrice, String stockname) throws SearchNotFoundException;

	int updateAmountBuy(int updateAmount, UserStock us) throws SearchNotFoundException;

	int insertUserstock(Stock buyStock, int amountBuy) throws SearchNotFoundException;
	
	Stock searchBystockName(String stock_name) throws SearchNotFoundException;
	
	UserStock searchByUserstockName(String stock_name) throws SearchNotFoundException;
}
