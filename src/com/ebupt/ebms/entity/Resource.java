package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author 
 * Create on 2014-02-13
 * @version 1.0
 * 媒介资源表  
 */
@Entity
@Table(name = "resource")
public class Resource {
	
	@Id
	@Column(length=32)
	private String resourceid; // 媒介资源id

	@Column(length=50)
	private String operatorname; // 申请人

	@Column(name="[group]",length=32)
	private String group; // 申请部门 

	@Column(name="[project]",length=100)
	private String  project; // 项目名称
	
	@Column(length=10)
	private String startdate; // 媒体上刊日期

	@Column(length=10)
	private String  enddate; // 媒体下刊日期

	@Column(length=100)
	private String media; // 媒体形式
	
	@Column(length=32)
	private String cost; // 广告费用
	
	@Column(length=14)
	private String createtime; // 创建时间

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
  