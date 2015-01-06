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
//素材下载状态报告
@Entity
@Table(name = "contentdownstatus")
public class ContentDownStatus {
		
	/*
	 * 流水号
	 */
	@Id
//	@GeneratedValue(generator = "system-uuid")
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String serialno;
	
	/*
	 * 终端id
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * 素材ID
	 */
	@Column(length = 32,nullable=false)
	private String contentid;
	
	/*
	 * 资源名称
	 */
	@Column(length = 128)
	private String contenttitle;
	
	/*
	 * 播放任务ID
	 */
	@Column(length = 32,nullable=false)
	private String playlistid;
	
	/*
	 * 接收时间
	 */
	@Column(length = 14,nullable=true)
	private String recvtime;
	
	/*
	 * 下载时间
	 */
	@Column(length = 19)
	private String downtime;
	
	/*
	 * 素材文件下载状态
	 * 0001：下载完成
	 * 01XX：下载失败
	 * 0101：源文件不存在
	 * 0102：下载超时
	 * 0103：账号权限错误
	 * 0104：文件md5校验错误
	 * 0105：连接超时
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
  