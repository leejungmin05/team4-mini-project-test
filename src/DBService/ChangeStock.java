package DBService;

//prepre , pre , now (데베 테이블 구조는 이렇게)
// 어떻게 갱신하냐면 가자 오른쪽값을 왼쪽으로 보내고 새로운값 업데이트 가운데있는 값을 또 가장왼쪽값으로
// 주식의 핵심 인터페이스 , 주식의 랜덤적인 변동을 해주는 서비스이다
public interface ChangeStock {
	public int change(String chStockName,int chMoney); // 바뀔 머니가 들어가면 그값대로 변경
	public void preChange(String chStockName);
	public void prepreChange(String chStockName);
}
