package com.afnan.harimitti.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.afnan.harimitti.databasebackup.MysqlExportService;
import com.afnan.harimitti.model.ReturnMsg;

@Repository
public class DatabaseDaoImpl implements DatabaseDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ReturnMsg databaseBackup(String email_id) {
		// TODO Auto-generated method stub
		ReturnMsg returnMsg = new ReturnMsg();

		try {
			// required properties for exporting of db
			Properties properties = new Properties();
			properties.setProperty(MysqlExportService.DB_NAME, "lllc");
			properties.setProperty(MysqlExportService.DB_USERNAME, "user");
			properties.setProperty(MysqlExportService.DB_PASSWORD, "password");
			properties.setProperty(MysqlExportService.JDBC_CONNECTION_STRING, "jdbc:mysql://jws-app-mysql:3306/lllc");

			// properties relating to email config
			properties.setProperty(MysqlExportService.EMAIL_HOST, "smtp.gmail.com");
			properties.setProperty(MysqlExportService.EMAIL_PORT, "587");
			properties.setProperty(MysqlExportService.EMAIL_USERNAME, "apps@globopex.com");
			properties.setProperty(MysqlExportService.EMAIL_PASSWORD, "apps@123#");
			properties.setProperty(MysqlExportService.EMAIL_FROM, "apps@globopex.com");
			properties.setProperty(MysqlExportService.EMAIL_TO, email_id);

			// set the outputs temp dir
			properties.setProperty(MysqlExportService.TEMP_DIR, new File("external").getPath());

			MysqlExportService mysqlExportService = new MysqlExportService(properties);
			String returnResult = mysqlExportService.export();

			returnMsg.setStatus(true);
			returnMsg.setMsg(returnResult);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMsg.setStatus(false);
			returnMsg.setMsg("" + e);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMsg.setStatus(false);
			returnMsg.setMsg("" + e);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMsg.setStatus(false);
			returnMsg.setMsg("" + e);
		}

		return returnMsg;
	}

}
