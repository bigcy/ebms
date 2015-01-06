package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua 
 * Create time 2011-03-04
 * @version 1.0
 */

// ����ԱȨ�ޱ�
@Entity
@Table(name = "operatorpower")
public class OperatorPower {

	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;

	/*
	 * Ȩ��Id
	 */
	@Column(length = 32, nullable = false)
	private String powerId;

	/*
	 * ����Ա���
	 */
	@Column(length = 32, nullable = false)
	private String operatorid;

	/*
	 * ����ͳ��ʹ�ô����Լ������б����������
	 */
	@Column(length = 4, nullable = true)
	private String sort;

	/*
	 * ��ʶ�Ƿ��ǿ�ݷ��� �����ó��������ļ����ÿ�ݷ�ʽ�ĸ���
	 */
	@Column(length = 2, nullable = true)
	private String shortcut;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

}
