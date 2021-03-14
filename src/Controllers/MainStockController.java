package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Services.BuySellService;
import Services.BuySellServiceImpl;
import Services.MainStockService;
import Services.MainStockServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class MainStockController implements Initializable{
	private Parent root;
	private MainStockService mss; 
	private BuySellService bss;
	
    public void setRoot(Parent root) {
		this.root = root;
		mss.setRoot(root); // 서비스 임플리에 루트값을 가져와줌
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mss = new MainStockServiceImpl();
		bss = new BuySellServiceImpl();
		
	}
	
	//go 매매 매수 창 가는 버튼
	public void btnBuySell() {
		bss.newWindowBuySell();
		
	}
	
	

}
