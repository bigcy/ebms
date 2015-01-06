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
 *  changed by lishuhua
 *  change time 2011-04-01
 *  节目表
 */
@Entity
@Table(name = "program")
public class Program {
	
	@Id
	@Column(length=32)
	private String programid; // 节目id

	@Column(length=32, nullable = false)
	private String name; // 节目名称

	@Column(length=10, nullable = false)
	private String type; // 包括video-视频、pic-图片、audio-音频、text-文本、led-LED屏任务、realdata-实时数据
	
	@Column(length=14, nullable = false)
	private String createtime; // 创建日期
	
	@Column(length=1, nullable =false)
	private String issystem; // 是否是系统创建 ， 1：是。0：不是。如果是1，呈现的时候不显示给用户 ， 系统生成任务的时候调用。

	@Column(nullable = false)
	private int timelength; // 时间长度，单位秒
	
	@Column(length=255)
	private String description; // 节目单描述
	
	@Column(length=14)
	private String approvetime; // 审批时间

	@Column(length=32)
	private String approver; // 审批者

	@Column(length=1)
	private String status; // 状态 W：待审批  P：审批通过  R：审批不通过 

	@Column(length=100)
	private String denyReason; // 审批不通过的理由
	
	@Column(nullable = false)
	private int usedcount = 0;//使用次数
	
	@Column(length=1,nullable = false)
	private String isopen;//是否公开T：公开（True）F：不公开（False）

	@Column(length = 14, nullable = true)
	private String expirydate = "20201231";//节目有效期，根据素材中有效期自动计算
	
	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
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

	public String getIssystem() {
		return issystem;
	}

	public void setIssystem(String issystem) {
		this.issystem = issystem;
	}

	public int getTimelength() {
		return timelength;
	}

	public void setTimelength(int timelength) {
		this.timelength = timelength;
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

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	
}
  