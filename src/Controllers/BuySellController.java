package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import DBService.DBBuySellService;
import DBService.DBBuySellServiceImpl;
import Services.BuySellShowService;
import Services.BuySellShowServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class BuySellController implements Initializable{
	private Parent root;
	private BuySellShowService bss;
	private DBBuySellService bsss; // 여기다 값이 고정되어야하니깐 여기 컨트롤러에서 받아와야할듯
	public void setRoot(Parent root) {
		this.root =root;
		bss.setRoot(root);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bss = new BuySellShowServiceImpl();
		bsss = new DBBuySellServiceImpl();
		//bss.ChoStack(); // 여기서 실행시키면 이때는 널값이니깐 여기는 아닌듯
	}
	
	
	public void btnBuy() {
		bss.BuyTest();
		//bsss.UpdateDTO();
	}
	
	
	public void btnSell() {
		bss.SellTest();
	}
}
