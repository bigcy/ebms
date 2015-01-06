package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0 
 * 
 *  changed by lishuhua
 *  change time 2011-04-01
 *  ��Ŀ��
 */
@Entity
@Table(name = "program")
public class Program {
	
	@Id
	@Column(length=32)
	private String programid; // ��Ŀid

	@Column(length=32, nullable = false)
	private String name; // ��Ŀ����

	@Column(length=10, nullable = false)
	private String type; // ����video-��Ƶ��pic-ͼƬ��audio-��Ƶ��text-�ı���led-LED������realdata-ʵʱ����
	
	@Column(length=14, nullable = false)
	private String createtime; // ��������
	
	@Column(length=1, nullable =false)
	private String issystem; // �Ƿ���ϵͳ���� �� 1���ǡ�0�����ǡ������1�����ֵ�ʱ����ʾ���û� �� ϵͳ���������ʱ����á�

	@Column(nullable = false)
	private int timelength; // ʱ�䳤�ȣ���λ��
	
	@Column(length=255)
	private String description; // ��Ŀ������
	
	@Column(length=14)
	private String approvetime; // ����ʱ��

	@Column(length=32)
	private String approver; // ������

	@Column(length=1)
	private String status; // ״̬ W��������  P������ͨ��  R��������ͨ�� 

	@Column(length=100)
	private String denyReason; // ������ͨ��������
	
	@Column(nullable = false)
	private int usedcount = 0;//ʹ�ô���
	
	@Column(length=1,nullable = false)
	private String isopen;//�Ƿ񹫿�T��������True��F����������False��

	@Column(length = 14, nullable = true)
	private String expirydate = "20201231";//��Ŀ��Ч�ڣ������ز�����Ч���Զ�����
	
	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIssystem() {
		return issystem;
	}

	public void setIssystem(String issystem) {
		this.issystem = issystem;
	}

	public int getTimelength() {
		return timelength;
	}

	public void setTimelength(int timelength) {
		this.timelength = timelength;
	}

	public int getUsedcount() {
		return usedcount;
	}

	public void setUsedcount(int usedcount) {
		this.usedcount = usedcount;
	}

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	
}
  