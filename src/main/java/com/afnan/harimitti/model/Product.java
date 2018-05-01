package com.afnan.harimitti.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
public class Product {

	@Id
	@Column(name = "product_id", unique = true, nullable = true)
	private String product_id;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "img_url", nullable = true)
	private String img_url;

	@Column(name = "action_on", nullable = true)
	private Date action_on;
	
	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
