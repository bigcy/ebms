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
 */
@Entity
@Table(name = "template")
public class Template {
	
	@Id
	@Column(length=32)
	private String templateid; // ģ��Id

	@Column(length=32, nullable = false)
	private String name; // ģ������

	@Column(length=100)
	private String path; // ģ���ļ�·��������ͼ��/thumb/����

	@Column(length=14, nullable = false)
	private String createtime; // ����ʱ��

	@Column(length=4, nullable = false)
	private String width; // ��ȣ�����

	@Column(length=4, nullable = false)
	private String height; // �߶ȣ�����

	@Column(length=100)
	private String tplDescPath; // ģ���ļ���Ӧ�����ļ�·��

	/*
	 * ģ��������Ϣ
	 */
	@Column(length=100)
	private String description;
	
	/*
	 * ����ʱ��
	 */
	@Column(length=14)
	private String approvetime;
	
	/*
	 * ������
	 */
	@Column(length=32)
	private String approver;
	
	/*
	 * ״̬
	 * W��������
	 * P������ͨ�� 
	 * R��������ͨ�� 
	 */
	@Column(length=1)
	private String status;
	
	/*
	 * ������ͨ��������
	 */
	@Column(length=100)
	private String denyReason;
	
	@Column(nullable = false)
	private int usedcount = 0;//ʹ�ô���
	
	/*
	 * �Ƿ񹫿�  T��������True��F����������False��
	 */
	@Column(length=1,nullable = false)
	private String isopen;

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getTplDescPath() {
		return tplDescPath;
	}

	public void setTplDescPath(String tplDescPath) {
		this.tplDescPath = tplDescPath;
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
	
}
  