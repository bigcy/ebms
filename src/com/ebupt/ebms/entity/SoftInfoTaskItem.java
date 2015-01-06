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
@Table(name = "softinfotaskitem")
public class SoftInfoTaskItem {
	
	@Id
	@Column(length=32)
	private String serialno; // 流水号

	@Column(length=32, nullable = false)
	private String terminalid; // 终端id

	@Column(length=32)
	private String softinfoid; // 软件信息ID，任务id

	@Column(length=8 , nullable = false)
	private String version; // 当前的版本号

	@Column(length=14, nullable = false)
	private String createtime; // 创建时间

	@Column(length=14)
	private String recvtime; // 状态上报时间

	@Column(length=4)
	private String status; // 任务状态：0：新建0000：收完任务通知0001：升级成功（终端下载完升级包并升级后返回）01XX：升级失败0101：源文件不存在0102：下载超时0103：账号权限错误0104：文件md5校验错误0105：连接超时

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

	public String getSoftinfoid() {
		return softinfoid;
	}

	public void setSoftinfoid(String softinfoid) {
		this.softinfoid = softinfoid;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
  