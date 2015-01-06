package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author
 * Create on 2014-02-13
 * @version 1.0
 * ��Դ�������̱�  
 */
@Entity
@Table(name = "resourceappro")
public class ResourceAppro {
	
	@Id
	@Column(length=32)
	private String serialno; // ��Դ��������id

	@Column(length=32)
	private String operatorid; // ������id

	@Column(length=32)
	private String resourceid; // ý����Դid
	
	@Column(length=1)
	private String approvestatus; // ����״̬��W�������� P������ͨ�� R������ 

	@Column(length=100)
	private String denyReason; // �������
	
	@Column(length=14)
	private String createtime; // ��������ʱ��
	
	@Column(length=4)
	private int count; // ��¼�������Ⱥ����
	
	@Column(length=14)
	private String approvetime; // ����ʱ��
	
	@Column(length=32)
	private String deliresourceid; // �����ƻ�id

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
  