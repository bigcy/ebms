package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua
 * Create on 2011-04-01
 * @version 1.0
 * 模板目录表
 */
@Entity
@Table(name = "templatedir")
public class TemplateDir {
	
	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * 目录id
	 */
	@Column(length = 32, nullable = false)
	private String directoryid;
	
	/*
	 * 模板id
	 */
	@Column(length = 32, nullable = false)
	private String templateid;

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

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}


}
