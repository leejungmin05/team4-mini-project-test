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

	@Override
	public int change(String chStockName, int chMoney) {
		String sql = "UPDATE Stock SET StockPrice = " + "'" + chMoney + "'" 
	                         + "WHERE StockName ="+"'"+chStockName+"'";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결완료");

			ps = con.prepareStatement(sql);

			// 새로 업데이트 해주는것 보다 처음 먼저 데베꺼를 가져와서 그냥 업데이트 해주고
			// 아이디 새로만들때마다 해주는게 맞는듯 매번 하면 좀 그렇긴 할듯

			ps.executeQuery();
			
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
