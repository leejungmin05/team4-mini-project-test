package Services;

import Model.User;
import javafx.scene.Parent;

//회원가입 서비스

public interface JoinService {
	public void newWindowJoin(); // 회원가입창을 새로 띄어주는 메소드
	public void setController(Parent root); // 해당 서비스에 root값을 가져오는 프로그램 
	
	public int JoinInsert(); // 데베에 insert하는거임
}
