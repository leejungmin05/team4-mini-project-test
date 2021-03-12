package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Services.JoinService;
import Services.JoinServiceImpl;
import Services.LoginService;
import Services.LoginServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class JoinController implements Initializable{
	private Parent root;
    private JoinService js;
    private LoginService ls;
	
	public void setRoot(Parent root) {
		this.root = root;
		js.setController(root); // 이게 포인트 여기서 받아내야 서비스로 날아간다
		ls.setJoinRoot(root);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		js = new JoinServiceImpl();
		ls = new LoginServiceImpl();
	}
	
	public void btnGoLogin() {
		ls.newLoginView();
		
	}
	
	public void btnJoin() {
		if(js.CheckTxt()) {
			js.JoinInsert();
		}
		
	}

}
