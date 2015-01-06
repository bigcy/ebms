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

//��ɫ��
@Entity
@Table(name = "role")
public class Role {
	
	/*
	 * ��ɫ���
	 */
	@Id
	@Column(length = 32)
	private String roleid;

	/*
	 * ��ɫ��
	 */
	@Column(length = 50,nullable=false)
	private String rolename;
	
	/*
	 * ����ʱ��
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * �����ý�ɫ�Ĳ���Ա
	 */
	@Column(length = 32,nullable=false)
	private String operatorid;
	
	/*
	 * ��ɫ���ͣ�0��ϵͳ��ɫ  1���Զ����ɫ  ϵͳ��ɫ�������޸ĺ�ɾ��
	 */
	@Column(length = 1,nullable=false)
	private String type;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
  