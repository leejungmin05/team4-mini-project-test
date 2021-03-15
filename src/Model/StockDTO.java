package Model;

import java.util.HashMap;

public class StockDTO {
	HashMap<String, Stock> stockList;
	
	public StockDTO() {
		stockList = new HashMap<String, Stock>();
	}

	public HashMap<String, Stock> getStockList() {
		return stockList;
	}

	public void setStockList(String StockName,Stock stock) {
		this.stockList.put(StockName, stock);
	}
	
	
}
