package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create on 2011-04-06
 * @version 1.0 
 * 节目操作员关系表
 */
@Entity
@Table(name = "programoperator")
public class ProgramOperator {
	/*
	 * 流水号
	 */
	@Id
	@Column(length=32)
	private String serialno; 

	/*
	 * 节目Id
	 */
	@Column(length=32, nullable = false)
	private String programid; 

	/*
	 * 操作员Id
	 */
	@Column(length=32, nullable = false)
	private String operatorid; 
	
	/*
	 * 类型：	0：自己创建的	1：下发下来的
	 */
	@Column(length=1, nullable = false)
	private String type;
	
	/*
	 * 共享者，取自操作员id，下发下来的才有该值
	 */
	@Column(length=32)
	private String sharers;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSharers() {
		return sharers;
	}

	public void setSharers(String sharers) {
		this.sharers = sharers;
	}
	
	
}
  