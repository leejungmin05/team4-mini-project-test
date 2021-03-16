package Services;

import javafx.scene.Parent;

public interface BuySellShowService {
	public void setRoot(Parent root);
	public void newWindowBuySell(); // 매수 매매 창 열기
	public void ChoStack(); // 리스트뷰에서 주식을 선택했을때 반응효과 하는것
	public void addStockList(); // 리스트 뷰 주식 추가하는 메소드
	public void BuyTest(); //바이테스트
	public void SellTest();
	//public void setChart();

}
