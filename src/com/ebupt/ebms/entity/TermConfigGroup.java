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
	private String serialno; // ��ˮ��

	@Column(length=32, nullable = false)
	private String termconfigid; // ������Ϣid

	@Column(length=32, nullable = false)
	private String groupid; // Ⱥ��ID

	@Column(length=14, nullable = false)
	private String createtime; // ����ʱ��

	@Column(length=8, nullable = false)
	private String validdate; // ��Ч����YYYYMMDD��������Ч�浱ǰ����

	@Column(length=1, nullable = false)
	private String status; // ����״̬��N:�ȴ�ִ�� R:���� P:��ͣ C:���� E:ִ�н���

	@Column(length=1, nullable = false)
	private String approvestatus; // ����״̬��W�������� P������ͨ�� R��������ͨ�� 

	@Column(length=14)
	private String approvetime; // ����ʱ��

	@Column(length=32)
	private String approver; // ������

	@Column(length=100)
	private String denyReason; // ������ͨ��������

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
  