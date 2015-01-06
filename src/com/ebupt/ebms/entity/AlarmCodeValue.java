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
@Entity
@Table(name = "alarmcodevalue")
public class AlarmCodeValue {

	/*
	 * 告警码
	 */
	@Id
	@Column(length = 4)
	private String alarmcode;
	
	/*
	 * 告警内容：可扩展
	 * 参考接口规范中的告警码对应关系
	 */
	@Column(length = 50,nullable=false)
	private String alarmvalue;

	public String getAlarmcode() {
		return alarmcode;
	}

	public void setAlarmcode(String alarmcode) {
		this.alarmcode = alarmcode;
	}

	public String getAlarmvalue() {
		return alarmvalue;
	}

	public void setAlarmvalue(String alarmvalue) {
		this.alarmvalue = alarmvalue;
	}

}
  