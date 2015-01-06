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
//�ն˻�����Ϣ��
@Entity
@Table(name = "terminal")
public class Terminal {
	/*
	 * �ն��˺�
	 */
	@Id
	@Column(length = 32)
	private String terminalid;
	
	/*
	 * �ն��ǳ�
	 */
	@Column(length = 32,nullable=true)
	private String name;
	
	/*
	 * ���̱�ţ���Ӧ�ն��˺ŵĵ�3-5λ
	 */
	@Column(length = 3,nullable=false)
	private String factoryid;
	
	/*
	 * ����ʱ��
	 */
	@Column(length =14,nullable=false)
	private String createtime;
	
	/*
	 * �ն��������򣬶�Ӧ�����id
	 */
	@Column(length = 32,nullable=false)
	private String locationid;
	
	/*
	 * �ն˰�װ��λ��
	 */
	@Column(length = 32,nullable=false)
	private String areaid;
	
	/*
	 * ��ϵ������
	 */
	@Column(length = 32)
	private String contacter;
	
	/*
	 * ��ϵ���ֻ���
	 */
	@Column(length = 11)
	private String phone;
	
	/*
	 * ��ϵ������
	 */
	@Column(length = 50)
	private String email;
	
	/*
	 * �ն˵���ϸ����
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
  