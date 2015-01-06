package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author
 * Create on 2014-02-13
 * @version 1.0 
 * 资源会签表
 */
@Entity
@Table(name = "resourceapprosign")
public class ResourceApproSign {
	
	@Id
	@Column(length=32)
	private String serialno; // 会签流程id

	@Column(length=32)
	private String resourceid; // 媒介资源id

	@Column(length=32)
	private String approverid; // 会签人id
	
	@Column(length=1)
	private String approvestatus; // 会签状态：U：未会签P：审批通过 R：审批不通过

	@Column(length=14)
	private String approvetime; // 会签时间
	
	@Column(length=50)
	private String denyReason; // 驳回原因

	@Column(length=32)
	private String signerid; // 会签人id
	
	@Column(length=14)
	private String createtime; // 媒介资源创建时间
	
	@Column(length=32)
	private String deliresourceid; // 发布计划id

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public String getApproverid() {
		return approverid;
	}

	public void setApproverid(String approverid) {
		this.approverid = approverid;
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

	public String getSignerid() {
		return signerid;
	}

	public void setSignerid(String signerid) {
		this.signerid = signerid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDeliresourceid() {
		return deliresourceid;
	}

	public void setDeliresourceid(String deliresourceid) {
		this.deliresourceid = deliresourceid;
	}
	
}
  