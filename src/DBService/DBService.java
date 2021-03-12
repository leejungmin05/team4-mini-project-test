package DBService;

import Model.User;

// 패키지를 비워놓으면 안떠서 임시로 넣은 인터페이스 입니다. 
// 사용하셔도 괜찮은데 쓰실때 말씀해주세요 안겹치게 하려고요! 

// table 구조 (id, pwd, name , email , money  ) //필요하면 더 추가예정


// 1번 일단 데베에 저장된 값이 하나라도 있는지 없는지를 확인 ,  있으면 바로 리스트에 저장(DTO에 저장)
// 2번 회원가입 성공시 또 DTO에 저장해줘야함


public interface DBService {
	public void insert(); // DB에 값을 넣을 때 사용한다.
	public int selectId(String userId,String UserPwd);  // 값을 조회할때 사용한다.
	public int InsertDB(User user);
	public void DBBring(); //db에있는 값들을 다 불러온다
}
