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

//�ڲ������ϱ�
@Entity
@Table(name = "termplaying")
public class TermPlaying {
	
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
	 * ��������id
	 */
	@Column(length = 32)
	private String playlistid;
	
	/*
	 * ץȡʱ���YYMMDDHHMMSS
	 */
	@Column(length = 14)
	private String time;
	
	/*
	 * �ز�id
	 */
	@Column(length = 32,nullable=true)
	private String contentid;
	
	/*
	 * �Ѳ�ʱ�䣬��λ��s
	 */
	@Column(length = 5,nullable=true)
	private String durtime;
	
	/*
	 * ץ��ͼƬ�ڷ������ϵľ���·��
	 */
	@Column(length = 255,nullable=true)
	private String picture;
	
	/**
	 * ����ʱ��
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/**
	 * �ϱ�ʱ��
	 */
	@Column(length = 14,nullable=true)
	private String recvtime;
	
	/*
	 * ����״̬��0���½�1���ϴ����
	 */
	@Column(length = 4,nullable=false)
	private String status;
	
	@Column(length = 50)
	private String position;

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getDurtime() {
		return durtime;
	}

	public void setDurtime(String durtime) {
		this.durtime = durtime;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
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

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


}
  