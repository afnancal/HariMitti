package com.afnan.harimitti.dao;

import java.util.List;

import com.afnan.harimitti.model.Admin;
import com.afnan.harimitti.model.ReturnMsg;

public interface AdminDao {

	public List<Admin> getListAdmin();

	public List<Admin> findAdminById(String admin_id);

	public List<Admin> findAdminByName(String name);

	// public Login loginAdmin(Admin admin);

	public ReturnMsg createAdmin(Admin admin);

	public ReturnMsg updateAdmin(Admin admin);

	public ReturnMsg deleteAdmin(String admin_id);

}
