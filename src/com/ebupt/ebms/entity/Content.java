package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author QiChen Create on 2011-3-4
 * @version 1.0
 * 
 * changed by lishuhua
 * change time 2011-04-01
 * 资源表
 */
@Entity
@Table(name = "content")
public class Content {
	
	@Id
	@Column(length = 32)
	private String contentid; // 内容资源Id
	
	@Column(length = 255, nullable = false)
	private String contenttitle; // 资源名称
	
	@Column(length = 10, nullable = false)
	private String type; // 包括video-视频、pic-图片、audio-音频、text-文本、ppt-幻灯片、led-LED屏任务、realdata-实时数据
	
	@Column(length = 255, nullable = false)
	private String path; // 其他类型，存储内容对应的路径，缩略图在thumb下面
	
	@Column(length = 14, nullable = false)
	private String createtime; // 创建时间
	
	@Column(length = 5, nullable = false)
	private String length; // 音视频广告对应内容播放时长,单位（s),文本广告对应文字长度。
	
	@Column(length = 32, nullable = false)
	private String md5; // 资源md5值
	
	/*
	 * 素材描述信息
	 */
	@Column(length = 255, nullable = true)
	private String description;
	
	/*
	 * 审批时间
	 */
	@Column(length = 14, nullable = true)
	private String approvetime; 
	
	/*
	 * 审批者
	 */
	@Column(length = 32, nullable = true)
	private String approver;
	
	/*
	 * 状态
	 * W：待审批
	 * P：审批通过
	 * R：审批不通过 
	 */
	@Column(length = 1, nullable = false)
	private String status;
	
	/*
	 * 审批不通过的理由
	 */
	@Column(length = 100, nullable = true)
	private String denyReason;
	
	@Column(nullable = false)
	private int usedcount = 0;//使用次数
	
	/*
	 * 是否公开   T：公开（True）F：不公开（False）
	 */
	@Column(length = 1, nullable = false)
	private String isopen;
	
	/*
	 * 素材有效，默认一直有效
	 * 2012-11-26
	 */
	@Column(length = 14, nullable = true)
	private String expirydate = "20201231";

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getContenttitle() {
		return contenttitle;
	}

	public void setContenttitle(String contenttitle) {
		this.contenttitle = contenttitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDenyReason() {
		return denyReason;
	}

	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	public int getUsedcount() {
		return usedcount;
	}

	public void setUsedcount(int usedcount) {
		this.usedcount = usedcount;
	}

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	} 
	

}
