package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class StockDTO {
	ArrayList<Stock> ArrStockList;
	HashMap<String, Stock> stockList;
	
	public StockDTO() {
		ArrStockList = new ArrayList<Stock>();
		stockList = new HashMap<String, Stock>();
	}

	
	// 왜냐면 여기서 인덱스값 추적이 가능해지기 때문에
	public ArrayList<Stock> getArrStoList(){
		return ArrStockList;
	}
	
	public void setArrStoList(Stock stock){
		ArrStockList.add(stock);
	}
	
	public HashMap<String, Stock> getStockList() {
		return stockList;
	}

	public void setStockList(String StockName,Stock stock) {
		this.stockList.put(StockName, stock);
	}
	
	
}
