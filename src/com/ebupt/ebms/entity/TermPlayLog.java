package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua Create time 2011-10-19
 * @version 1.0
 */
@Entity
@Table(name = "termplaylog")
public class TermPlayLog {

	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;

	@Column(length = 32, nullable = false)
	private String terminalid;

	/*
	 * 任务类别
	 */
	@Column(length = 1, nullable = false)
	private String taskType;

	/*
	 * 任务、节目、素材ID
	 */
	@Column(length = 32, nullable = false)
	private String taskid;

	/*
	 * 所属任务、节目、素材ID
	 */
	@Column(length = 32, nullable = false)
	private String belongtaskid;

	/*
	 * 开始播放时间
	 */
	@Column(length = 19, nullable = false)
	private String playtime;

	@Column(length = 14, nullable = false)
	private String recvtime;

	@Column(length = 8, nullable = false)
	private String datetime;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getBelongtaskid() {
		return belongtaskid;
	}

	public void setBelongtaskid(String belongtaskid) {
		this.belongtaskid = belongtaskid;
	}

	public String getPlaytime() {
		return playtime;
	}

	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}

	public String getRecvtime() {
		return recvtime;
	}

	public void setRecvtime(String recvtime) {
		this.recvtime = recvtime;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

}
