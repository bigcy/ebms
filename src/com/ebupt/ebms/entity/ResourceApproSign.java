package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author
 * Create on 2014-02-13
 * @version 1.0 
 * ��Դ��ǩ��
 */
@Entity
@Table(name = "resourceapprosign")
public class ResourceApproSign {
	
	@Id
	@Column(length=32)
	private String serialno; // ��ǩ����id

	@Column(length=32)
	private String resourceid; // ý����Դid

	@Column(length=32)
	private String approverid; // ��ǩ��id
	
	@Column(length=1)
	private String approvestatus; // ��ǩ״̬��U��δ��ǩP������ͨ�� R��������ͨ��

	@Column(length=14)
	private String approvetime; // ��ǩʱ��
	
	@Column(length=50)
	private String denyReason; // ����ԭ��

	@Column(length=32)
	private String signerid; // ��ǩ��id
	
	@Column(length=14)
	private String createtime; // ý����Դ����ʱ��
	
	@Column(length=32)
	private String deliresourceid; // �����ƻ�id

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
  