package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author lishuhua
 * Create on 2011-11-10
 * @version 1.0 
 * 播放列表会签操作表 
 */
@Entity
@Table(name = "playlistgroupsign")
public class PlaylistGroupSign {
	
	@Id
	@Column(length=32)
	private String serialno; // 流水号

	@Column(length=32, nullable = false)
	private String operatorid; // 操作员Id，会签人

	@Column(length=32, nullable = false)
	private String playlistgroupid; // Playlistgroup表的serialno
	
	@Column(length=1, nullable = false)
	private String approvestatus; // 审批状态：W：待审批 P：审批通过 R：驳回 

	@Column(length=14)
	private String approvetime; // 审批时间
	
	@Column(length=100)
	private String denyReason; // 审批不通过的理由

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getPlaylistgroupid() {
		return playlistgroupid;
	}

	public void setPlaylistgroupid(String playlistgroupid) {
		this.playlistgroupid = playlistgroupid;
	}

	public String getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(String approvestatus) {
		this.approvestatus = approvestatus;
	}

	public String getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}

	public String getDenyReason() {
		return denyReason;
	}

	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}
	
}
  