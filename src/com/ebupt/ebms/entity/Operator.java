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

//����Ա��
@Entity
@Table(name = "operator")
public class Operator {

	@Id
	@Column(length = 32)
	private String operatorid;
	
	/*
	 * ������Աid��-1���������Ա
	 */
	@Column(length = 32)
	private String superoperatorid;
	
	/*
	 * ����Ա���ڵļ��𣬳�������ԱΪ0�������Ը�����Ա���ڵ�level��1���Դ����ơ�
	 */
	@Column(length = 2, nullable = false)
	private String operatorlevel;

	/*
	 * �û���¼��
	 */
	@Column(length = 50,nullable=false)
	private String loginname;
	
	/*
	 * ��¼����
	 */
	@Column(length = 32,nullable=false)
	private String passwd;
	
	/*
	 * ��ʾ���û���
	 */
	@Column(length = 50,nullable=false)
	private String showname;
	
	/*
	 * �ֻ�����
	 */
	@Column(length = 11,nullable=false)
	private String phone;
	
	/*
	 * ����
	 */
	@Column(length = 50,nullable=true)
	private String email;
	
	/*
	 * ����ʱ��
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * 00����ȫʡ
	 */
	@Column(length = 32,nullable=false)
	private String locationid;
	
	/*
	 * ��ɫ���
	 */
	@Column(length = 32)
	private String roleid;

	/*
	 * ����¼ʱ��
	 */
	@Column(length = 14)
	private String lastlogintime;
	
	/*
	 * ���ε�¼ʱ�䣬ÿ�ε�¼�󣬽����ֶε�ֵ���µ�lastlogintime
	 */
	@Column(length = 14)
	private String newlogintime;
	
	/*
	 * ����¼IP
	 */
	@Column(length = 15)
	private String lastloginip;
	
	/*
	 * ���ε�¼IP
	 */
	@Column(length = 15)
	private String newloginip;
	
	/*
	 * ��ϸ��������ע
	 */
	@Column(length = 255)
	private String description;
	
	/*
	 * ����Ȩ�޵Ĳ���Աid
	 */
	@Column(length = 32)
	private String setroleoperid;
	
	/*
	 * ����Ȩ��ʱ��
	 */
	@Column(length = 14)
	private String setroletime;
	
	/*
	 * �˺ŵ�״̬��4Aʹ�ã��ڱ�ϵͳ����ʵ������
	 */
	@Column(length = 1,columnDefinition="Integer default 0")
	private Integer acct_status;
	
	/*
	 * ����Ա����Ӫҵ��
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
  