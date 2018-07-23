package com.afnan.harimitti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afnan.harimitti.dao.MaintainerLocationDao;
import com.afnan.harimitti.model.MaintainerLocation;
import com.afnan.harimitti.model.ReturnMsg;

@Service
@Transactional
public class MaintainerLocationServiceImpl implements MaintainerLocationService {

	MaintainerLocationDao maintainerLocationDao;

	@Autowired
	public void setMaintainerLocationDao(MaintainerLocationDao maintainerLocationDao) {
		this.maintainerLocationDao = maintainerLocationDao;
	}

	@Override
	public List<MaintainerLocation> findMaintainerLocationById(String maintainer_id) {
		// TODO Auto-generated method stub
		return maintainerLocationDao.findMaintainerLocationById(maintainer_id);
	}

	@Override
	public ReturnMsg createMaintainerLocation(MaintainerLocation maintainerLocation) {
		// TODO Auto-generated method stub
		return maintainerLocationDao.createMaintainerLocation(maintainerLocation);
	}

	@Override
	public ReturnMsg maintainerLocationExist(String maintainer_id) {
		// TODO Auto-generated method stub
		return maintainerLocationDao.maintainerLocationExist(maintainer_id);
	}

	@Override
	public ReturnMsg updateMaintainerLocation(String date, MaintainerLocation maintainerLocation) {
		// TODO Auto-generated method stub
		return maintainerLocationDao.updateMaintainerLocation(date, maintainerLocation);
	}

	@Override
	public ReturnMsg deleteMaintainerLocation(String maintainer_id) {
		// TODO Auto-generated method stub
		return maintainerLocationDao.deleteMaintainerLocation(maintainer_id);
	}

}
