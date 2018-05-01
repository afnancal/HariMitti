package com.afnan.harimitti.service;

import java.util.List;

import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.model.User;

public interface UserService {

	public List<User> getListUser();
	
	public List<User> findUserByName(String name);

	public Login login(String contact_no, String password);
	
	public ReturnMsg createUser(User user);
	
	public ReturnMsg userExist(String contact_no);
	
	public ReturnMsg updatePassword(User user);
	
	public ReturnMsg updateUser(User user);

	public ReturnMsg deleteUser(String user_id);

}
