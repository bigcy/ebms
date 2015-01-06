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

//角色权限细表
@Entity
@Table(name = "rolepower")
public class RolePower {
	
	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;

	/*
	 * 权限Id
	 */
	@Column(length = 32,nullable=false)
	private String powerid;
	
	/*
	 *角色ID，标识此权限属于那个角色
	 */
	@Column(length = 32,nullable=false)
	private String roleid;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getPowerid() {
		return powerid;
	}

	public void setPowerid(String powerid) {
		this.powerid = powerid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	

}
  