package Model;

import java.util.ArrayList;
import java.util.HashMap;

// 스트링 저거 어케하냐면 중간에 쉼표를 넣는거임 그래서 키값이 'userId','StockName' 이렇게 들어가는거지

public class StockUserDTO {
	private HashMap<String, StockUser> stockUserFind;
	private ArrayList<StockUser> stockUserList;
	
	public StockUserDTO() {
		stockUserFind = new HashMap<String, StockUser>();
		stockUserList = new ArrayList<StockUser>();
	}
	
	
	public HashMap<String, StockUser> getstockUserFind(){
		return stockUserFind;
	}
	
	public void setStockUserFind(String Key, StockUser StockUserList) {
		stockUserFind.put(Key, StockUserList);
	}
	
	
	public ArrayList<StockUser> getStockUserList(){
		return stockUserList;
	}
	
	public void setStockUserList(StockUser stockuser) {
		stockUserList.add(stockuser);
	}
	
}
