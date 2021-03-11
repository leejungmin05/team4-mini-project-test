package DBService;

public class DBServiceImpl implements DBService{
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 에러날시 개인컴에서 사용하던 url을 이용해주세요
	private String id = ""; // 개인이 사용하시던 데베 아이디 이용해주세요
	private String pwd = ""; // 비밀번호도 마찬가지!
	
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		
	}

}
