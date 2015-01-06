package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create on 2011-04-06
 * @version 1.0 
 * ��Ŀ����Ա��ϵ��
 */
@Entity
@Table(name = "programoperator")
public class ProgramOperator {
	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length=32)
	private String serialno; 

	/*
	 * ��ĿId
	 */
	@Column(length=32, nullable = false)
	private String programid; 

	/*
	 * ����ԱId
	 */
	@Column(length=32, nullable = false)
	private String operatorid; 
	
	/*
	 * ���ͣ�	0���Լ�������	1���·�������
	 */
	@Column(length=1, nullable = false)
	private String type;
	
	/*
	 * �����ߣ�ȡ�Բ���Աid���·������Ĳ��и�ֵ
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
  