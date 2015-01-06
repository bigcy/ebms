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

//工作状态上报
@Entity
@Table(name = "termworking")
public class TermWorking {
	
	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;

	/*
	 * 终端id
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * 任务id
	 */
	@Column(length = 32,nullable=false)
	private String taskid;
	
	/*
	 * 播控终端状态
	 * 0：playing(播放中)
	 * 1：sleep(休眠)
	 * 2：close(关机)
	 */
	@Column(length = 1)
	private String playerstatus;
	
	/*
	 * 外接设备类型
	 * 0001：电视机
	 * 0002：LED
	 */
	@Column(length = 4)
	private String extype;
	
	/*
	 * 外接设备状态
	 * 0：正常
	 * 1：异常
	 */
	@Column(length = 1)
	private String exstatus;
	
	/*
	 * 创建时间
	 */
	@Column(length = 14, nullable = false)
	private String createtime;
	
	/*
	 * 上报时间
	 */
	@Column(length = 14)
	private String reporttime;
	
	/*
	 * 任务状态：0：新建1：上传完成
	 */
	@Column(length = 4, nullable = false)
	private String status;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getPlayerstatus() {
		return playerstatus;
	}

	public void setPlayerstatus(String playerstatus) {
		this.playerstatus = playerstatus;
	}

	public String getExtype() {
		return extype;
	}

	public void setExtype(String extype) {
		this.extype = extype;
	}

	public String getExstatus() {
		return exstatus;
	}

	public void setExstatus(String exstatus) {
		this.exstatus = exstatus;
	}

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
  