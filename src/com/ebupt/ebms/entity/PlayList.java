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
 * changed by lishuhua
 * change time 2011-04-01
 * ���������
 */
@Entity
@Table(name = "playlist")
public class PlayList {
	
	@Id
	@Column(length=32)
	private String playlistid; // ��������id

	@Column(length=255, nullable = false)
	private String name; // ������������

	@Column(length=4, nullable = false)
	private String starttime; // ��ʼʱ�� ����0900��2100�ֱ��ʾ�硢��9��HHMM

	@Column(length=4, nullable = false)
	private String  endtime; // ����ʱ�� ����ͬ��ʼʱ��
	
	@Column(length=8, nullable = true)
	private String startdate; // ��ʼ����YYYYMMDD

	@Column(length=8, nullable = true)
	private String  enddate; // ��������YYYYMMDD

	@Column(length=32, nullable = false)
	private String templateid; // �����Ӧ��ģ��
	
	@Column(length=14, nullable = false)
	private String createtime; // ��������
	
	@Column(length=255)
	private String description; // ����
	
	@Column(length=14)
	private String approvetime; // ����ʱ��

	@Column(length=32)
	private String approver; // ������

	@Column(length=1)
	private String status; // �����б�״̬N����ʼ�� W�������� P������ͨ�� R��������ͨ�� 

	@Column(length=100)
	private String denyReason; // ������ͨ��������
	
	@Column(nullable = false)
	private int usedcount = 0;//ʹ�ô���
	
	@Column(length = 1,nullable = false)
	private String isopen;//�Ƿ񹫿�T��������True)F����������False��
	
	@Column(length = 1,nullable = false)
	private String type;//������ʽ 0��excel����  1���ֶ����

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
  