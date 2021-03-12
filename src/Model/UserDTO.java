package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDTO {
	private ArrayList<User> userList;
	private HashMap<String, User> userFind;

	
	public UserDTO() {
		userList = new ArrayList<User>();
		userFind = new HashMap<String, User>();
	}
	
	public void setuserFind(String key,User user) {
		userFind.put(key, user);
	}
	
	public HashMap<String, User> getuserFind(){
		return userFind;
	}
	
	
	public void setUserList(User user) {
		userList.add(user);
	}
	
	public ArrayList<User> getUserList() {
		return userList;
	}
	
	
	public int Search() {
		
		
		
		
		return 1 ; //  올바르게 찾았을때 Search
	}
	
	
}
