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
	private String serialno; // ��ˮ��

	@Column(length=32, nullable = false)
	private String operatorid; // ����ԱId

	@Column(length=32, nullable = false)
	private String imessagegroupid; // imessagegroup���serialno
	
	@Column(length=1, nullable = false)
	private String approvestatus; // ����״̬��W�������� P������ͨ�� R������ 

	@Column(nullable = false)
	private int count; // �ò���Ա�����������ж�Ӧ�Ĵ�����1��ʼ�����ֵΪimessagegroup���approvetotalcount
	
	@Column(length=14)
	private String approvetime; // ����ʱ��
	
	@Column(length=100)
	private String denyReason; // ������ͨ��������
	
	@Column(length=32)
	private String grantorid; // ������Id

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
  