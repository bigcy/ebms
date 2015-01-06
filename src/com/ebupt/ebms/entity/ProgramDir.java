package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua
 * Create on 2011-04-01
 * @version 1.0
 * ��ĿĿ¼��
 */
@Entity
@Table(name = "programdir")
public class ProgramDir {
	
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
	 * ��Ŀid
	 */
	@Column(length = 32, nullable = false)
	private String programid;

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

	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

}
