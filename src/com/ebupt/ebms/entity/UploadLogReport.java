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
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * �ն�Id��
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * ����id
	 */
	@Column(length = 32, nullable = false)
	private String taskid;

	
	
	/*
	 * ��־����runlog��������־playlog��������־downloadlog��������־debuglog��������־
	 */
	@Column(length = 12)
	private String logtype;
	
	/*
	 * ��־�ļ�������
	 */
	@Column(length = 32)
	private String logname;
	
	/*
	 * ����״̬
	 */
	@Column(length = 4,nullable=false)
	private String status;
	
	/*
	 * ����ʱ��
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * ����ʱ��
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
  