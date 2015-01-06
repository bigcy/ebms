package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 */
@Entity
@Table(name = "playlistgroup")
public class PlayListGroup {
	
	@Id
	@Column(length=32)
	private String serialno; // 流水号

	@Column(length=32, nullable = false)
	private String playlistid; // 播放任务id

	@Column(length=32, nullable = false)
	private String groupid; // 群组ID
	
	@Column(nullable = false)
	private int priority; // 优先级，值越小优先级越高,0-4,5-9

	@Column(length=14, nullable = false)
	private String createtime; // 创建时间
	
	@Column(length=8, nullable = false)
	private String startdate; // 开始日期YYYYMMDD
	
	@Column(length=8, nullable = false)
	private String enddate; // 结束日期YYYYMMDD

	@Column(length=1, nullable = false)
	private String status; // 任务状态：N:等待执行 R:运行 P:暂停 C:撤销 E:执行结束

	@Column(length=1)
	private String approvestatus; // 审批状态：N：初始化 W：待审批 P：审批通过 R：审批不通过 

	@Column(nullable = false)
	private int approvetotalcount; // 需要审批的总次数

	@Column(nullable = false)
	private int approvedcount; // 当前已审批次数，用于计算审批进度，初始值为0
	
	@Column(length = 32, nullable = false)
	private String operatorid; // 操作员Id
	
	@Column(length = 1)
	private String readstatus = "N"; // 阅读状态：Y已阅，N未阅，默认为未阅

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(String approvestatus) {
		this.approvestatus = approvestatus;
	}

	public int getApprovetotalcount() {
		return approvetotalcount;
	}

	public void setApprovetotalcount(int approvetotalcount) {
		this.approvetotalcount = approvetotalcount;
	}

	public int getApprovedcount() {
		return approvedcount;
	}

	public void setApprovedcount(int approvedcount) {
		this.approvedcount = approvedcount;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}
	
}
  