package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-09-20
 * @version 1.0  
 * �ն�Ʒ�ƶ�Ӧ��
 */
@Entity
@Table(name = "branditem")
public class BrandItem {

	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * �ն��˺�
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;

	/*
	 * ��ӦƷ�Ƶ�id
	 */
	@Column(length = 32,nullable=false)
	private String brandid;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

}
  