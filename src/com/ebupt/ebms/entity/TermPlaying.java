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

//在播内容上报
@Entity
@Table(name = "termplaying")
public class TermPlaying {
	
	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;

	/*
	 * 终端id
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * 任务id
	 */
	@Column(length = 32,nullable=false)
	private String taskid;
	
	/*
	 * 播放任务id
	 */
	@Column(length = 32)
	private String playlistid;
	
	/*
	 * 抓取时间点YYMMDDHHMMSS
	 */
	@Column(length = 14)
	private String time;
	
	/*
	 * 素材id
	 */
	@Column(length = 32,nullable=true)
	private String contentid;
	
	/*
	 * 已播时间，单位：s
	 */
	@Column(length = 5,nullable=true)
	private String durtime;
	
	/*
	 * 抓屏图片在服务器上的绝对路径
	 */
	@Column(length = 255,nullable=true)
	private String picture;
	
	/**
	 * 创建时间
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/**
	 * 上报时间
	 */
	@Column(length = 14,nullable=true)
	private String recvtime;
	
	/*
	 * 任务状态：0：新建1：上传完成
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
  