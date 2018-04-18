package com.afnan.harimitti.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class User {

	/*
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	 */

	@Id
	@Column(name = "user_id", unique = true, nullable = true)
	private String user_id;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "address", nullable = true)
	private String address;

	@Column(name = "contact_no", nullable = false)
	private String contact_no;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "action_on", nullable = true)
	private Date action_on;

	/*
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 */

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getAction_on() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return dateFormat.format(action_on);
	}

	public void setAction_on(Date action_on) {
		this.action_on = action_on;
	}

}