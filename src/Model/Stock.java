package Model;

public class Stock {
	private String StockName;
	private int StockPrice;
	private int preStockPrice;
	private int prepreStockPrice;
	
	public int getPreStockPrice() {
		return preStockPrice;
	}
	public void setPreStockPrice(int preStockPrice) {
		this.preStockPrice = preStockPrice;
	}
	public int getPrepreStockPrice() {
		return prepreStockPrice;
	}
	public void setPrepreStockPrice(int prepreStockPrice) {
		this.prepreStockPrice = prepreStockPrice;
	}
	public String getStockName() {
		return StockName;
	}
	public void setStockName(String stockName) {
		StockName = stockName;
	}
	public int getStockPrice() {
		return StockPrice;
	}
	public void setStockPrice(int stockPrice) {
		StockPrice = stockPrice;
	}
}
