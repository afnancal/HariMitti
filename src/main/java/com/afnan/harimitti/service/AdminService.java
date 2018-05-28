package com.afnan.harimitti.service;

import java.util.List;

import com.afnan.harimitti.model.Admin;
import com.afnan.harimitti.model.ReturnMsg;

public interface AdminService {

	public List<Admin> getListAdmin();

	// public Login loginAdmin(Admin admin);

	public ReturnMsg createAdmin(Admin admin);

	public ReturnMsg updateAdmin(Admin admin);

	public ReturnMsg deleteAdmin(String admin_id);

}
