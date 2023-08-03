package mvc.dao;

import java.util.List;

import mvc.dto.Stock;
import mvc.dto.UserStock;
import mvc.exception.SearchNotFoundException;
<<<<<<< HEAD

public interface StockDAO {

	int updateAvgPrice(UserStock us, Stock buyStock);
	
=======
import mvc.exception.SellingAmountException;

public interface StockDAO {

    List<Stock> stockAll() throws SearchNotFoundException;


    List<UserStock> stockUserAll() throws SearchNotFoundException;

	int updateAvgPrice(int updatePrice, String stockname) throws SearchNotFoundException;

	int updateAmountBuy(int updateAmount, UserStock us) throws SearchNotFoundException;

	int insertUserstock(Stock buyStock, int amountBuy) throws SearchNotFoundException;
	
	Stock searchBystockName(String stock_name) throws SearchNotFoundException;
	
	UserStock searchByUserstockName(String stock_name) throws SearchNotFoundException;

	int sellStockMinusAmount(int amountSell, String stockName) throws SellingAmountException;

	int sellStockDeleteUser(String stockName) throws SellingAmountException;
>>>>>>> 9295a714bc0dbde8addac09c44c0c804d6c366ae
	int updatePrice() throws SearchNotFoundException;
}

