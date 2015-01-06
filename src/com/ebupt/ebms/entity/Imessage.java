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
 * changed time 2011-04-01
 */
@Entity
@Table(name = "imessage")
public class Imessage {
	
	@Id
	@Column(length=32)
	private String messageid; // 消息id

	@Column(length=4)
	private String fontsize; // 字体大小

	@Column(length=7)
	private String bgcolor; // 背景色#FFFFFF

	@Column(length=7)
	private String fontcolor; // 字体颜色#FFFFFF

	@Column(length=1)
	private String speed; // 滚动速度0：慢；1：正常；2快

	@Column(length=5)
	private String timelength; // 播放时长，单位（s）
	
	@Column(length=14,nullable=false)
	private String createtime;
	
	/*
	 * 播放次数，跟播放时长互斥
	 */
	@Column(length=5)
	private String count; 

	@Column(length=255, nullable = false)
	private String message; // 消息内容

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getFontsize() {
		return fontsize;
	}

	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getFontcolor() {
		return fontcolor;
	}

	public void setFontcolor(String fontcolor) {
		this.fontcolor = fontcolor;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getTimelength() {
		return timelength;
	}

	public void setTimelength(String timelength) {
		this.timelength = timelength;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
  