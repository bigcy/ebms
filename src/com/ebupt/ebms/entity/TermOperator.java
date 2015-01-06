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

//终端操作员关系表
@Entity
@Table(name = "termoperator")
public class TermOperator {

	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * 终端账号
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;

	/*
	 * 操作员Id
	 */
	@Column(length = 32,nullable=false)
	private String operatorid;
	
	/*
	 * 类型：0：自己创建的 	1：分配下来的
	 */
	@Column(length = 1, nullable = false)
	private String type = "0";

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
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

}
  