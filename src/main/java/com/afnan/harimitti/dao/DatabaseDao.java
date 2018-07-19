package com.afnan.harimitti.dao;

import com.afnan.harimitti.model.ReturnMsg;

public interface DatabaseDao {

	public ReturnMsg databaseBackup(String email_id);

}
