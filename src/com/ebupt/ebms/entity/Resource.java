package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author 
 * Create on 2014-02-13
 * @version 1.0
 * ý����Դ��  
 */
@Entity
@Table(name = "resource")
public class Resource {
	
	@Id
	@Column(length=32)
	private String resourceid; // ý����Դid

	@Column(length=50)
	private String operatorname; // ������

	@Column(name="[group]",length=32)
	private String group; // ���벿�� 

	@Column(name="[project]",length=100)
	private String  project; // ��Ŀ����
	
	@Column(length=10)
	private String startdate; // ý���Ͽ�����

	@Column(length=10)
	private String  enddate; // ý���¿�����

	@Column(length=100)
	private String media; // ý����ʽ
	
	@Column(length=32)
	private String cost; // ������
	
	@Column(length=14)
	private String createtime; // ����ʱ��

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
  