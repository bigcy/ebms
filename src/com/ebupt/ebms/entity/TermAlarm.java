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
//终端告警信息上报
@Entity
@Table(name = "termalarm")
public class TermAlarm {

	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * 终端账号
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * 告警码
	 */
	@Column(length = 4,nullable=false)
	private String alarmcode;
	
	/*
	 * 告警时间
	 */
	@Column(length = 14,nullable=false)
	private String time;
	
	/*
	 * 告警信息，供内部使用
	 */
	@Column(length = 128)
	private String alarmvalue;
	
	/*
	 * 告警状态
	 *0：待处理 （初始化默认值）
	 *1：已处理 
	 */
	@Column(length = 1,nullable=false)
	private String status;
	
	/*
	 * 告警处理时间
	 */
	@Column(length = 14)
	private String dealtime;
	
	/*
	 * 处理人
	 */
	@Column(length = 32)
	private String operatorid;
	
	/*
	 * 告警处理详情描述
	 */
	@Column(length = 255)
	private String description;

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

	public String getAlarmcode() {
		return alarmcode;
	}

	public void setAlarmcode(String alarmcode) {
		this.alarmcode = alarmcode;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAlarmvalue() {
		return alarmvalue;
	}

	public void setAlarmvalue(String alarmvalue) {
		this.alarmvalue = alarmvalue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDealtime() {
		return dealtime;
	}

	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
  