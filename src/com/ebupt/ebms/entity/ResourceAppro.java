package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author
 * Create on 2014-02-13
 * @version 1.0
 * 资源审批流程表  
 */
@Entity
@Table(name = "resourceappro")
public class ResourceAppro {
	
	@Id
	@Column(length=32)
	private String serialno; // 资源审批流程id

	@Column(length=32)
	private String operatorid; // 申请人id

	@Column(length=32)
	private String resourceid; // 媒介资源id
	
	@Column(length=1)
	private String approvestatus; // 审批状态：W：待审批 P：审批通过 R：驳回 

	@Column(length=100)
	private String denyReason; // 审批意见
	
	@Column(length=14)
	private String createtime; // 创建审批时间
	
	@Column(length=4)
	private int count; // 记录审批的先后次序
	
	@Column(length=14)
	private String approvetime; // 审批时间
	
	@Column(length=32)
	private String deliresourceid; // 发布计划id

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

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public String getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(String approvestatus) {
		this.approvestatus = approvestatus;
	}

	public String getDenyReason() {
		return denyReason;
	}

	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(String approvetime) {
		this.approvetime = approvetime;
	}

	public String getDeliresourceid() {
		return deliresourceid;
	}

	public void setDeliresourceid(String deliresourceid) {
		this.deliresourceid = deliresourceid;
	}

}
  