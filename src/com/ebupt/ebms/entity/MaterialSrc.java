package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author
 * Create on 2014-02-13
 * @version 1.0
 * 物料来源表
 */
@Entity
@Table(name = "materialsrc")
public class MaterialSrc {
	
	@Id
	@Column(length = 32)
	private String materialsrcid; // 物料来源id
	
	@Column(length = 32)
	private String materialsrcname; // 物料来源名称
	
	@Column(length = 14)
	private String createtime; // 创建时间

	public String getMaterialsrcid() {
		return materialsrcid;
	}

	public void setMaterialsrcid(String materialsrcid) {
		this.materialsrcid = materialsrcid;
	}

	public String getMaterialsrcname() {
		return materialsrcname;
	}

	public void setMaterialsrcname(String materialsrcname) {
		this.materialsrcname = materialsrcname;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
