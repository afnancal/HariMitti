package com.afnan.harimitti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afnan.harimitti.dao.UserDao;
import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getListUser() {
		return userDao.getListUser();
	}

	public List<User> findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	public Login login(String contact_no, String password) {
		return userDao.login(contact_no, password);
	}

	public ReturnMsg createUser(User user) {
		return userDao.createUser(user);
	}

	public ReturnMsg userExist(String contact_no) {
		return userDao.userExist(contact_no);
	}

	public ReturnMsg updatePassword(User user) {
		return userDao.updatePassword(user);
	}

	public ReturnMsg updateUser(User user) {
		return userDao.updateUser(user);
	}

	public ReturnMsg deleteUser(String user_id) {
		return userDao.deleteUser(user_id);
	}

}
