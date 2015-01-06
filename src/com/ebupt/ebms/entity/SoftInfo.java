package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 */
@Entity
@Table(name = "softinfo")
public class SoftInfo {
	
	@Id
	@Column(length=32)
	private String softinfoid; // ��ˮ��

	@Column(length=3, nullable = false)
	private String factoryid; // ����id

	@Column(length=8 , nullable = false)
	private String version; // ����汾

	@Column(length=128, nullable = false)
	private String link; // ������ַ

	@Column(length=12, nullable = false)
	private String softsize; // �����Сbyte

	@Column(length=32, nullable = false)
	private String md5; // ������md5

	@Column(length=50)
	private String description; // ���������
	
	@Column(length=14, nullable = false)
	private String createtime; //����ʱ��

	public String getSoftinfoid() {
		return softinfoid;
	}

	public void setSoftinfoid(String softinfoid) {
		this.softinfoid = softinfoid;
	}

	public String getFactoryid() {
		return factoryid;
	}

	public void setFactoryid(String factoryid) {
		this.factoryid = factoryid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


	public String getSoftsize() {
		return softsize;
	}

	public void setSoftsize(String softsize) {
		this.softsize = softsize;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
  