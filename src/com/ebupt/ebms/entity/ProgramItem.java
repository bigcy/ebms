package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 */
@Entity
@Table(name = "programitem")
public class ProgramItem {
	
	@Id
	@Column(length=32)
	private String programitemid; // 子节目id

	@Column(nullable = false)
	private String programid; // 节目id

	@Column(nullable = false)
	private int position; // 位置，第一个元素0，以此类推1、2

	@Column(length=32, nullable = false)
	private String contentid; // 内容ID

	@Column(length=1, nullable = false)
	private String type; // type : 视频类型 0 本地资源文件 1 网络或其他资源（此时src为完整播放url）

	@Column
	private int timelength; // 播放时间，以秒为单位。

	@Column(name="[loop]", length=2)
	private String loop; // 循环次数。

	@Column(length=3)
	private String fontsize; // 文字大小。

	@Column(length=7)
	private String fgcolor; // 文字颜色，如：#FF6600
	
	@Column(length=7)
	private String bgcolor; // 文字颜色，如：#FF6600

	@Column(length=50)
	private String fontname; // 字体类型，如：simhei.ttf

	@Column(length=2)
	private String showspeed; // 滚动速度，0为不滚动

	@Column(length=10)
	private String showmode; // 滚动模式 up 右下至上 left 由右至左 图片转场效果转场模式 0 无 1 淡入效果 2 飞入效果 3 百叶窗效果 4 推入效果 5 推入 6 Z字效果 7 水平开门效果8 垂直开门效果 9 水平边缘模糊 宽 10 水平边缘模糊 普通 11 水平边缘模糊 窄12 垂直边缘模糊 宽 13 垂直边缘模糊  普通14 垂直边缘模糊  窄 15 十字交叉效果 16 金刚石效果17 马赛克效果
	
	/*
	 * YYYY年MM月DD日 星期WK hh:mm:ss
	 */
	@Column(length=30)
	private String timeformat;
	
	public String getFgcolor() {
		return fgcolor;
	}

	public void setFgcolor(String fgcolor) {
		this.fgcolor = fgcolor;
	}

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getFontname() {
		return fontname;
	}

	public void setFontname(String fontname) {
		this.fontname = fontname;
	}

	public String getShowspeed() {
		return showspeed;
	}

	public void setShowspeed(String showspeed) {
		this.showspeed = showspeed;
	}

	public String getShowmode() {
		return showmode;
	}

	public void setShowmode(String showmode) {
		this.showmode = showmode;
	}

	public String getProgramitemid() {
		return programitemid;
	}

	public void setProgramitemid(String programitemid) {
		this.programitemid = programitemid;
	}

	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTimelength() {
		return timelength;
	}

	public void setTimelength(int timelength) {
		this.timelength = timelength;
	}

	public String getLoop() {
		return loop;
	}

	public void setLoop(String loop) {
		this.loop = loop;
	}

	public String getFontsize() {
		return fontsize;
	}

	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}

	public String getTimeformat() {
		return timeformat;
	}

	public void setTimeformat(String timeformat) {
		this.timeformat = timeformat;
	}
	
}