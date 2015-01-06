package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-3-4
 * @version 1.0  
 */
//�ز�����״̬����
@Entity
@Table(name = "contentdownstatus")
public class ContentDownStatus {
		
	/*
	 * ��ˮ��
	 */
	@Id
//	@GeneratedValue(generator = "system-uuid")
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String serialno;
	
	/*
	 * �ն�id
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * �ز�ID
	 */
	@Column(length = 32,nullable=false)
	private String contentid;
	
	/*
	 * ��Դ����
	 */
	@Column(length = 128)
	private String contenttitle;
	
	/*
	 * ��������ID
	 */
	@Column(length = 32,nullable=false)
	private String playlistid;
	
	/*
	 * ����ʱ��
	 */
	@Column(length = 14,nullable=true)
	private String recvtime;
	
	/*
	 * ����ʱ��
	 */
	@Column(length = 19)
	private String downtime;
	
	/*
	 * �ز��ļ�����״̬
	 * 0001���������
	 * 01XX������ʧ��
	 * 0101��Դ�ļ�������
	 * 0102�����س�ʱ
	 * 0103���˺�Ȩ�޴���
	 * 0104���ļ�md5У�����
	 * 0105�����ӳ�ʱ
	 */
	@Column(length = 4,nullable=false)
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

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
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

	public String getDowntime() {
		return downtime;
	}

	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}

	public String getContenttitle() {
		return contenttitle;
	}

	public void setContenttitle(String contenttitle) {
		this.contenttitle = contenttitle;
	}
	
}
  