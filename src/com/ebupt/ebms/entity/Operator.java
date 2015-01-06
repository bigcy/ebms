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

//操作员表
@Entity
@Table(name = "operator")
public class Operator {

	@Id
	@Column(length = 32)
	private String operatorid;
	
	/*
	 * 父操作员id，-1代表根操作员
	 */
	@Column(length = 32)
	private String superoperatorid;
	
	/*
	 * 操作员所在的级别，超级管理员为0，往下以父操作员所在的level加1，以此类推。
	 */
	@Column(length = 2, nullable = false)
	private String operatorlevel;

	/*
	 * 用户登录名
	 */
	@Column(length = 50,nullable=false)
	private String loginname;
	
	/*
	 * 登录密码
	 */
	@Column(length = 32,nullable=false)
	private String passwd;
	
	/*
	 * 显示的用户名
	 */
	@Column(length = 50,nullable=false)
	private String showname;
	
	/*
	 * 手机号码
	 */
	@Column(length = 11,nullable=false)
	private String phone;
	
	/*
	 * 邮箱
	 */
	@Column(length = 50,nullable=true)
	private String email;
	
	/*
	 * 创建时间
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * 00代表全省
	 */
	@Column(length = 32,nullable=false)
	private String locationid;
	
	/*
	 * 角色编号
	 */
	@Column(length = 32)
	private String roleid;

	/*
	 * 最后登录时间
	 */
	@Column(length = 14)
	private String lastlogintime;
	
	/*
	 * 本次登录时间，每次登录后，将该字段的值更新到lastlogintime
	 */
	@Column(length = 14)
	private String newlogintime;
	
	/*
	 * 最后登录IP
	 */
	@Column(length = 15)
	private String lastloginip;
	
	/*
	 * 本次登录IP
	 */
	@Column(length = 15)
	private String newloginip;
	
	/*
	 * 详细描述，备注
	 */
	@Column(length = 255)
	private String description;
	
	/*
	 * 设置权限的操作员id
	 */
	@Column(length = 32)
	private String setroleoperid;
	
	/*
	 * 设置权限时间
	 */
	@Column(length = 14)
	private String setroletime;
	
	/*
	 * 账号的状态，4A使用，在本系统中无实际意义
	 */
	@Column(length = 1,columnDefinition="Integer default 0")
	private Integer acct_status;
	
	/*
	 * 操作员所属营业厅
	 */
	@Column(length = 32)
	private String areaid;
	
	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getSuperoperatorid() {
		return superoperatorid;
	}

	public void setSuperoperatorid(String superoperatorid) {
		this.superoperatorid = superoperatorid;
	}

	public String getOperatorlevel() {
		return operatorlevel;
	}

	public void setOperatorlevel(String operatorlevel) {
		this.operatorlevel = operatorlevel;
	}

	public String getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getNewlogintime() {
		return newlogintime;
	}

	public void setNewlogintime(String newlogintime) {
		this.newlogintime = newlogintime;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getNewloginip() {
		return newloginip;
	}

	public void setNewloginip(String newloginip) {
		this.newloginip = newloginip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSetroleoperid() {
		return setroleoperid;
	}

	public void setSetroleoperid(String setroleoperid) {
		this.setroleoperid = setroleoperid;
	}

	public String getSetroletime() {
		return setroletime;
	}

	public void setSetroletime(String setroletime) {
		this.setroletime = setroletime;
	}

	public Integer getAcct_status() {
		return acct_status;
	}

	public void setAcct_status(Integer acct_status) {
		this.acct_status = acct_status;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	

}
  