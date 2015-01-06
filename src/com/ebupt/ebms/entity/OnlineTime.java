package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author 
 * Create time 2014-02-27
 * @version 1.0  
 * 终端在线时长统计
 */

@Entity
@Table(name = "onlinetime")
public class OnlineTime {
	
	@Id
	@Column(length = 32)
	private String serialno;//流水号

	@Column(length = 32,nullable=false)
	private String terminalid;//终端编号
	
	@Column(length = 8,nullable=false)
	private String statisticsdate;//统计日期
	
	@Column(length = 5)
	private String onlinetime;//在线时长
	
	@Column(length = 14, nullable = false)
	private String updatetime;//更新时间

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

	public String getStatisticsdate() {
		return statisticsdate;
	}

	public void setStatisticsdate(String statisticsdate) {
		this.statisticsdate = statisticsdate;
	}

	public String getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
}