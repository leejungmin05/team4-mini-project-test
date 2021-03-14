package Services;

import java.io.IOException;

import Controllers.BuySellController;
import Controllers.MainStockController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BuySellServiceImpl implements BuySellService {
	private Parent root;
	private ListView<String> stockList;
	private AreaChart stockArea;
	ObservableList<String> stockString;

	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
		stockList = (ListView<String>) root.lookup("#stockList");
		stockArea = (AreaChart)root.lookup("#stockArea");
		addStockList();
		ChoStack();// 여기서 실행, 한번 메소드 실행만되고 등록만 된다면 사용가능한듯 이게 등록해야 터치시 반응 얻을수있음
		
	}

	@Override
	public void addStockList() {
		stockString = FXCollections.observableArrayList();
		
		// 여기 DB로 주식 데이터를 가져와 주는 부분
		stockString.add("삼승전자");
		stockString.add("SJ브로드밴드");
		stockString.add("무야호이닉스");
		
		
		stockList.setItems(stockString);
		
	}
	
	public void setChart(String title) {
		XYChart.Series series = new XYChart.Series();
		series.setName(title);
		series.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015" ,10 ),
				new XYChart.Data("2016" ,20 ),
				new XYChart.Data("2017" ,7 ),
				new XYChart.Data("2019" ,1 )));
		
		stockArea.getData().add(series);
	}
	
	@Override
	public void ChoStack() {
		stockList.getSelectionModel().selectedIndexProperty().addListener((a,b,c)->{
			System.out.println("형식:" + a);
			System.out.println("이전선택값: "+b);
			System.out.println("지금선택값: "+ c);
			
			
			setChart(stockString.get((int)c));
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
