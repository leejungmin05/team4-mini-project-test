package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import DBService.DBMainStock;
import DBService.DBMainStockImpl;
import Model.DisplayTableData;
import Services.BuySellShowService;
import Services.BuySellShowServiceImpl;
import Services.MainStockService;
import Services.MainStockServiceImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainStockController implements Initializable{
	private Parent root;
	private MainStockService mss; 
	private BuySellShowService bss;
	private DBMainStock dbs;
	
	@FXML
	private TableView<Model.tableView> tableView;
	@FXML
	private TableColumn<Model.tableView,String> stockName;
	@FXML
	private TableColumn<Model.tableView,Integer> stockPrice;
	@FXML
	private TableColumn<Model.tableView,Integer> changePrice;
	
	public MainStockController(ObservableList<Model.tableView> myList) {
		
		tableView.getColumns().setAll(new DisplayTableData().getColumns());
		tableView.getItems().setAll(myList);
	}
	

	
    public void setRoot(Parent root) {
		this.root = root;
		mss.setRoot(root); // 서비스 임플리에 루트값을 가져와줌
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mss = new MainStockServiceImpl();
		bss = new BuySellShowServiceImpl();
		dbs = new DBMainStockImpl();
		
		ObservableList<Model.tableView> myList = FXCollections.observableArrayList();
		stockName.setCellValueFactory(new PropertyValueFactory<>("stockName"));
		stockPrice.setCellValueFactory(new PropertyValueFactory<>("stockPrice"));
		changePrice.setCellValueFactory(new PropertyValueFactory<>("changePrice"));
		
		dbs.DBLoad();
		
	    tableView.setItems(myList);
		
	}
	
	//go 매매 매수 창 가는 버튼
	public void btnBuySell() {
		bss.newWindowBuySell();
		
	}
	
	

}
