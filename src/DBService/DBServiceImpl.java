package DBService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;
import Model.UserDTO;

public class DBServiceImpl implements DBService {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 에러날시 개인컴에서 사용하던 url을 이용해주세요
	private String id = "java"; // 개인이 사용하시던 데베 아이디 이용해주세요
	private String pwd = "1234"; // 비밀번호도 마찬가지!

	private UserDTO userdto = new UserDTO();
	private User user;
	
	
	
	
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
	public int selectId(String userId, String UserPwd) {
		DBBring(); // 고쳐야 하는 부분 매번 이렇게 가져올수는 없음(물론 중복처리된거는 업데이트 안되게함)
		// 아이디를 찾는 부분
		if (userdto.getuserFind().containsKey(userId)) {
			if (userdto.getuserFind().get(userId).getPwd().equals(UserPwd)) {
				Run.Run.setNOWUSERMONEY(userdto.getuserFind().get(userId).getMoney());
				System.out.println("로그인 성공");
				return 1;

			} else {
				System.out.println("비밀번호 틀림");
				return 0;
			}
		} else {
			System.out.println("아이디가 없음");
			return -1;
		}

	}

	@Override
	public int InsertDB(User user) {
		// 세미콜론이 알아서 붙게됨 , 또한 작은따옴표도 알아서붙음
		String sql = "insert into stUser values(?,?,?,?,?)";
		// insert into stUser values('hi','1234','sj',?,?)
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(url, id, pwd);
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
			return -1;
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

	@Override
	public void DBBring() {
		// "select * from stUser where userId = "+"'"+ id +"'"
		String sql = "select * from stUser ";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결완료");

			ps = con.prepareStatement(sql);

			// 새로 업데이트 해주는것 보다 처음 먼저 데베꺼를 가져와서 그냥 업데이트 해주고
			// 아이디 새로만들때마다 해주는게 맞는듯 매번 하면 좀 그렇긴 할듯

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				// user 데이터에 담기!
				user = new User();
				
				if (userdto.getuserFind().containsKey(rs.getString("userId"))) {
					 //System.out.println("로그 : 이미 있는 아이디 입니다. : ");
				} else {
					// 매번 새로 업데이트 해주는 거임
					user.setId(rs.getString("userId"));
					user.setPwd(rs.getString("userPwd"));
					user.setName(rs.getString("userName"));
					user.setEmail(rs.getString("userEmail"));
					user.setMoney(rs.getInt("userMoney"));
					
					
					
					userdto.setUserList(user);
					userdto.setuserFind(rs.getString("userId"), user); // 아이디를 키값 밸류값은 그대로 넣어주고 찾을때 여기서씀
					userdto.getUserList(); // ? 이건 머지? 일단 남겨놓음
				}
			}
			System.out.println("DB값을 가져오는데 성공했습니다");
			
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
		
		
	}

}
