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
//终端基本信息表
@Entity
@Table(name = "terminal")
public class Terminal {
	/*
	 * 终端账号
	 */
	@Id
	@Column(length = 32)
	private String terminalid;
	
	/*
	 * 终端昵称
	 */
	@Column(length = 32,nullable=true)
	private String name;
	
	/*
	 * 厂商编号，对应终端账号的第3-5位
	 */
	@Column(length = 3,nullable=false)
	private String factoryid;
	
	/*
	 * 创建时间
	 */
	@Column(length =14,nullable=false)
	private String createtime;
	
	/*
	 * 终端所在区域，对应区域表id
	 */
	@Column(length = 32,nullable=false)
	private String locationid;
	
	/*
	 * 终端安装的位置
	 */
	@Column(length = 32,nullable=false)
	private String areaid;
	
	/*
	 * 联系人姓名
	 */
	@Column(length = 32)
	private String contacter;
	
	/*
	 * 联系人手机号
	 */
	@Column(length = 11)
	private String phone;
	
	/*
	 * 联系人邮箱
	 */
	@Column(length = 50)
	private String email;
	
	/*
	 * 终端的详细描述
	 */
	@Column(length = 255,nullable=true)
	private String description;

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFactoryid() {
		return factoryid;
	}

	public void setFactoryid(String factoryid) {
		this.factoryid = factoryid;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
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

	
}
  