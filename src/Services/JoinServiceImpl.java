package Services;

import java.io.IOException;

import Controllers.JoinController;
import DBService.DBService;
import DBService.DBServiceImpl;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class JoinServiceImpl implements JoinService {
	private TextField txtId;
	private TextField txtName;
	private TextField txtPwd;
	private TextField txtEmail;
	private Stage JoinStage ;
	private Parent root; // 요건 조인, 회원가입 root값 
	private Parent Lroot; // 이건 로그인의 root값 가져오기
	private Alert alert;
	private User user = new User();
	private DBService dbService = new DBServiceImpl();

	public JoinServiceImpl() {
		JoinStage = new Stage();
		root = null; // 패널 , 판때기, 전체적인 정보 전달을 위해서
		alert = new Alert(AlertType.INFORMATION);
	}
	
	public void setLoginRoot(Parent Lroot) {
		this.Lroot = Lroot;
	}
	
	
	public void setController(Parent root) {
		this.root = root; // 여기다가 루트값 받아와서 stage.close() 하기위해 받아온것
		txtId = (TextField) root.lookup("#txtId");
		txtName = (TextField) root.lookup("#txtName");
		txtPwd = (TextField) root.lookup("#txtPwd");
		txtEmail = (TextField) root.lookup("#txtEmail");
	}

	@Override
	public void newWindowJoin() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/JoinView.fxml"));
		Stage JoinStage = new Stage();
		 

		// 이미 컨트롤러가 실행돼서 이전에 그래서 루트가 로드하기전이기때문에 널값을 가짐
		try {
			root = loader.load();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(root);

		Scene scene = new Scene(root);
		JoinController joinController = loader.getController();
		joinController.setRoot(root);
		
		JoinStage.setScene(scene);
		JoinStage.show();
		
		// 로그인 창 닫는 부분
		Stage LoginStage = new Stage();
		LoginStage = (Stage)Lroot.getScene().getWindow();
		
		LoginStage.close();
	}

	@Override
	public int JoinInsert() {
		user = new User();
		
		// user = 1,2,3,4
		user.setId(txtId.getText());
		user.setPwd(txtPwd.getText());
		user.setName(txtName.getText());
		user.setEmail(txtEmail.getText());

		if(dbService.InsertDB(user)==1) {		
			JoinStage = (Stage)root.getScene().getWindow();
			alert.setContentText("회원가입이 완료되었습니다");
			alert.show();
			JoinStage.close();
		}else {
			alert.setContentText("회원가입실패");
			alert.show();
		}
		return 0;
	}

	@Override
	public boolean CheckTxt() {
		System.out.println(txtId.getText());
		if(txtId.getText().isEmpty() || txtPwd.getText().isEmpty() 
				 || txtName.getText().isEmpty()  || txtEmail.getText().isEmpty()) {
			alert.setContentText("모든정보를 기입해주세요");
			alert.show();
			return false;	
		}
		
		return true;
		
	}

}
