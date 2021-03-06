package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua
 * Create on 2011-04-01
 * @version 1.0
 * �ز�Ŀ¼��
 */
@Entity
@Table(name = "contentdir")
public class ContentDir {
	
	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * Ŀ¼id
	 */
	@Column(length = 32, nullable = false)
	private String directoryid;
	
	/*
	 * �ز�id
	 */
	@Column(length = 32, nullable = false)
	private String contentid;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getDirectoryid() {
		return directoryid;
	}

	public void setDirectoryid(String directoryid) {
		this.directoryid = directoryid;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	} 
	

}
