package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua
 * 2011-09-02
 * 4.9.	安装区域表  营业厅网点的信息
 */
@Entity
@Table(name = "area")
public class Area {
	
	@Id
	@Column(length = 32)
	private String areaid; // 区域ID
	
	@Column(length = 255, nullable = false)
	private String area; // 网点名称
	
	@Column(length = 255, nullable = false)
	private String address; // 网点地址
	
	@Column(length = 32, nullable = false)
	private String locationid; // 网点所属地市（Location表的主键）
	
	@Column(length = 50, nullable = false)
	private String longitude; // 经度
	
	@Column(length = 50, nullable = false)
	private String latitude; // 纬度

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
