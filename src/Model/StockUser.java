package Model;

public class StockUser {
	private String StockUserFK;
	private String userId;
	private String StockName;
	private int SaveStockNumber;
	
	
	
	public String getStockUserFK() {
		return StockUserFK;
	}
	public void setStockUserFK(String userId,String StockName) {
		StockUserFK = userId+","+StockName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStockName() {
		return StockName;
	}
	public void setStockName(String stockName) {
		StockName = stockName;
	}
	public int getSaveStockNumber() {
		return SaveStockNumber;
	}
	public void setSaveStockNumber(int saveStockNumber) {
		SaveStockNumber = saveStockNumber;
	}

}
