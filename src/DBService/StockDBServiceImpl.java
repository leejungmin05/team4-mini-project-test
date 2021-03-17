package DBService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Stock;
import Model.StockDTO;
import Model.User;

public class StockDBServiceImpl implements StockDBService {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 에러날시 개인컴에서 사용하던 url을 이용해주세요
	private String id = "jm"; // 개인이 사용하시던 데베 아이디 이용해주세요
	private String pwd = "1234"; // 비밀번호도 마찬가지!
	private StockDTO stockdto = new StockDTO(); // 여기가 사실 저장한거니깐 여기서 받아오자

	public StockDBServiceImpl() {
		StockInsert(); // 값 가져오려면 무조건 브링 시켜야지
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
	public int StockInsert() {
		// "select * from stUser where userId = "+"'"+ id +"'"
		String sql = "select * from Stock ";
		Stock stock;
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
				stock = new Stock();
				// 매번 새로 업데이트 해주는 거임
				stock.setStockName(rs.getString("StockName"));
				stock.setStockPrice(rs.getInt("StockPrice"));
				stock.setPreStockPrice(rs.getInt("preStockPrice"));
				stock.setPrepreStockPrice(rs.getInt("prepreStockPrice"));

				
				stockdto.setStockList(rs.getString("StockName"), stock); // 아이디를 키값 밸류값은 그대로 넣어주고 찾을때 여기서씀
				stockdto.setArrStoList(stock); // 잘 저장될듯 ㅎㅎ
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

		return 1;
	}

}
