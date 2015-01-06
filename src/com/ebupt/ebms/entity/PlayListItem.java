package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 * 
 * changed by lishuhua
 * change time 2011-04-01
 * 播放任务表
 */
@Entity
@Table(name = "playlistitem")
public class PlayListItem {
	
	@Id
	@Column(length = 32)
	private String playlistitemid; 
	
	/* 播放列表详情id*/
	@Column(length = 32 , nullable = false)
	private String playlistid;
	
	/*
	 * 开始时间点-节目ID--循环次数-结束时间点。
	 * 如：早上5点到6点播放节目id为3的循环次数为3的节目。下午4点到4点半播放节目id为14的循环次数为0的节目。
	 * 0500-3-3-0600，1600-14-0-1630
	 */
	//@Lob
	@Column(length = 4000)
	private String playseq;
	
	/* 开始时间 例：0900、2100分别表示早、晚9点  */
	@Column(length = 4 , nullable = false)
	private String starttime;
	
	/* 结束时间，同开始时间  */
	@Column(length = 4 , nullable = false)
	private String endtime;
	
	/*
	 * 属于哪个子分屏的任务
	 */
	@Column(length = 32 , nullable = false)
	private String subtemplateid;

	public String getPlaylistitemid() {
		return playlistitemid;
	}
	
	// getter and setter...
	
	public void setPlaylistitemid(String playlistitemid) {
		this.playlistitemid = playlistitemid;
	}

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
	}

	public String getPlayseq() {
		return playseq;
	}

	public void setPlayseq(String playseq) {
		this.playseq = playseq;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getSubtemplateid() {
		return subtemplateid;
	}

	public void setSubtemplateid(String subtemplateid) {
		this.subtemplateid = subtemplateid;
	}
	
}
  