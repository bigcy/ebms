package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-03-04
 * @version 1.0  
 */

//�����
@Entity
@Table(name = "location")
public class Location {
	
	/*
	 * ����ID00:ȫ��01������(0771)02������(0771)03������(0772������
	 */
	@Id
	@Column(length = 32)
	private String locationId;

	/*
	 * ��������
	 */
	@Column(length = 8,nullable=false)
	private String city;
	
	/*
	 * ��������
	 */
	@Column(length = 6)
	private String code;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
  