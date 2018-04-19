package com.afnan.harimitti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.model.User;
import com.afnan.harimitti.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	// -------------------Retrieve All Users-------------------------------
	@RequestMapping(value = "/userList", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<User> getListUser() {
		List<User> users = userService.getListUser();

		return users;
	}

	// -------------------Find Users by name------------------------------
	@RequestMapping(value = "/searchUser/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<User> search(@PathVariable("name") String name) {
		List<User> users = userService.findUserByName(name);

		return users;
	}

	// -------------------Login a User------------------------------------
	@RequestMapping(value = "/login/{contact_no}/{password}", method = RequestMethod.GET)
	public @ResponseBody Login login(@PathVariable("contact_no") String contact_no,
			@PathVariable("password") String password) {

		return userService.login(contact_no, password);
	}

	// -------------------Create a User------------------------------------
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody ReturnMsg add(@RequestBody User user) {

		return userService.createUser(user);
	}

	// -------------------validate a User is exist---------------------------------
	@RequestMapping(value = "/userExist/{contact_no}", method = RequestMethod.GET)
	public @ResponseBody ReturnMsg userExist(@PathVariable("contact_no") String contact_no) {

		return userService.userExist(contact_no);
	}

	// ------------------- Forget password of a User -----------------------
	@RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg updatePassword(@RequestBody User user) {

		return userService.updatePassword(user);
	}

	// ------------------- Update a User -----------------------------------
	@RequestMapping(value = "/update/{user_id}", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg update(@PathVariable("user_id") String user_id, @RequestBody User user) {
		user.setUser_id(user_id);

		return userService.updateUser(user);
	}

	// ------------------- Delete a User ---------------------------------
	@RequestMapping(value = "/delete/{user_id}", method = RequestMethod.DELETE)
	public @ResponseBody ReturnMsg delete(@PathVariable("user_id") String user_id) {

		return userService.deleteUser(user_id);
	}

}
