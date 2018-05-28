package com.afnan.harimitti.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_details")
public class Admin {

	@Id
	@Column(name = "admin_id", unique = true, nullable = false)
	private String admin_id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "address", nullable = true)
	private String address;

	@Column(name = "contact_no", unique = true, nullable = false)
	private String contact_no;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "gcm_reg", nullable = false)
	private String gcm_reg;

	@Column(name = "img_url", nullable = true)
	private String img_url;

	@Column(name = "action_on", nullable = false)
	private Date action_on;

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGcm_reg() {
		return gcm_reg;
	}

	public void setGcm_reg(String gcm_reg) {
		this.gcm_reg = gcm_reg;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getAction_on() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return dateFormat.format(action_on);
	}

	public void setAction_on(Date action_on) {
		this.action_on = action_on;
	}

}
