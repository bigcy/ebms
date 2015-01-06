package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-04-01
 * @version 1.0  
 * �û������
 */
@Entity
@Table(name = "usergroup")
public class UserGroup {

	/*
	 * �û����
	 */
	@Id
	@Column(length = 32)
	private String groupid;
	
	/*
	 * ������
	 */
	@Column(length = 32,nullable=false)
	private String name;
	
	/*
	 * �����Ӧ�Ĳ���Ա
	 */
	@Column(length = 32,nullable=false)
	private String operatorid;
	
	/*
	 * ����ʱ��
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * ��Ⱥ�飬��Ⱥ��Ĭ��Ϊ-1
	 */
	@Column(length = 32,nullable=false)
	private String supergroupid;
	
	/*
	 * �����ڵĲ�Σ���Ⱥ��Ϊ1�������Ը�Ⱥ�����ڵ�level��1���Դ����ơ�������ͬlevelֵ����Ϊͬ�ȼ�����顣
	 */
	@Column(length = 2,nullable=false)
	private String userlevel;
	
	/*
	 * Ⱥ�����ϸ��������ע
	 */
	@Column(length = 255)
	private String description;

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getSupergroupid() {
		return supergroupid;
	}

	public void setSupergroupid(String supergroupid) {
		this.supergroupid = supergroupid;
	}

	public String getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
		

}
  