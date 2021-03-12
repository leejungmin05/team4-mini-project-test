package Services;

import javafx.scene.Parent;

// LoginView , LoginController 두개와 연관있는 로직을 처리하는 클래스 입니다.
// 인터페이스에 무슨기능을하는지 잘적어 주세요!

public interface LoginService {
	public void Login(); // 로그인 사용할때 쓸거같은 메소드, 1일때 올바른 로그인
	public void setController(Parent root); //루트값 컨트롤러에서 받아오기
	public void newLoginView();
	public void setJoinRoot(Parent Jroot); // 회원가입창 닫기위해 join의 루트값 받아오는 메소드 
	public void setRoot(Parent root); // 자기 루트 받아오는거
	
	
	
	
	
}
