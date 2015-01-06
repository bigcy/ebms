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
@Table(name = "termconfigtaskitem")
public class TermConfigTaskItem {
	
	@Id
	@Column(length=32)
	private String serialno; // ��ˮ��

	@Column(length=32, nullable = false)
	private String termconfigid; // ������Ϣid����ͬ���������

	@Column(length=32, nullable = false)
	private String terminalid; // �ն�Id��

	@Column(length=4, nullable = false)
	private String status; // ����״̬��0���½�0000����������֪ͨ���ն��������������������0001�����óɹ�0002������ʧ��

	@Column(length=14, nullable = false)
	private String createtime; // ����ʱ��

	@Column(length=14)
	private String recvtime; // ����ʱ��

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getTermconfigid() {
		return termconfigid;
	}

	public void setTermconfigid(String termconfigid) {
		this.termconfigid = termconfigid;
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
  