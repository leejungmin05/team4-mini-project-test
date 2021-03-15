package DBService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;

public class ChangeStockImpl implements ChangeStock {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 에러날시 개인컴에서 사용하던 url을 이용해주세요
	private String id = "java"; // 개인이 사용하시던 데베 아이디 이용해주세요
	private String pwd = "1234"; // 비밀번호도 마찬가지!

	public ChangeStockImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("드라이브 로드 되었습니다");
	}

	public void prepreChange(String chStockName) {
		String sql1 = "SELECT preStockPrice from Stock WHERE StockName =" + "'" + chStockName + "'";
		Connection con1 = null;
		Connection con2 = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			con1 = DriverManager.getConnection(url, id, pwd);
			con2 = DriverManager.getConnection(url, id, pwd);
			// System.out.println("연결완료");

			ps1 = con1.prepareStatement(sql1);

			ResultSet result = ps1.executeQuery();
			
			// 값을 무조건 다써야하나봄
			while (result.next()) {
				int preStockPrice = result.getInt("preStockPrice");
				String sql2 = "UPDATE Stock SET prepreStockPrice = " + "'" + preStockPrice + "'" + "WHERE StockName =" + "'"
						+ chStockName + "'";
				ps2 = con2.prepareStatement(sql2);

				ps2.executeQuery();
				
			}

			

		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps1 != null)
					ps1.close();
				if (ps2 != null)
					ps2.close();
				if (con1 != null)
					con1.close();
				if (con2 != null)
					con2.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	public void preChange(String chStockName) {
		String sql1 = "SELECT StockPrice from Stock WHERE StockName =" + "'" + chStockName + "'";
		Connection con1 = null;
		Connection con2 = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			con1 = DriverManager.getConnection(url, id, pwd);
			con2 = DriverManager.getConnection(url, id, pwd);
			// System.out.println("연결완료");

			ps1 = con1.prepareStatement(sql1);

			ResultSet result = ps1.executeQuery();
			
			// 값을 무조건 다써야하나봄
			while (result.next()) {
				int StockPrice = result.getInt("StockPrice");
				String sql2 = "UPDATE Stock SET preStockPrice = " + "'" + StockPrice + "'" + "WHERE StockName =" + "'"
						+ chStockName + "'";
				ps2 = con2.prepareStatement(sql2);

				ps2.executeQuery();
				
			}

			

		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps1 != null)
					ps1.close();
				if (ps2 != null)
					ps2.close();
				if (con1 != null)
					con1.close();
				if (con2 != null)
					con2.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public int change(String chStockName, int chMoney) {
		String sql = "UPDATE Stock SET StockPrice = " + "'" + chMoney + "'" + "WHERE StockName =" + "'" + chStockName
				+ "'";

		String sql1 = "SELECT StockPrice from Stock WHERE StockName =" + "'" + chStockName + "'";

		Connection con = null;

		PreparedStatement ps = null;


		try {

			con = DriverManager.getConnection(url, id, pwd);

			// System.out.println("연결완료");

			ps = con.prepareStatement(sql);


			ps.executeQuery();


			

		

			return 1;

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

		return 0;
	}

}
