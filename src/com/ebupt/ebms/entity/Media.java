package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 
 * Create on 2014-02-13
 * @version 1.0
 * ý����ʽ��
 */
@Entity
@Table(name = "media")
public class Media {
	
	@Id
	@Column(length = 32)
	private String mediaid; // ý����ʽid
	
	@Column(length = 32)
	private String medianame; // ý������
	
	@Column(length = 14)
	private String createtime; // ����ʱ��

	public String getMediaid() {
		return mediaid;
	}

	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}

	public String getMedianame() {
		return medianame;
	}

	public void setMedianame(String medianame) {
		this.medianame = medianame;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
