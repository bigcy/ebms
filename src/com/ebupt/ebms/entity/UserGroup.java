package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-04-01
 * @version 1.0  
 * 用户分组表
 */
@Entity
@Table(name = "usergroup")
public class UserGroup {

	/*
	 * 用户组号
	 */
	@Id
	@Column(length = 32)
	private String groupid;
	
	/*
	 * 组名称
	 */
	@Column(length = 32,nullable=false)
	private String name;
	
	/*
	 * 该组对应的操作员
	 */
	@Column(length = 32,nullable=false)
	private String operatorid;
	
	/*
	 * 创建时间
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * 父群组，根群组默认为-1
	 */
	@Column(length = 32,nullable=false)
	private String supergroupid;
	
	/*
	 * 组所在的层次，根群组为1，往下以父群组所在的level加1，以此类推。具有相同level值的组为同等级别的组。
	 */
	@Column(length = 2,nullable=false)
	private String userlevel;
	
	/*
	 * 群组的详细描述，备注
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
  