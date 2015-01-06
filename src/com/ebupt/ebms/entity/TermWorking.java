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

//����״̬�ϱ�
@Entity
@Table(name = "termworking")
public class TermWorking {
	
	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;

	/*
	 * �ն�id
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * ����id
	 */
	@Column(length = 32,nullable=false)
	private String taskid;
	
	/*
	 * �����ն�״̬
	 * 0��playing(������)
	 * 1��sleep(����)
	 * 2��close(�ػ�)
	 */
	@Column(length = 1)
	private String playerstatus;
	
	/*
	 * ����豸����
	 * 0001�����ӻ�
	 * 0002��LED
	 */
	@Column(length = 4)
	private String extype;
	
	/*
	 * ����豸״̬
	 * 0������
	 * 1���쳣
	 */
	@Column(length = 1)
	private String exstatus;
	
	/*
	 * ����ʱ��
	 */
	@Column(length = 14, nullable = false)
	private String createtime;
	
	/*
	 * �ϱ�ʱ��
	 */
	@Column(length = 14)
	private String reporttime;
	
	/*
	 * ����״̬��0���½�1���ϴ����
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
  