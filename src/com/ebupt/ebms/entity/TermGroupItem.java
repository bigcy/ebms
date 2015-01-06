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
//�ն˷�����ϸ��
@Entity
@Table(name = "termgroupitem")
public class TermGroupItem {

	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * �ն��˺�
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;

	/*
	 * �ն����
	 */
	@Column(length = 32,nullable=false)
	private String groupid;

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

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

}
  