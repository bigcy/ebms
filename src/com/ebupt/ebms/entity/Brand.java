package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua Create time 2012-09-20
 * @version 1.0
 * 品牌表
 */
@Entity
@Table(name = "brand")
public class Brand {

	/*
	 * 品牌id
	 */
	@Id
	@Column(length = 32)
	private String brandid;

	/*
	 * 暂无实际意义，默认1
	 */
	@Column(length = 1)
	private String brandlevel = "1";

	/*
	 *品牌名称
	 */
	@Column(length = 32, nullable = false)
	private String name;

	/*
	 * 创建该品牌的操作员
	 */
	@Column(length = 32)
	private String operatorid;

	/*
	 * 创建时间
	 */
	@Column(length = 14, nullable = false)
	private String createtime;

	/*
	 * 该品牌的上级id，目前填写默认值-1
	 */
	@Column(length = 32)
	private String superbrandid;

	/*
	 * 品牌的详细描述
	 */
	@Column(length = 255, nullable = true)
	private String description;

	/*
	 * 品牌类型，暂无实际用处，默认0
	 */
	@Column(length = 1)
	private String type;

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getBrandlevel() {
		return brandlevel;
	}

	public void setBrandlevel(String brandlevel) {
		this.brandlevel = brandlevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getSuperbrandid() {
		return superbrandid;
	}

	public void setSuperbrandid(String superbrandid) {
		this.superbrandid = superbrandid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
