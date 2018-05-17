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
@Table(name = "feedback_details")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "membership_id", nullable = false)
	private String membership_id;

	@Column(name = "maintainer_id", nullable = false)
	private String maintainer_id;

	@Column(name = "plant_img1", nullable = false)
	private String plant_img1;

	@Column(name = "plant_img2", nullable = false)
	private String plant_img2;

	@Column(name = "plant_img3", nullable = false)
	private String plant_img3;

	@Column(name = "plant_img4", nullable = false)
	private String plant_img4;

	@Column(name = "plant_img5", nullable = false)
	private String plant_img5;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "audio_file_url", nullable = false)
	private String audio_file_url;

	@Column(name = "status", nullable = true)
	private int status;

	@Column(name = "payment_method", nullable = false)
	private String payment_method;

	@Column(name = "chk_img_url", nullable = false)
	private String chk_img_url;

	@Column(name = "action_on", nullable = false)
	private Date action_on;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMembership_id() {
		return membership_id;
	}

	public void setMembership_id(String membership_id) {
		this.membership_id = membership_id;
	}

	public String getMaintainer_id() {
		return maintainer_id;
	}

	public void setMaintainer_id(String maintainer_id) {
		this.maintainer_id = maintainer_id;
	}

	public String getPlant_img1() {
		return plant_img1;
	}

	public void setPlant_img1(String plant_img1) {
		this.plant_img1 = plant_img1;
	}

	public String getPlant_img2() {
		return plant_img2;
	}

	public void setPlant_img2(String plant_img2) {
		this.plant_img2 = plant_img2;
	}

	public String getPlant_img3() {
		return plant_img3;
	}

	public void setPlant_img3(String plant_img3) {
		this.plant_img3 = plant_img3;
	}

	public String getPlant_img4() {
		return plant_img4;
	}

	public void setPlant_img4(String plant_img4) {
		this.plant_img4 = plant_img4;
	}

	public String getPlant_img5() {
		return plant_img5;
	}

	public void setPlant_img5(String plant_img5) {
		this.plant_img5 = plant_img5;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAudio_file_url() {
		return audio_file_url;
	}

	public void setAudio_file_url(String audio_file_url) {
		this.audio_file_url = audio_file_url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getChk_img_url() {
		return chk_img_url;
	}

	public void setChk_img_url(String chk_img_url) {
		this.chk_img_url = chk_img_url;
	}

	public String getAction_on() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return dateFormat.format(action_on);
	}

	public void setAction_on(Date action_on) {
		this.action_on = action_on;
	}

}
