package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create on 2011-04-06
 * @version 1.0  
 * 播放列表操作员关系表
 */
@Entity
@Table(name = "playlistoperator")
public class PlayListOperator {
	
	@Id
	@Column(length=32)
	private String serialno;

	@Column(length=32, nullable = false)
	private String playlistid;

	@Column(length=32, nullable = false)
	private String operatorid;

	@Column(length=1, nullable = false)
	private String type;
	
	@Column(length=32)
	private String sharers;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSharers() {
		return sharers;
	}

	public void setSharers(String sharers) {
		this.sharers = sharers;
	}

}
  