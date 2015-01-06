package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-09-20
 * @version 1.0  
 * 终端品牌对应表
 */
@Entity
@Table(name = "branditem")
public class BrandItem {

	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * 终端账号
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;

	/*
	 * 对应品牌的id
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
  