package com.afnan.harimitti.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "maintainers_location")
public class MaintainerLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "maintainer_id", nullable = false)
	private String maintainer_id;

	@Column(name = "membership_id", nullable = false)
	private String membership_id;

	@Column(name = "image_number", nullable = false)
	private int image_number;

	@Column(name = "latitude", nullable = false)
	private Double latitude;

	@Column(name = "longitude", nullable = true)
	private Double longitude;

	@Column(name = "action_on", nullable = false)
	private Date action_on;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaintainer_id() {
		return maintainer_id;
	}

	public void setMaintainer_id(String maintainer_id) {
		this.maintainer_id = maintainer_id;
	}

	public String getMembership_id() {
		return membership_id;
	}

	public void setMembership_id(String membership_id) {
		this.membership_id = membership_id;
	}

	public int getImage_number() {
		return image_number;
	}

	public void setImage_number(int image_number) {
		this.image_number = image_number;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAction_on() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return dateFormat.format(action_on);
	}

	public void setAction_on(Date action_on) {
		this.action_on = action_on;
	}

}
