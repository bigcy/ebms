package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-04-01
 * @version 1.0  
 * ����Ա�û����
 */

//����Ա��
@Entity
@Table(name = "operatorgroup")
public class OperatorGroup {
	
	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * ����Աid
	 */
	@Column(length = 32,nullable = false)
	private String operatorid;

	/*
	 * Ⱥ��id
	 */
	@Column(length = 32,nullable = false)
	private String groupid;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	

}
  