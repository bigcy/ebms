package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 * ����������Ⱥ���Ӧ��ϵ��
 */
@Entity
@Table(name ="imessagegroup")
public class ImessageGroup {
	
	@Id
	@Column(length=32)
	private String serialno; // ��ˮ��

	@Column(length=32, nullable = false)
	private String messageid; // ��ʱ��Ϣid

	@Column(length=32, nullable = false)
	private String groupid; // Ⱥ��ID

	@Column(length=14, nullable = false)
	private String createtime; // ����ʱ��

	@Column(length=1, nullable = false)
	private String approvestatus; // ����״̬��W�������� P������ͨ�� R��������ͨ�� 

	@Column(nullable = false)
	private int approvetotalcount; // ��Ҫ�������ܴ���

	@Column(nullable = false)
	private int approvedcount; // ��ǰ���������������ڼ����������ȣ���ʼֵΪ0
	
	@Column(length = 32, nullable = false)
	private String operatorid; // ����ԱId
	
	@Column(length = 1)
	private String readstatus = "N"; // �Ķ�״̬��Y���ģ�Nδ�ģ�Ĭ��Ϊδ��
		
	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
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

	public String getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(String approvestatus) {
		this.approvestatus = approvestatus;
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

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}

}
  