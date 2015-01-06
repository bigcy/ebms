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

// 操作员权限表
@Entity
@Table(name = "operatorpower")
public class OperatorPower {

	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;

	/*
	 * 权限Id
	 */
	@Column(length = 32, nullable = false)
	private String powerId;

	/*
	 * 操作员编号
	 */
	@Column(length = 32, nullable = false)
	private String operatorid;

	/*
	 * 用于统计使用次数以及功能列表的排序依据
	 */
	@Column(length = 4, nullable = true)
	private String sort;

	/*
	 * 标识是否是快捷方试 在配置程序配置文件配置快捷方式的个数
	 */
	@Column(length = 2, nullable = true)
	private String shortcut;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

}
