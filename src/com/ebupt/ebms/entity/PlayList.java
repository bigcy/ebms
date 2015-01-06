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
 * 播放任务表
 */
@Entity
@Table(name = "playlist")
public class PlayList {
	
	@Id
	@Column(length=32)
	private String playlistid; // 播放任务id

	@Column(length=255, nullable = false)
	private String name; // 播放任务名称

	@Column(length=4, nullable = false)
	private String starttime; // 开始时间 例：0900、2100分别表示早、晚9点HHMM

	@Column(length=4, nullable = false)
	private String  endtime; // 结束时间 例：同开始时间
	
	@Column(length=8, nullable = true)
	private String startdate; // 开始日期YYYYMMDD

	@Column(length=8, nullable = true)
	private String  enddate; // 结束日期YYYYMMDD

	@Column(length=32, nullable = false)
	private String templateid; // 任务对应的模板
	
	@Column(length=14, nullable = false)
	private String createtime; // 创建日期
	
	@Column(length=255)
	private String description; // 描述
	
	@Column(length=14)
	private String approvetime; // 审批时间

	@Column(length=32)
	private String approver; // 审批者

	@Column(length=1)
	private String status; // 播放列表状态N：初始化 W：待审批 P：审批通过 R：审批不通过 

	@Column(length=100)
	private String denyReason; // 审批不通过的理由
	
	@Column(nullable = false)
	private int usedcount = 0;//使用次数
	
	@Column(length = 1,nullable = false)
	private String isopen;//是否公开T：公开（True)F：不公开（False）
	
	@Column(length = 1,nullable = false)
	private String type;//创建方式 0：excel导入  1：手动添加

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
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

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
  