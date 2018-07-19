package com.afnan.harimitti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afnan.harimitti.dao.DatabaseDao;
import com.afnan.harimitti.model.ReturnMsg;

@Service
@Transactional
public class DatabaseServiceImpl implements DatabaseService {

	DatabaseDao databaseDao;

	@Autowired(required = true)
	public void setFeedbackDao(DatabaseDao databaseDao) {
		this.databaseDao = databaseDao;
	}

	@Override
	public ReturnMsg databaseBackup(String email_id) {
		// TODO Auto-generated method stub
		return databaseDao.databaseBackup(email_id);
	}

}
