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

//�ն˳�����Ϣ
@Entity
@Table(name = "termfactory")
public class TermFactory {
	
	/*
	 * ����ID
	 */
	@Id
	@Column(length = 3)
	private String factoryid;

	/*
	 * ��������
	 */
	@Column(length = 32,nullable=false)
	private String name;
	
	/*
	 * ����ʱ��)
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * �ն���Կ
	 */
	@Column(length = 128,nullable=false)
	private String secretkey;

	public String getFactoryid() {
		return factoryid;
	}

	public void setFactoryid(String factoryid) {
		this.factoryid = factoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	
}
  