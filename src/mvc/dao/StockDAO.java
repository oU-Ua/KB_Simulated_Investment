package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Stock;
import mvc.dto.UserStock;

public interface StockDAO {

	int updateAvgPrice(int updatePrice, UserStock us);

	int updateAmountBuy(int updateAmount, UserStock us);

	int insertUserstock(Stock buyStock, int amountBuy);


}
