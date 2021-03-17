package Services;

import java.io.IOException;
import java.util.Iterator;

import Controllers.BuySellController;
import Controllers.MainStockController;
import DBService.DBBuySellService;
import DBService.DBBuySellServiceImpl;
import DBService.DBService;
import DBService.DBServiceImpl;
import DBService.StockDBService;
import DBService.StockDBServiceImpl;
import Model.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BuySellShowServiceImpl implements BuySellShowService {
	private Parent root;
	private ListView<String> stockList;
	private AreaChart stockArea;
	ObservableList<String> stockString;
	private XYChart.Series series = new XYChart.Series(); // 그림 겹치기 방지
	private TextField StockTheMumber;
	private TextField StockThePrice;
	private Label LabId; //현재 유저 아이디
	private Label LableftMoney; // 현재 유저 머니
	private TextField SaveStockTheMumber;
	
	
	private String NowLoginUser = Run.Run.getNOWUSER(); // 현재 로그인된 자의 이름을 가져옴
	
	private String choiceStock ; // 현재 뭘 골라놧는지 볼수있는 스트링 
	
	
	private StockDBService sdbs ;
	private DBBuySellService dbbss; // 디비에 저장된 사고팔기를 위한 기능, StockUser 데베 가져온 DTO
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
		stockList = (ListView<String>) root.lookup("#stockList");
		stockArea = (AreaChart)root.lookup("#stockArea");
		StockThePrice = (TextField)root.lookup("#StockThePrice");
		StockTheMumber= (TextField)root.lookup("#StockTheMumber");
		LabId = (Label)root.lookup("#LabId");
		SaveStockTheMumber = (TextField)root.lookup("#SaveStockTheMumber");
		LableftMoney = (Label)root.lookup("#LableftMoney");
		addStockList(); // 리스트에 DB에 모든 주식들을 저장하는 프로그램
		ChoStack();// 여기서 실행, 한번 메소드 실행만되고 등록만 된다면 사용가능한듯 이게 등록해야 터치시 반응 얻을수있음
		LabId.setText(NowLoginUser);
		
		LableftMoney.setText(Integer.toString(Run.Run.getNOWUSERMONEY()));
	}

	@Override
	public void addStockList() {
		stockString = FXCollections.observableArrayList();
		sdbs = new StockDBServiceImpl(); // 여기서 초기화 매번 가져와
		dbbss = new DBBuySellServiceImpl(); // 초기화 여기서
		//작명 개망했다... 겹치는데 나중에 수정해야함
		// 여기 DB로 주식 데이터를 가져와 주는 부분
		Iterator<String> keys =  sdbs.getStockList().getStockList().keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next();
            stockString.add(key);
            System.out.println("KEY : " + key); 
        }
		stockList.setItems(stockString);
		
	}
	
	// 인트 요거는 자료형으로 받아야할 듯
	public void setChart(String title, Stock stockMoney) {
		stockArea.getData().clear(); // 차트안에 데이터값이 중복되어서 열리니깐 삭제해주고 확인
		
		//스톡을 받아서 그안의 stockMoney를 저장한거임
		
		
		series.setName(title);
		series.setData(FXCollections.observableArrayList(
				new XYChart.Data("prepre" ,stockMoney.getPrepreStockPrice()),
				new XYChart.Data("pre" ,stockMoney.getPreStockPrice()),
				new XYChart.Data("now" ,stockMoney.getStockPrice())
				));
		
		stockArea.getData().add(series);
		LableftMoney.setText(Integer.toString(Run.Run.getNOWUSERMONEY()) );
	}
	
	@Override
	public void ChoStack() {
		stockList.getSelectionModel().selectedIndexProperty().addListener((a,b,c)->{
			sdbs = new StockDBServiceImpl(); // 여기서 초기화 매번 가져와
			System.out.println("형식:" + a);
			System.out.println("이전선택값: "+b);
			System.out.println("지금선택값: "+ c);
			choiceStock = stockString.get((int)c); // 이걸 선택해야 저기 인식을함 아래에서 
			// 클릭할때마다 차트값을 초기화후 새로 등록하는 setChart 가 발동
			setChart(stockString.get((int)c), sdbs.getStockList().getStockList().get(stockString.get((int)c)));
			
			String  price = Integer.toString(sdbs.getStockList().getStockList().get(stockString.get((int)c)).getStockPrice()) ;
			StockThePrice.setText(price);

			//System.out.println(NowLoginUser+","+stockString.get((int)c));
			try {
				String userPrice = Integer.toString(dbbss.getstDTO().getstockUserFind().get(NowLoginUser+","+stockString.get((int)c)).getSaveStockNumber());
				if(userPrice.equals(null)) {
				}else {
					SaveStockTheMumber.setText(userPrice);
				}
				
			}catch(Exception e) {
				// 에러 로그는 뺌 일부로 만들어둔거
				System.out.println("산주식이없음");
				SaveStockTheMumber.clear();
			}
			
			//System.out.println(userPrice);
			
			//			fximageView.setImage(
//					new Image("/resources/phone/phone0"+(int)c+".png"));
		
		});
	}
	
	public void BuyTest() {
		dbbss.ConcurStock(choiceStock, Integer.parseInt(StockTheMumber.getText()),true ); // 일단 여기는 사는거니깐
		LableftMoney.setText(Integer.toString(Run.Run.getNOWUSERMONEY()));
	}
	
	public void SellTest() {
		dbbss.ConcurStock(choiceStock, Integer.parseInt(StockTheMumber.getText()),false ); // 일단 여기는 사는거니깐
		LableftMoney.setText(Integer.toString(Run.Run.getNOWUSERMONEY()) );
	}
	
	
	@Override
	public void newWindowBuySell() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/BuySellView.fxml"));
		Stage JoinStage = new Stage();
		Parent root = null;

		try {
			root = loader.load();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(root);

		Scene scene = new Scene(root);
		BuySellController buySellController = loader.getController();
		buySellController.setRoot(root);
		JoinStage.setScene(scene);
		JoinStage.show();

	}
	




}
