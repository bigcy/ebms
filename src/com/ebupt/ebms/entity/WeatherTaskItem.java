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
@Table(name = "weathertaskitem")
public class WeatherTaskItem {
	
	@Id
	@Column(length=32, nullable = false)
	private String serialno; // 流水号

	@Column(length=32, nullable = false)
	private String weatherid; // 天气信息id，等同于任务号码

	@Column(length=32, nullable = false)
	private String terminalid; // 终端Id号

	@Column(length=4, nullable = false)
	private String status; // 任务状态：0：新建0000：收完任务通知（终端下载完任务后反馈）0001：配置成功0002：配置失败

	@Column(length=14, nullable = false)
	private String createtime; // 创建时间

	@Column(length=14)
	private String recvtime; // 接收时间

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getWeatherid() {
		return weatherid;
	}

	public void setWeatherid(String weatherid) {
		this.weatherid = weatherid;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
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


}
  