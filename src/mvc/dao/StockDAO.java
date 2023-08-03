package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.SellingAmountException;

public interface StockDAO {

	int updateAvgPrice(UserStock us, Stock buyStock);

	int sellStockMinusAmount(int amountSell, String stockName) throws SellingAmountException;

	int sellStockDeleteUser(String stockName) throws SellingAmountException;
}
