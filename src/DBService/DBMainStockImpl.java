package DBService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.net.httpserver.Authenticator.Result;

import Model.Stock;
import Model.StockDTO;
import Model.tableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBMainStockImpl implements DBMainStock {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
	private String id = "jm"; 
	private String pwd = "1234"; 
	private StockDTO stockdto = new StockDTO();
	
	public DBMainStockImpl() {
			DBLoad();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("드라이브 로드 되었습니다");
		}

	public StockDTO getStockList() {
		return stockdto;
	}

	@Override
	public int DBLoad() {
		String sql = "select * from StockDTO ";
		tableView tableView;
		Connection con = null;
		PreparedStatement ps = null;

		
		try {
				con = DriverManager.getConnection(url, id, pwd);
				ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
					
				}
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return 1;
	}		
	}
	
	

