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
	 * �澯��
	 */
	@Id
	@Column(length = 4)
	private String alarmcode;
	
	/*
	 * �澯���ݣ�����չ
	 * �ο��ӿڹ淶�еĸ澯���Ӧ��ϵ
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
  