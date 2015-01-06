package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-04-01
 * @version 1.0  
 */
@Entity
@Table(name = "directory")
public class Directory {

	/*
	 * 目录Id
	 */
	@Id
	@Column(length = 32)
	private String directoryid;
	
	/*
	 * 父目录，根目录默认为-1
	 */
	@Column(length = 32,nullable=false)
	private String superdirectoryid;
	
	/*
	 * 目录名称
	 */
	@Column(length = 32,nullable=false)
	private String name;
	
	/*
	 * 目录类型
	 * 01：素材目录
	 * 02：节目目录
	 * 03：模板目录
	 * 04：播放任务目录
	 */
	@Column(length = 2,nullable=false)
	private String type;
	
	/*
	 * 该目录对应的操作员
	 */
	@Column(length = 32,nullable=false)
	private String operatorid;
	
	/*
	 * 创建时间
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * 目录的详细描述，备注
	 */
	@Column(length = 255,nullable=true)
	private String description;

	public String getDirectoryid() {
		return directoryid;
	}

	public void setDirectoryid(String directoryid) {
		this.directoryid = directoryid;
	}

	public String getSuperdirectoryid() {
		return superdirectoryid;
	}

	public void setSuperdirectoryid(String superdirectoryid) {
		this.superdirectoryid = superdirectoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
  