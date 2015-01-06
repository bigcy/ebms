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

//角色表
@Entity
@Table(name = "role")
public class Role {
	
	/*
	 * 角色编号
	 */
	@Id
	@Column(length = 32)
	private String roleid;

	/*
	 * 角色名
	 */
	@Column(length = 50,nullable=false)
	private String rolename;
	
	/*
	 * 创建时间
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * 创建该角色的操作员
	 */
	@Column(length = 32,nullable=false)
	private String operatorid;
	
	/*
	 * 角色类型：0：系统角色  1：自定义角色  系统角色不允许修改和删除
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
  