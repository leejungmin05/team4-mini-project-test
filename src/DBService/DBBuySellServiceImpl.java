package DBService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Stock;
import Model.StockUser;
import Model.StockUserDTO;

public class DBBuySellServiceImpl implements DBBuySellService{
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 에러날시 개인컴에서 사용하던 url을 이용해주세요
	private String id = "java"; // 개인이 사용하시던 데베 아이디 이용해주세요
	private String pwd = "1234"; // 비밀번호도 마찬가지!
	
	private StockUserDTO stDTO = new StockUserDTO(); //여기에 아이디, 산주식, 주식갯수를 들어가고 저장함
	private StockUser stockUser;
	private String NowUser = Run.Run.getNOWUSER();
	private int NowUserMoney;
	
	public DBBuySellServiceImpl() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("드라이브 로드 되었습니다");
	}
	
	public int getNowUserMoney() {
		return NowUserMoney;
		
	}
	
	
	public StockUserDTO getstDTO() {
		UpdateDTO();
		return stDTO;
	}
	
	
	
	// 아이디 값을 받아와서 만약에 산게 있다면 수정
	// 그냥 아이디값만 받는게아닌 특수 아이디(내가 만들어놓은거)로 체크해야함 
	public int aleadyCherk(String id) {
		if( stDTO.getstockUserFind().containsKey(id)) {
			return 1;
		}
		
		return -1;
	}
	// 몇개 살껀지 아는 매개변수 인트형 buyStackNumber , 그리고 뭐살지 정해주는거
	// 사는게 트루
	public int ConcurStock(String StockName, int buyStackNumber, boolean buysellChoice) {
		String userMsql = "SELECT userMoney from stUser WHERE userId ="+"'"+NowUser+"'"; // 현재 유저의 돈
		String StockNsql = "SELECT StockPrice from Stock WHERE StockName = "+"'"+StockName+"'"; // 주식의돈
		Connection con1 = null;
		Connection con2 = null;
		int Number = buyStackNumber; // 몇개살껀지 받는거
		int userHaveMoney =0;
		int price =0;
		int TotlaPrice=0;
		boolean buysell = buysellChoice;
		int buySellTotlaMoney =0;
		int sellTotlaMoney =0;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		
		try {
			con1 = DriverManager.getConnection(url, id, pwd);
			con2 = DriverManager.getConnection(url, id, pwd);
			ps1 = con1.prepareStatement(userMsql);
			ps2 = con2.prepareStatement(StockNsql);
			
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			
			// 여기 유저머니를 빼돌리면 될듯
			while (rs1.next()) {
				userHaveMoney= rs1.getInt("userMoney");
				
			}
			while(rs2.next()) {
				price = rs2.getInt("StockPrice");
			}
			
			//-가 안나오게 해야함
			TotlaPrice =price * Number;
			System.out.println("사겠다는 주식가격: "+TotlaPrice);
			
			
			
			System.out.println("계산결과: "+(userHaveMoney - TotlaPrice));
			System.out.println("계산결과: "+(userHaveMoney + TotlaPrice));
			
			if(buysell) {
				buySellTotlaMoney = userHaveMoney - TotlaPrice;
				
				
				
				
				// 살때니깐 마이너스
			}else {
				buySellTotlaMoney = userHaveMoney + TotlaPrice;
				// 팔때니깐 플러스 
			}
			
			
			//사면 일단 유저 테이블의  유저 머니 변경, 그리고 가지고있는 주식량 증가(유저이름, 주식이름 찾아서), 
			// aleadyCherk로 체크 한후 아예 안산게 없다면 InsertBuy 여기서 발동
			
			
			if(aleadyCherk(NowUser+","+StockName) == 1) {
				// 이미 산 게 있다는 뜻이니깐 패스
				if((buySellTotlaMoney) < 0 ) {
					System.out.println("돈이없습니다");
				}else {
					UpdateBuy(Number, buySellTotlaMoney, StockName,buysell);
					Run.Run.setNOWUSERMONEY(buySellTotlaMoney);
				}
			}else {
				if((buySellTotlaMoney) < 0 ) {
					System.out.println("돈이없습니다");
				}else {
					InsertBuy(StockName ,buyStackNumber, buySellTotlaMoney);
					Run.Run.setNOWUSERMONEY(buySellTotlaMoney);
					
				}
				 
			}
			
			
		}catch(Exception e) {
			
		}finally {
			if(ps1 != null && con1 != null || ps2 != null && con2 != null ) {
				try {
					ps1.close();
					ps2.close();
					con1.close();				
					con2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		this.NowUserMoney =(buySellTotlaMoney);
		return 1;
	}
	
	
	// 그러니깐 완전히 아예 안산 주식이니깐 데베에 없으니까 그냥 인설트 하면됨, leftMoney는 남은돈을 매개변수로 받는거임
	public int InsertBuy(String Stock,int stockNumber, int leftMoney) {
		String sql1 = "insert into StockUser values("+"'"+NowUser+"'" +","+"'"+Stock+"'"+","+"'"+stockNumber+"'"+")";
		String sql2 = "update stUser set userMoney ="+ "'"+leftMoney+"'"+"where USERID ="+"'"+NowUser+"'";
		
		Connection con1 = null;
		PreparedStatement ps1 = null;
		
		Connection con2 = null;
		PreparedStatement ps2 = null;
		
		
		try {
			con1 = DriverManager.getConnection(url, id, pwd);
			con2 = DriverManager.getConnection(url, id, pwd);
			ps1 = con1.prepareStatement(sql1);
			ps2 = con1.prepareStatement(sql2);
			
			ps1.executeQuery();
			ps2.executeQuery();
			
		}catch(Exception e) {
			
		}finally {
			if(ps1 != null && con1 != null && ps2 != null && con2 != null) {
				try {
					ps1.close();
					ps2.close();
					con1.close();
					con2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return 1;
	}
	
	//몇개를 살껀지 -> stockNumber, 어떤 주식인지 -> stockN
	public int UpdateBuy(int stockNumber, int leftMoney, String stockN,boolean buysellB) {
		UpdateDTO(); // 업데이트를 해서 먼저 받아온다
		String sql2 = "update stUser set userMoney ="+ "'"+leftMoney+"'"+"where USERID ="+"'"+NowUser+"'"; //남은돈 계산
		int TotalPrice = 0;
		boolean bysellCh = buysellB;
		int preMoney = stDTO.getstockUserFind().get(NowUser+","+stockN).getSaveStockNumber(); // 이미 사놓은 주식임
		if(bysellCh) {
			TotalPrice = stockNumber + preMoney; // 살주식과 원래있는 주식을 더해
		}else {
			TotalPrice = preMoney-stockNumber ; // 
			
			if(TotalPrice < 0) {
				System.out.println("부족합니다 가지고있던 주식이");
				return -1; // 부족하다는 뜻
			
			}
			
			
		}
		// 계속 팔수있게해버려서 일단 이거 수정요망
		
		
		//System.out.println(preMoney);
		//System.out.println("합친 주식량 : "+TotalPrice);
		String sql1 = "update StockUser set SAVESTOCKNUMBER ="+ "'"+TotalPrice+"'" +"where USERID ="+ "'"+NowUser+"'" +" and STOCKNAME = "+ "'"+stockN+"'" ;
		
		Connection con1 = null;
		PreparedStatement ps1 = null;
		
		Connection con2 = null;
		PreparedStatement ps2 = null;
		
		
		try {
			con1 = DriverManager.getConnection(url, id, pwd);
			con2 = DriverManager.getConnection(url, id, pwd);
			ps1 = con1.prepareStatement(sql1);
			ps2 = con1.prepareStatement(sql2);
			
			ps1.executeQuery();
			ps2.executeQuery();
			
		}catch(Exception e) {
			
		}finally {
			if(ps1 != null && con1 != null && ps2 != null && con2 != null) {
				try {
					ps1.close();
					ps2.close();
					con1.close();
					con2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	
		
		return 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int Buy() {
		UpdateDTO(); // 계속 업데이트가 필요
		
		
		
		
		
		
		
		return 0;
	}

	@Override
	public int Sell() {
		UpdateDTO(); // 계속 업데이트가 필요
		
		
		
		
		return 0;
	}

	public void UpdateDTO() {
		String sql = "select * from StockUser ";
		Stock stock;
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try {
			con = DriverManager.getConnection(url, id, pwd);
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				stockUser = new StockUser();
				
				stockUser.setStockUserFK(rs.getString("userId"), rs.getString("StockName"));// 아이디랑 스톡이름이랑 , 이걸로묶음
				stockUser.setUserId(rs.getString("userId"));
				stockUser.setStockName(rs.getString("StockName"));
				stockUser.setSaveStockNumber(rs.getInt("SaveStockNumber"));
				
				
				
				stDTO.setStockUserFind(stockUser.getStockUserFK(), stockUser);
				stDTO.setStockUserList(stockUser);
				
				// 동작확인
				//System.out.println(stockUser.getStockUserFK());
				//System.out.println(stDTO.getstockUserFind().get(stockUser.getStockUserFK()).getSaveStockNumber());
				
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(ps != null && con != null) {
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		
		
		
	}
	
	
}
