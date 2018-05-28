package com.afnan.harimitti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afnan.harimitti.dao.AdminDao;
import com.afnan.harimitti.model.Admin;
import com.afnan.harimitti.model.ReturnMsg;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	AdminDao adminDao;

	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public List<Admin> getListAdmin() {
		// TODO Auto-generated method stub
		return adminDao.getListAdmin();
	}

	@Override
	public ReturnMsg createAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.createAdmin(admin);
	}

	@Override
	public ReturnMsg updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.updateAdmin(admin);
	}

	@Override
	public ReturnMsg deleteAdmin(String admin_id) {
		// TODO Auto-generated method stub
		return adminDao.deleteAdmin(admin_id);
	}

}
