package Services;

import java.io.IOException;

import Controllers.JoinController;
import DBService.DBService;
import DBService.DBServiceImpl;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JoinServiceImpl implements JoinService {
	private TextField txtId;
	private TextField txtName;
	private TextField txtPwd;
	private TextField txtEmail;
	
	private User user = new User();
	private DBService dbService = new DBServiceImpl();

	public void setController(Parent root) {
		txtId = (TextField) root.lookup("#txtId");
		txtName = (TextField) root.lookup("#txtName");
		txtPwd = (TextField) root.lookup("#txtPwd");
		txtEmail = (TextField) root.lookup("#txtEmail");
	}

	@Override
	public void newWindowJoin() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/JoinView.fxml"));
		Stage JoinStage = new Stage();
		Parent root = null; // 패널 , 판때기, 전체적인 정보 전달을 위해서

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
	}

	@Override
	public int JoinInsert() {
		user = new User();

		// user = 1,2,3,4
		user.setId(txtId.getText());
		user.setPwd(txtPwd.getText());
		user.setName(txtName.getText());
		user.setEmail(txtEmail.getText());

		dbService.InsertDB(user);
		return 0;
	}

}
