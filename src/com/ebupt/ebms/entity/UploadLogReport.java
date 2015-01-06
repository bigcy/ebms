package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-03-23
 * @version 1.0  
 */
@Entity
@Table(name = "uploadlogreport")
public class UploadLogReport {
	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * 终端Id号
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * 任务id
	 */
	@Column(length = 32, nullable = false)
	private String taskid;

	
	
	/*
	 * 日志类型runlog：运行日志playlog：播放日志downloadlog：下载日志debuglog：调试日志
	 */
	@Column(length = 12)
	private String logtype;
	
	/*
	 * 日志文件的名称
	 */
	@Column(length = 32)
	private String logname;
	
	/*
	 * 任务状态
	 */
	@Column(length = 4,nullable=false)
	private String status;
	
	/*
	 * 创建时间
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * 接收时间
	 */
	@Column(length = 14)
	private String recvtime;

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getRecvtime() {
		return recvtime;
	}

	public void setRecvtime(String recvtime) {
		this.recvtime = recvtime;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	

}
  