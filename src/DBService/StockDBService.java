package DBService;

import Model.Stock;
import Model.StockDTO;

public interface StockDBService {
	public int StockInsert(); // 데베에 있는 모든 주식들을 가져오기
	public StockDTO getStockList(); //지금 저장돼있는 값을 가져오기
}
