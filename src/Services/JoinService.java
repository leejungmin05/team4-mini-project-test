package Services;

import Model.User;
import javafx.scene.Parent;

//회원가입 서비스

public interface JoinService {
	public void newWindowJoin(); // 회원가입창을 새로 띄어주는 메소드
	public void setController(Parent root); // 해당 서비스에 root값을 가져오는 프로그램 
	public void setLoginRoot(Parent Lroot); // 남의 꺼 루트 가져오는애 (로그인의 루트 )
	public int JoinInsert(); // 데베에 insert하는거임
	public boolean CheckTxt(); // 회원가입 텍스트 필드중 비어있는곳이 있는지 없는지 체크
}
