package Services;
//여기서 난수 실행 계속 실행시키는 중요한 요소임
// 스레드 하나더 발생시키도록 하자 여기 스레드로 계속 랜덤 변수로 데베값을 넣었다 뺏다 하는거임

import java.util.ArrayList;
import java.util.Random;

import DBService.ChangeStock;
import DBService.ChangeStockImpl;
import DBService.StockDBService;
import DBService.StockDBServiceImpl;

public class MainStackSystem extends Thread{
	private int i;
	private Random random;
	private double randomPrice;
	private double randomStackName;
	private StockDBService SDBS; // 여기 디비저장후 바로 여기에 값들을 다 저장하니깐 이걸 가져와야함
	private ChangeStock chst;
	public MainStackSystem() {
		SDBS = new StockDBServiceImpl();
		random = new Random();
		chst = new ChangeStockImpl();
	}
	
	public void run() {
		this.i =0;
		  try
	        {
			  // 이름만 저장한 리스트를 만든다.
			  // 순서를 랜덤으로 해서 값을 출력한다 즉 인덱스 1 -> "샘성" 이런식으로 
			  
			  
			 
			  
	            while(true)
	            {
	                // 스레드 1초동안 대기
	                Thread.sleep(1000);
	               
	                
	                System.out.println("주식 변동프로그램  작동중 : " + i);
	                randomPrice = Math.random();
	                randomStackName = Math.random();
	                int intPrice = (int )(randomPrice * 35000)+ 27000;
	                // 0부터 리스트 사이즈까지 랜덤으로
	                int indexStackName = (int )(randomPrice * SDBS.getStockList().getArrStoList().size())+ 0; // 이거수정
	                // 여기에 전달해서 그값을 변동시키면 될듯 
	                // 근데 미리 받아놓은 STACKDTO가 있으니깐 거기 리스트 밸류값도 같이 받아야할듯
	                
	                
	                System.out.println( SDBS.getStockList().getArrStoList().get(indexStackName).getStockName());
	                System.out.println(intPrice);
	                chst.change(SDBS.getStockList().getArrStoList().get(indexStackName).getStockName(), intPrice);
	                i++;
	            }
	        }catch(InterruptedException e)
	        {
	            System.out.println(e);
	        }
	}
	
	
	
}
