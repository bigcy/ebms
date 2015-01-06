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
@Table(name = "termconfiggroup")
public class TermConfigGroup {
	
	@Id
	@Column(length=32)
	private String serialno; // 流水号

	@Column(length=32, nullable = false)
	private String termconfigid; // 配置信息id

	@Column(length=32, nullable = false)
	private String groupid; // 群组ID

	@Column(length=14, nullable = false)
	private String createtime; // 创建时间

	@Column(length=8, nullable = false)
	private String validdate; // 生效日期YYYYMMDD，立即生效存当前日期

	@Column(length=1, nullable = false)
	private String status; // 任务状态：N:等待执行 R:运行 P:暂停 C:撤销 E:执行结束

	@Column(length=1, nullable = false)
	private String approvestatus; // 审批状态：W：待审批 P：审批通过 R：审批不通过 

	@Column(length=14)
	private String approvetime; // 审批时间

	@Column(length=32)
	private String approver; // 审批者

	@Column(length=100)
	private String denyReason; // 审批不通过的理由

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getTermconfigid() {
		return termconfigid;
	}

	public void setTermconfigid(String termconfigid) {
		this.termconfigid = termconfigid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getValiddate() {
		return validdate;
	}

	public void setValiddate(String validdate) {
		this.validdate = validdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getDenyReason() {
		return denyReason;
	}

	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}
}
  