package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "imessagegroupappro")
public class ImessageGroupAppro {
	
	@Id
	@Column(length=32)
	private String serialno; // 流水号

	@Column(length=32, nullable = false)
	private String operatorid; // 操作员Id

	@Column(length=32, nullable = false)
	private String imessagegroupid; // imessagegroup表的serialno
	
	@Column(length=1, nullable = false)
	private String approvestatus; // 审批状态：W：待审批 P：审批通过 R：驳回 

	@Column(nullable = false)
	private int count; // 该操作员在审批流程中对应的次数从1开始，最大值为imessagegroup表的approvetotalcount
	
	@Column(length=14)
	private String approvetime; // 审批时间
	
	@Column(length=100)
	private String denyReason; // 审批不通过的理由
	
	@Column(length=32)
	private String grantorid; // 赋予者Id

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


	public String getImessagegroupid() {
		return imessagegroupid;
	}

	public void setImessagegroupid(String imessagegroupid) {
		this.imessagegroupid = imessagegroupid;
	}

	public String getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(String approvestatus) {
		this.approvestatus = approvestatus;
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

	public String getDenyReason() {
		return denyReason;
	}

	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	public String getGrantorid() {
		return grantorid;
	}

	public void setGrantorid(String grantorid) {
		this.grantorid = grantorid;
	}
	
	
}
  