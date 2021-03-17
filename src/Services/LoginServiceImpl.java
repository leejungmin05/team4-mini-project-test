package Services;

import java.io.IOException;

import Controllers.BuySellController;
import Controllers.LoginController;
import DBService.DBService;
import DBService.DBServiceImpl;
import DBService.StockDBService;
import Model.NowUser;
import Run.Run;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginServiceImpl implements LoginService {
	private TextField txtId;
	private TextField txtPwd;
	private Parent Jroot;
	private Parent root;
	private Stage LoginStage;
	private DBService dbservice;
	private MainStockService ms;
	

	public void setRoot(Parent root) {
		
	}
	
	public void setJoinRoot(Parent Jroot) {
		this.Jroot = Jroot;
	}
	
	
	public void newLoginView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/LoginView.fxml"));
		LoginStage = new Stage();
		root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);

		// 컨드롤러를 넣어주면 (fxid나 fxml에 있는 값들을 받아올수있어서 해놓은거임)
		LoginController loginController = loader.getController();
		loginController.setRoot(root); // 여기가 값 받아다 전달하는 부분 여기가 제일먼저긴 함

		LoginStage.setScene(scene);
		LoginStage.show();

		// 회원가입창 창 닫는 부분
		Stage JoinStage = new Stage();
		JoinStage = (Stage) Jroot.getScene().getWindow();

		JoinStage.close();

	}

	public LoginServiceImpl() {
		dbservice = new DBServiceImpl();
		ms = new MainStockServiceImpl();

	}

	@Override
	public void setController(Parent root) {
		this.root = root;
		txtId = (TextField) root.lookup("#txtId");
		txtPwd = (TextField) root.lookup("#txtPwd");
	}

	@Override
	public void Login() {
		Alert alert = new Alert(AlertType.INFORMATION);
		if (dbservice.selectId(txtId.getText(), txtPwd.getText()) == 1) {
			Run.setNOWUSER(txtId.getText()); // 스태틱 유저 받는것
			LoginStage = (Stage) root.getScene().getWindow();
			LoginStage.close();
			ms.newWindowMainStock();
		} else if (dbservice.selectId(txtId.getText(), txtPwd.getText()) == -1) {
			alert.setContentText("아이디가 틀렸습니다");
			alert.show();
			// 아이디가 없음
		} else {
			alert.setContentText("비밀번호가 틀렸습니다");
			alert.show();
			// 비번틀림
		}

	}

}
