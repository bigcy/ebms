package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author QiChen Create on 2011-3-4
 * @version 1.0
 * 
 * changed by lishuhua
 * change time 2011-04-01
 * ��Դ��
 */
@Entity
@Table(name = "content")
public class Content {
	
	@Id
	@Column(length = 32)
	private String contentid; // ������ԴId
	
	@Column(length = 255, nullable = false)
	private String contenttitle; // ��Դ����
	
	@Column(length = 10, nullable = false)
	private String type; // ����video-��Ƶ��pic-ͼƬ��audio-��Ƶ��text-�ı���ppt-�õ�Ƭ��led-LED������realdata-ʵʱ����
	
	@Column(length = 255, nullable = false)
	private String path; // �������ͣ��洢���ݶ�Ӧ��·��������ͼ��thumb����
	
	@Column(length = 14, nullable = false)
	private String createtime; // ����ʱ��
	
	@Column(length = 5, nullable = false)
	private String length; // ����Ƶ����Ӧ���ݲ���ʱ��,��λ��s),�ı�����Ӧ���ֳ��ȡ�
	
	@Column(length = 32, nullable = false)
	private String md5; // ��Դmd5ֵ
	
	/*
	 * �ز�������Ϣ
	 */
	@Column(length = 255, nullable = true)
	private String description;
	
	/*
	 * ����ʱ��
	 */
	@Column(length = 14, nullable = true)
	private String approvetime; 
	
	/*
	 * ������
	 */
	@Column(length = 32, nullable = true)
	private String approver;
	
	/*
	 * ״̬
	 * W��������
	 * P������ͨ��
	 * R��������ͨ�� 
	 */
	@Column(length = 1, nullable = false)
	private String status;
	
	/*
	 * ������ͨ��������
	 */
	@Column(length = 100, nullable = true)
	private String denyReason;
	
	@Column(nullable = false)
	private int usedcount = 0;//ʹ�ô���
	
	/*
	 * �Ƿ񹫿�   T��������True��F����������False��
	 */
	@Column(length = 1, nullable = false)
	private String isopen;
	
	/*
	 * �ز���Ч��Ĭ��һֱ��Ч
	 * 2012-11-26
	 */
	@Column(length = 14, nullable = true)
	private String expirydate = "20201231";

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getContenttitle() {
		return contenttitle;
	}

	public void setContenttitle(String contenttitle) {
		this.contenttitle = contenttitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
