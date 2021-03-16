package DBService;

import Model.StockUserDTO;

// StockUser 테이블에 산 값을 저장 , 기록느낌?
// USER ID , STOCK NAME , BUY PRICE 가 모두 일치하는 셀렉문을 만들어서 찾아내고 구매하면 추가, 팔면 삭제 해야하는듯
// (가격이다르잔냐 그럼 무조건 BUY PRICE는 없데이트해야하는 부분이고 USER ID , STOCK NAME랑 SaveStockNumber 만
// 변경해줘야지 )

// 마지막으로 산 BUY PRICE 로 업데이트 해주고
// SaveStockNumber 를 추가하거나 줄이거나 해주고

// 로직 정리
// 1번 아이디 로그인 성공 (아이디값은 스트링으로 그대로 어디에다가 저장 fxml쪽에)
// 2번 그 해당 아이디의 소지금, 보유주, 보유주 갯수, 마지막으로 산 해당 주들의 가격을 알려준다
// if(살때) - > 소지금을 주식의 가격만큼빼고 저장, 늘어난 수량, 마지막으로산 가격 저장 (이미산 주식이있다면 거기에업뎃)
// if (팔때) - > 이미산 주식이 있으면 바로 거기에서 줄여주고 판 가격 을 업데이트해준다

// 결론 손익 테이블을 하나 더만들어야할듯 보유 주식이랑 같이

public interface DBBuySellService {
	public int Buy();
	public int Sell();
	public void UpdateDTO();
	public StockUserDTO getstDTO();
	public int ConcurStock(String StockName, int buyStackNumber, boolean buysellChoice);
	public int getNowUserMoney(); // 현재유저의 돈을 받기위해 만든 메소드
	public int UpdateBuy(int stockNumber, int leftMoney, String stockN,boolean buysellB); //DB안에 이미 산 주식이있을경우 더하거나 빼야함
}
