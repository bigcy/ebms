package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author
 * Create on 2014-02-13
 * @version 1.0
 * �����ƻ���
 */
@Entity
@Table(name = "resourcedeliver")
public class ResourceDeliver {
	
	@Id
	@Column(length=32)
	private String deliresourceid; // �����ƻ�id

	@Column(length=32)
	private String resourceid; // ý����Դid

	@Column(length=10)
	private String delistartdate; // ���ڿ�ʼʱ��
	
	@Column(length=10)
	private String delienddate; // ���ڽ���ʱ��

	@Column(length=14)
	private String createtime; // ���������ƻ�ʱ��
	
	@Column(length=100)
	private String path; // ������ͼƬ·��
	
	@Column(length=100)
	private String description; // ����İ�����

	@Column(length=1)
	private String status; // �����ƻ�״̬��W�������� P������ͨ�� R��������ͨ��

	@Column(length=50)
	private String operatorname; // �����ƻ��Ĳ���Ա

	@Column(length=50)
	private int src; // ������Դ

	@Column(length=4)
	private int approvetotalcount; // ��Ҫ�������ܴ���
	
	@Column(length=4)
	private int approvedcount; // �������Ĵ���
	
	@Column(length=10)
	private int approveddate; // ͨ������������
	
	@Column(length = 1)
	private String readstatus = "N"; // �Ķ�״̬��Y���ģ�Nδ�ģ�Ĭ��Ϊδ��

	public String getDeliresourceid() {
		return deliresourceid;
	}

	public void setDeliresourceid(String deliresourceid) {
		this.deliresourceid = deliresourceid;
	}

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public String getDelistartdate() {
		return delistartdate;
	}

	public void setDelistartdate(String delistartdate) {
		this.delistartdate = delistartdate;
	}

	public String getDelienddate() {
		return delienddate;
	}

	public void setDelienddate(String delienddate) {
		this.delienddate = delienddate;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
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

	public int getApproveddate() {
		return approveddate;
	}

	public void setApproveddate(int approveddate) {
		this.approveddate = approveddate;
	}

	public String getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}

}
  