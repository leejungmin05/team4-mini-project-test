package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Services.JoinService;
import Services.JoinServiceImpl;
import Services.LoginService;
import Services.LoginServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class LoginController implements Initializable{
	private Parent root;
	private LoginService ls;
	private JoinService js;
	
	
    public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ls = new LoginServiceImpl();
		js = new JoinServiceImpl();
	}
	
	
	public void btnLogin() {
		ls.Login(); // 로그인 버튼을 누를때 로그인서비스에서 로그인메소드가 실행됨 
	}
	
	public void btnJoin() {
		js.newWindowJoin();
	}


	

}
