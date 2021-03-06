package com.afnan.harimitti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afnan.harimitti.dao.MaintainerAllotmentDao;
import com.afnan.harimitti.model.MaintainerAllotment;
import com.afnan.harimitti.model.ReturnMsg;

@Service
@Transactional
public class MaintainerAllotmentServiceImpl implements MaintainerAllotmentService {

	MaintainerAllotmentDao maintainerAllotmentDao;

	@Autowired
	public void setMaintainerAllotmentDao(MaintainerAllotmentDao maintainerAllotmentDao) {
		this.maintainerAllotmentDao = maintainerAllotmentDao;
	}

	@Override
	public List<MaintainerAllotment> getListMaintainerAllotment() {
		// TODO Auto-generated method stub
		return maintainerAllotmentDao.getListMaintainerAllotment();
	}

	@Override
	public List<MaintainerAllotment> findMaintainerAllotmentByMainId(String maintainer_id, String dateFrom,
			String dateTo) {
		// TODO Auto-generated method stub
		return maintainerAllotmentDao.findMaintainerAllotmentByMainId(maintainer_id, dateFrom, dateTo);
	}

	@Override
	public List<MaintainerAllotment> findTodaysMainAllotByMainId(String maintainer_id, String date) {
		// TODO Auto-generated method stub
		return maintainerAllotmentDao.findTodaysMainAllotByMainId(maintainer_id, date);
	}

	@Override
	public List<MaintainerAllotment> findPreviousMainAllotByMainId(String maintainer_id, String date) {
		// TODO Auto-generated method stub
		return maintainerAllotmentDao.findPreviousMainAllotByMainId(maintainer_id, date);
	}

	@Override
	public List<MaintainerAllotment> findComingMainAllotByMainId(String maintainer_id, String date) {
		// TODO Auto-generated method stub
		return maintainerAllotmentDao.findComingMainAllotByMainId(maintainer_id, date);
	}

	@Override
	public List<MaintainerAllotment> findMaintainerAllotmentByMembId(String member_id) {
		// TODO Auto-generated method stub
		return maintainerAllotmentDao.findMaintainerAllotmentByMembId(member_id);
	}

	@Override
	public ReturnMsg createMaintainerAllotment(MaintainerAllotment maintainerAllotment) {
		// TODO Auto-generated method stub
		return maintainerAllotmentDao.createMaintainerAllotment(maintainerAllotment);
	}

	@Override
	public ReturnMsg updateMaintainerAllotment(MaintainerAllotment maintainerAllotment) {
		// TODO Auto-generated method stub
		return maintainerAllotmentDao.updateMaintainerAllotment(maintainerAllotment);
	}

	@Override
	public ReturnMsg maintainerAllotmentExist(String maintainer_id) {
		// TODO Auto-generated method stub
		return maintainerAllotmentDao.maintainerAllotmentExist(maintainer_id);
	}

}
