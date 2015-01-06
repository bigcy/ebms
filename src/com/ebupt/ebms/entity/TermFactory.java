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

//终端厂商信息
@Entity
@Table(name = "termfactory")
public class TermFactory {
	
	/*
	 * 厂商ID
	 */
	@Id
	@Column(length = 3)
	private String factoryid;

	/*
	 * 厂商名字
	 */
	@Column(length = 32,nullable=false)
	private String name;
	
	/*
	 * 创建时间)
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * 终端密钥
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
  