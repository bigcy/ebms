package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua
 * Create on 2011-04-06
 * @version 1.0
 * �زĲ���Ա��ϵ��
 */
@Entity
@Table(name = "contentoperator")
public class ContentOperator {
	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * �ز�id
	 */
	@Column(length = 32, nullable = false)
	private String contentid;
	
	/*
	 * ����ԱId
	 */
	@Column(length = 32, nullable = false)
	private String operatorid;
	
	/*
	 * ���ͣ�0���Լ������� 	1���·�������
	 */
	@Column(length = 1, nullable = false)
	private String type;
	
	/*
	 * �����ߣ�ȡ�Բ���Աid���·������Ĳ��и�ֵ
	 */
	@Column(length = 32)
	private String sharers;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSharers() {
		return sharers;
	}

	public void setSharers(String sharers) {
		this.sharers = sharers;
	}
	
}
