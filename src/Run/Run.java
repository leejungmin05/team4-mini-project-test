package Run;

import Controllers.LoginController;
import Services.MainStackSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Run extends Application{
	static String NOWUSER ;
	static int NOWUSERMONEY;
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/LoginView.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		//컨드롤러를 넣어주면 (fxid나 fxml에 있는 값들을 받아올수있어서 해놓은거임)
		LoginController loginController = loader.getController();
		loginController.setRoot(root); // 여기가 값 받아다 전달하는 부분 여기가 제일먼저긴 함
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static String getNOWUSER() {
		return NOWUSER;
	}

	public static void setNOWUSER(String nOWUSER) {
		NOWUSER = nOWUSER;
	}

	public static int getNOWUSERMONEY() {
		return NOWUSERMONEY;
	}

	public static void setNOWUSERMONEY(int nOWUSERMONEY) {
		NOWUSERMONEY = nOWUSERMONEY;
	}

	public static void main(String[] args) {
		MainStackSystem mss = new MainStackSystem();
		mss.start(); //  아니 이거 어케끄는지는 나중에 생각
		launch();
		
	}

}
