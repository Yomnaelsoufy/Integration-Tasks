package com.example.timer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nafathinfo")
public class NafathInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String poinum;
	private String poitype;
	private String mobilenum;

	public NafathInfo(String poinum, String poitype, String mobilenum) {
		super();
		this.poinum = poinum;
		this.poitype = poitype;
		this.mobilenum = mobilenum;
	}

	public NafathInfo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPoinum() {
		return poinum;
	}

	public void setPoinum(String poinum) {
		this.poinum = poinum;
	}

	public String getPoitype() {
		return poitype;
	}

	public void setPoitype(String poitype) {
		this.poitype = poitype;
	}

	public String getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}

}