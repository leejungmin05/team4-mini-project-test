package Services;

import java.io.IOException;

import Controllers.BuySellController;
import Controllers.MainStockController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuySellServiceImpl implements BuySellService{
	private Parent root;
	
	@Override
	public void setRoot(Parent root) {
		this.root =root;
		
	}

	@Override
	public void newWindowBuySell() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/BuySellView.fxml"));
		Stage JoinStage = new Stage();
		Parent root = null;

		try {
			root = loader.load();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(root);

		Scene scene = new Scene(root);
		BuySellController buySellController = loader.getController();
		buySellController.setRoot(root);
		JoinStage.setScene(scene);
		JoinStage.show();

		
	}

}
