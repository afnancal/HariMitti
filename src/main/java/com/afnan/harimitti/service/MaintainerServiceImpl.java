package com.afnan.harimitti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afnan.harimitti.dao.MaintainerDao;
import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.Maintainer;
import com.afnan.harimitti.model.ReturnMsg;

@Service
@Transactional
public class MaintainerServiceImpl implements MaintainerService {

	MaintainerDao maintainerDao;

	@Autowired
	public void setMaintainerDao(MaintainerDao maintainerDao) {
		this.maintainerDao = maintainerDao;
	}

	@Override
	public List<Maintainer> getListMaintainer() {
		// TODO Auto-generated method stub
		return maintainerDao.getListMaintainer();
	}

	@Override
	public List<Maintainer> findMaintainerByName(String name) {
		// TODO Auto-generated method stub
		return maintainerDao.findMaintainerByName(name);
	}

	@Override
	public Login loginMaintainer(Maintainer maintainer) {
		// TODO Auto-generated method stub
		return maintainerDao.loginMaintainer(maintainer);
	}

	@Override
	public ReturnMsg createMaintainer(Maintainer maintainer) {
		// TODO Auto-generated method stub
		return maintainerDao.createMaintainer(maintainer);
	}

	@Override
	public ReturnMsg maintainerExist(String contact_no) {
		// TODO Auto-generated method stub
		return maintainerDao.maintainerExist(contact_no);
	}

	@Override
	public ReturnMsg updateMaintainerPassword(Maintainer maintainer) {
		// TODO Auto-generated method stub
		return maintainerDao.updateMaintainerPassword(maintainer);
	}

	@Override
	public ReturnMsg updateMaintainer(Maintainer maintainer) {
		// TODO Auto-generated method stub
		return maintainerDao.updateMaintainer(maintainer);
	}

	@Override
	public ReturnMsg deleteMaintainer(String maintainer_id) {
		// TODO Auto-generated method stub
		return maintainerDao.deleteMaintainer(maintainer_id);
	}

}
