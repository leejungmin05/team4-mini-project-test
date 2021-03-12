package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Services.JoinService;
import Services.JoinServiceImpl;
import Services.LoginService;
import Services.LoginServiceImpl;
import Services.MainStockService;
import Services.MainStockServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable {
	private Parent root;
	private LoginService ls;
	private JoinService js;

	public void setRoot(Parent root) {
		this.root = root;
		ls.setController(root);
		js.setLoginRoot(root); // 여기가 무조건적으로 먼저니깐 받아오려면 여기
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ls = new LoginServiceImpl();
		js = new JoinServiceImpl();
		

	}

	public void btnLogin() {
		ls.Login(); // 로그인 버튼을 누를때 로그인서비스에서 로그인메소드가 실행됨
		// 이건 이제 로그인 성공시 출력해야함
		

	}

	public void btnJoin() {
		js.newWindowJoin();
	}

}
