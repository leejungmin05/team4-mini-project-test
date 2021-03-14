package Services;

import javafx.scene.Parent;
//메인 스택 서비스 (메인 띄어주는 것 , 그럼 창하나를 더 만들어야 할듯)

public interface MainStockService {
	public void newWindowMainStock(); // 주식창을 새로 띄어주는 메소드
	public void setRoot(Parent root); // 루트값 가져오기 , // 창을 두개 만들어야 협업 할때 일이 안겹침
}
