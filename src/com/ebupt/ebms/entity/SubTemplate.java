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
 */
@Entity
@Table(name = "subtemplate")
public class SubTemplate {
	
	@Id
	@Column(length=32)
	private String subtemplateid; // 子项ID

	@Column(length=100, nullable = true)
	private String description; // 子模板描述信息

	@Column(length=10, nullable = false)
	private String type; //包括video-视频、pic-图片、audio-音频、text-文本、led-LED屏任务、realdata-实时数据、weather-天气预报、clock-时钟、countdown-倒计时

	@Column(length=50, nullable = false)
	private String position; // X,Y,W,H坐标 X 起点X坐标Y 起点Y坐标W 宽度H 高度

	@Column(length=32, nullable = false)
	private String templateid; // 所属模板

	@Column(length=32)
	private String mplname; // Mpl文件的名称，后台使用。模板切换时节目单需要能继承，所以这里定义的名字要能重用，而且要跟模板表中tplDescPath里面的mpl名字一致。
	
	@Column(length = 3)
	private String alpha; // 图层透明度设置，0-透明，255-不透明
	
	@Column(length = 1)
	private String hide; // 区域是否隐藏；0-显示 1-隐藏
	
	@Column(length = 2)
	private String zlevel; // z轴值，表示该区域在整个分屏样式的图层优先级，值越大，显示优先级越高。
	
	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}

	public String getZlevel() {
		return zlevel;
	}

	public void setZlevel(String zlevel) {
		this.zlevel = zlevel;
	}

	public String getSubtemplateid() {
		return subtemplateid;
	}

	public void setSubtemplateid(String subtemplateid) {
		this.subtemplateid = subtemplateid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getMplname() {
		return mplname;
	}

	public void setMplname(String mplname) {
		this.mplname = mplname;
	}
}
  