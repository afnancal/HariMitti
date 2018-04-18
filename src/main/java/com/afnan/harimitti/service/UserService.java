package com.afnan.harimitti.service;

import java.util.List;

import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.model.User;

public interface UserService {

	public List<User> getListUser();

	public ReturnMsg createUser(User user);
	
	public ReturnMsg updateUser(User user);

	public ReturnMsg deleteUser(String user_id);

	public User findUserById(int id);

}
