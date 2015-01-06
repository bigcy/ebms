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
	private String serialno; // ��ˮ��

	@Column(length=32, nullable = false)
	private String terminalid; // �ն�id

	@Column(length=32)
	private String softinfoid; // �����ϢID������id

	@Column(length=8 , nullable = false)
	private String version; // ��ǰ�İ汾��

	@Column(length=14, nullable = false)
	private String createtime; // ����ʱ��

	@Column(length=14)
	private String recvtime; // ״̬�ϱ�ʱ��

	@Column(length=4)
	private String status; // ����״̬��0���½�0000����������֪ͨ0001�������ɹ����ն��������������������󷵻أ�01XX������ʧ��0101��Դ�ļ�������0102�����س�ʱ0103���˺�Ȩ�޴���0104���ļ�md5У�����0105�����ӳ�ʱ

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
  