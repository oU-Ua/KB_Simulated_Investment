package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.SearchNotFoundException;

public interface StockDAO {

	int updateAvgPrice(UserStock us, Stock buyStock);

    List<Stock> stockAll() throws SearchNotFoundException;


    List<UserStock> stockUserAll() throws SearchNotFoundException;
}
