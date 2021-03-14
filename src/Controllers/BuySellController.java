package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Services.BuySellService;
import Services.BuySellServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class BuySellController implements Initializable{
	private Parent root;
	private BuySellService bss;
	
	public void setRoot(Parent root) {
		this.root =root;
		bss.setRoot(root);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bss = new BuySellServiceImpl();
		//bss.ChoStack(); // 여기서 실행시키면 이때는 널값이니깐 여기는 아닌듯
	}
	
	
	public void btnBuy() {
		
	}
	
	
	public void btnSell() {
		
	}
}
