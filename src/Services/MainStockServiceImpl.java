package Services;

import java.io.IOException;

import Controllers.MainStockController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStockServiceImpl implements MainStockService{

	@Override
	public void newWindowMainStock() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainStockView.fxml"));
		Stage JoinStage = new Stage();
		Parent root = null ; 
		
		
		try {
			root = loader.load();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(root);

		Scene scene = new Scene(root);
		MainStockController mainStockController = loader.getController();
		mainStockController.setRoot(root);
		JoinStage.setScene(scene);
		JoinStage.show();
		
	}

}
