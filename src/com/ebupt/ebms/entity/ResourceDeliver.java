package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author
 * Create on 2014-02-13
 * @version 1.0
 * 发布计划表
 */
@Entity
@Table(name = "resourcedeliver")
public class ResourceDeliver {
	
	@Id
	@Column(length=32)
	private String deliresourceid; // 发布计划id

	@Column(length=32)
	private String resourceid; // 媒介资源id

	@Column(length=10)
	private String delistartdate; // 排期开始时间
	
	@Column(length=10)
	private String delienddate; // 排期结束时间

	@Column(length=14)
	private String createtime; // 创建发布计划时间
	
	@Column(length=100)
	private String path; // 保存广告图片路径
	
	@Column(length=100)
	private String description; // 广告文案内容

	@Column(length=1)
	private String status; // 发布计划状态：W：待审批 P：审批通过 R：审批不通过

	@Column(length=50)
	private String operatorname; // 发布计划的操作员

	@Column(length=50)
	private int src; // 物料来源

	@Column(length=4)
	private int approvetotalcount; // 需要审批的总次数
	
	@Column(length=4)
	private int approvedcount; // 已审批的次数
	
	@Column(length=10)
	private int approveddate; // 通过审批的日期
	
	@Column(length = 1)
	private String readstatus = "N"; // 阅读状态：Y已阅，N未阅，默认为未阅

	public String getDeliresourceid() {
		return deliresourceid;
	}

	public void setDeliresourceid(String deliresourceid) {
		this.deliresourceid = deliresourceid;
	}

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public String getDelistartdate() {
		return delistartdate;
	}

	public void setDelistartdate(String delistartdate) {
		this.delistartdate = delistartdate;
	}

	public String getDelienddate() {
		return delienddate;
	}

	public void setDelienddate(String delienddate) {
		this.delienddate = delienddate;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getApprovetotalcount() {
		return approvetotalcount;
	}

	public void setApprovetotalcount(int approvetotalcount) {
		this.approvetotalcount = approvetotalcount;
	}

	public int getApprovedcount() {
		return approvedcount;
	}

	public void setApprovedcount(int approvedcount) {
		this.approvedcount = approvedcount;
	}

	public int getApproveddate() {
		return approveddate;
	}

	public void setApproveddate(int approveddate) {
		this.approveddate = approveddate;
	}

	public String getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}

}
  