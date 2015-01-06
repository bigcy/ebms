package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua Create time 2011-03-04
 * @version 1.0
 */
// 终端分组表
@Entity
@Table(name = "termgroup")
public class TermGroup {

	/*
	 * 终端组号
	 */
	@Id
	@Column(length = 32)
	private String groupid;

	/*
	 * 群组所在的层次，根（北京）为1，往下以父群组所在的grouplevel加1，以此类推。 北京项目用到三级层次，如： 北京->海淀区->知春路营业厅
	 */
	@Column(length = 2, nullable = false)
	private String grouplevel;

	/*
	 * 组名称
	 */
	@Column(length = 32, nullable = false)
	private String name;

	/*
	 * 该组对应的操作员
	 */
	@Column(length = 32, nullable = false)
	private String operatorid;

	/*
	 * 创建时间
	 */
	@Column(length = 14, nullable = false)
	private String createtime;

	/*
	 * 父群组，根群组默认为-1
	 */
	@Column(length = 32, nullable = false)
	private String supergroupid;

	/*
	 * 群组的详细描述，备注
	 */
	@Column(length = 255, nullable = true)
	private String description;

	/*
	 * 群组类型：0：行政分组 1：自定义分组
	 */
	@Column(length = 1, nullable = false)
	private String type;
	
	@Column(length = 32, nullable = true)
	private String groupCode;

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGrouplevel() {
		return grouplevel;
	}

	public void setGrouplevel(String grouplevel) {
		this.grouplevel = grouplevel;
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

	public String getSupergroupid() {
		return supergroupid;
	}

	public void setSupergroupid(String supergroupid) {
		this.supergroupid = supergroupid;
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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

}