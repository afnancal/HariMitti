package com.afnan.harimitti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afnan.harimitti.model.Admin;
import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	// -------------------Retrieve All Admins-------------------------------
	@RequestMapping(value = "/adminList", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Admin> getListAdmin() {
		List<Admin> admins = adminService.getListAdmin();

		return admins;
	}

	// ------------Login a Admin, but login from Maintainer login url---------

	// -------------------Create a Admin------------------------------------
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public @ResponseBody ReturnMsg add(@RequestBody Admin admin) {

		return adminService.createAdmin(admin);
	}

	// ------------------- Update a Admin-----------------------------------
	@RequestMapping(value = "/updateAdmin/{admin_id}", method = RequestMethod.PUT)
	public @ResponseBody ReturnMsg update(@PathVariable("admin_id") String admin_id, @RequestBody Admin admin) {
		admin.setAdmin_id(admin_id);

		return adminService.updateAdmin(admin);
	}

	// ------------------- Delete a Admin---------------------------------
	@RequestMapping(value = "/deleteAdmin/{admin_id}", method = RequestMethod.DELETE)
	public @ResponseBody ReturnMsg delete(@PathVariable("admin_id") String admin_id) {

		return adminService.deleteAdmin(admin_id);
	}

}
