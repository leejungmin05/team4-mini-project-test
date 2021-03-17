package Model;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;

public class DisplayTableData{
	public TableColumn[] getColumns() {
		final TableColumn<tableView, String> stockName = new TableColumn<>("stockName"); 
		stockName.setCellValueFactory(item -> item.getValue().getStockName()); 
		stockName.setPrefWidth(40);

		final TableColumn<tableView, String> stockPrice = new TableColumn<>("stockPrice"); 
		stockPrice.setCellValueFactory(item -> item.getValue().getStockPrice()); 
		stockPrice.setPrefWidth(20);
		
		final TableColumn<tableView, String> changePrice = new TableColumn<>("changePrice"); 
		changePrice.setCellValueFactory(item -> item.getValue().getChangePrice()); 
		changePrice.setPrefWidth(40);
		
	}

}
