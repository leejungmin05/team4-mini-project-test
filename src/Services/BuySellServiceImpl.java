package Services;

import java.io.IOException;
import java.util.Iterator;

import Controllers.BuySellController;
import Controllers.MainStockController;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BuySellServiceImpl implements BuySellService {
	private Parent root;
	private ListView<String> stockList;
	private AreaChart stockArea;
	ObservableList<String> stockString;
	private XYChart.Series series = new XYChart.Series(); // 그림 겹치기 방지
	private TextField StockTheMumber;
	private TextField StockThePrice;
	
	private StockDBService sdbs ;
	
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
		stockList = (ListView<String>) root.lookup("#stockList");
		stockArea = (AreaChart)root.lookup("#stockArea");
		StockThePrice = (TextField)root.lookup("#StockThePrice");
		StockTheMumber= (TextField)root.lookup("#StockTheMumber");
		
		addStockList();
		ChoStack();// 여기서 실행, 한번 메소드 실행만되고 등록만 된다면 사용가능한듯 이게 등록해야 터치시 반응 얻을수있음
		
	}

	@Override
	public void addStockList() {
		stockString = FXCollections.observableArrayList();
		sdbs = new StockDBServiceImpl(); // 여기서 초기화 매번 가져와
		
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
		
		
		
		series.setName(title);
		series.setData(FXCollections.observableArrayList(
				new XYChart.Data("prepre" ,stockMoney.getPrepreStockPrice()),
				new XYChart.Data("pre" ,stockMoney.getPreStockPrice()),
				new XYChart.Data("now" ,stockMoney.getStockPrice())
				));
		
		stockArea.getData().add(series);
	}
	
	@Override
	public void ChoStack() {
		stockList.getSelectionModel().selectedIndexProperty().addListener((a,b,c)->{
			sdbs = new StockDBServiceImpl(); // 여기서 초기화 매번 가져와
			System.out.println("형식:" + a);
			System.out.println("이전선택값: "+b);
			System.out.println("지금선택값: "+ c);
			
			
			setChart(stockString.get((int)c),sdbs.getStockList().getStockList().get(stockString.get((int)c)));
			String  price = Integer.toString(sdbs.getStockList().getStockList().get(stockString.get((int)c)).getStockPrice()) ;
			StockThePrice.setText(price);
//			fximageView.setImage(
//					new Image("/resources/phone/phone0"+(int)c+".png"));
		
		});
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
