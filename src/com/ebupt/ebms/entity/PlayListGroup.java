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
@Table(name = "playlistgroup")
public class PlayListGroup {
	
	@Id
	@Column(length=32)
	private String serialno; // ��ˮ��

	@Column(length=32, nullable = false)
	private String playlistid; // ��������id

	@Column(length=32, nullable = false)
	private String groupid; // Ⱥ��ID
	
	@Column(nullable = false)
	private int priority; // ���ȼ���ֵԽС���ȼ�Խ��,0-4,5-9

	@Column(length=14, nullable = false)
	private String createtime; // ����ʱ��
	
	@Column(length=8, nullable = false)
	private String startdate; // ��ʼ����YYYYMMDD
	
	@Column(length=8, nullable = false)
	private String enddate; // ��������YYYYMMDD

	@Column(length=1, nullable = false)
	private String status; // ����״̬��N:�ȴ�ִ�� R:���� P:��ͣ C:���� E:ִ�н���

	@Column(length=1)
	private String approvestatus; // ����״̬��N����ʼ�� W�������� P������ͨ�� R��������ͨ�� 

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

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
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
  