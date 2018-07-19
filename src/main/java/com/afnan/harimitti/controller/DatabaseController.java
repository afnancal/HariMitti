package com.afnan.harimitti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.afnan.harimitti.model.ReturnMsg;
import com.afnan.harimitti.service.DatabaseService;

@RestController
public class DatabaseController {

	@Autowired
	DatabaseService databaseService;

	// -------------------Database backup----------------------
	@RequestMapping(value = "/databaseBackup/{email_id}/", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ReturnMsg databaseBackup(@PathVariable("email_id") String email_id) {

		return databaseService.databaseBackup(email_id);
	}

}
