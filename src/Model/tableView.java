package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class tableView {
	private StringProperty stockName;
	private IntegerProperty stockPrice;
	private IntegerProperty changePrice;
	
	
	  public tableView(StringProperty stockName, IntegerProperty stockPrice, IntegerProperty changePrice) {
	        this.stockName = stockName;
	        this.stockPrice = stockPrice;
	        this.changePrice = changePrice;
	    }

	  public tableView(String stockName, Integer stockPrice,Integer changePrice ) {
		  this.stockName.set(stockName);
		  this.stockPrice.set(stockPrice);
		  this.changePrice.set(changePrice);
	  }

	public StringProperty getStockName() {
		return stockName;
	}
	public IntegerProperty getStockPrice() {
		return stockPrice;
	}
	
	public IntegerProperty getChangePrice() {
		return changePrice;
	}


	
	  
	
}