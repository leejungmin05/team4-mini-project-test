package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Services.JoinService;
import Services.JoinServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class JoinController implements Initializable{
	private Parent root;
    private JoinService js;
	
	public void setRoot(Parent root) {
		this.root = root;
		js.setController(root); // 이게 포인트 여기서 받아내야 서비스로 날아간다
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		js = new JoinServiceImpl();
		
	}
	
	public void btnGoLogin() {
		
	}
	
	public void btnJoin() {
		js.JoinInsert();
	}

}
