package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 * 
 * changed by lishuhua
 * change time 2011-04-01
 */
@Entity
@Table(name = "template")
public class Template {
	
	@Id
	@Column(length=32)
	private String templateid; // 模板Id

	@Column(length=32, nullable = false)
	private String name; // 模板名称

	@Column(length=100)
	private String path; // 模板文件路径，缩略图在/thumb/下面

	@Column(length=14, nullable = false)
	private String createtime; // 创建时间

	@Column(length=4, nullable = false)
	private String width; // 宽度：像素

	@Column(length=4, nullable = false)
	private String height; // 高度：像素

	@Column(length=100)
	private String tplDescPath; // 模板文件对应描述文件路径

	/*
	 * 模板描述信息
	 */
	@Column(length=100)
	private String description;
	
	/*
	 * 审批时间
	 */
	@Column(length=14)
	private String approvetime;
	
	/*
	 * 审批者
	 */
	@Column(length=32)
	private String approver;
	
	/*
	 * 状态
	 * W：待审批
	 * P：审批通过 
	 * R：审批不通过 
	 */
	@Column(length=1)
	private String status;
	
	/*
	 * 审批不通过的理由
	 */
	@Column(length=100)
	private String denyReason;
	
	@Column(nullable = false)
	private int usedcount = 0;//使用次数
	
	/*
	 * 是否公开  T：公开（True）F：不公开（False）
	 */
	@Column(length=1,nullable = false)
	private String isopen;

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getTplDescPath() {
		return tplDescPath;
	}

	public void setTplDescPath(String tplDescPath) {
		this.tplDescPath = tplDescPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDenyReason() {
		return denyReason;
	}

	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	public int getUsedcount() {
		return usedcount;
	}

	public void setUsedcount(int usedcount) {
		this.usedcount = usedcount;
	}

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	
}
  