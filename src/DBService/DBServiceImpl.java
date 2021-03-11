package DBService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.User;

public class DBServiceImpl implements DBService{
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 에러날시 개인컴에서 사용하던 url을 이용해주세요
	private String id = "java"; // 개인이 사용하시던 데베 아이디 이용해주세요
	private String pwd = "1234"; // 비밀번호도 마찬가지!
	
	
	public DBServiceImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("드라이브 로드 되었습니다");
	}
	
	

	@Override
	public void insert() {
		
		
	}

	@Override
	public void select() {
		
		
	}



	@Override
	public void InsertDB(User user) {
		// 무슨차이인지 알수가 없네 일단 에러 발견했는데 뭐가 문제인지도 모른채 해결함 
		String sql = "insert into stUser values(?,?,?,?,?)";
		Connection con=null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(url,id,pwd);
			ps = con.prepareStatement(sql);
			
			
			ps.setString(1, user.getId());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getName());
			ps.setString(4, user.getEmail());
			ps.setInt(5, user.getMoney());
			
			ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)ps.close();
				if(con != null) con.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}




}
