package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua Create time 2011-03-04
 * @version 1.0
 */
// �ն˷����
@Entity
@Table(name = "termgroup")
public class TermGroup {

	/*
	 * �ն����
	 */
	@Id
	@Column(length = 32)
	private String groupid;

	/*
	 * Ⱥ�����ڵĲ�Σ�����������Ϊ1�������Ը�Ⱥ�����ڵ�grouplevel��1���Դ����ơ� ������Ŀ�õ�������Σ��磺 ����->������->֪��·Ӫҵ��
	 */
	@Column(length = 2, nullable = false)
	private String grouplevel;

	/*
	 * ������
	 */
	@Column(length = 32, nullable = false)
	private String name;

	/*
	 * �����Ӧ�Ĳ���Ա
	 */
	@Column(length = 32, nullable = false)
	private String operatorid;

	/*
	 * ����ʱ��
	 */
	@Column(length = 14, nullable = false)
	private String createtime;

	/*
	 * ��Ⱥ�飬��Ⱥ��Ĭ��Ϊ-1
	 */
	@Column(length = 32, nullable = false)
	private String supergroupid;

	/*
	 * Ⱥ�����ϸ��������ע
	 */
	@Column(length = 255, nullable = true)
	private String description;

	/*
	 * Ⱥ�����ͣ�0���������� 1���Զ������
	 */
	@Column(length = 1, nullable = false)
	private String type;
	
	@Column(length = 32, nullable = true)
	private String groupCode;

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGrouplevel() {
		return grouplevel;
	}

	public void setGrouplevel(String grouplevel) {
		this.grouplevel = grouplevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getSupergroupid() {
		return supergroupid;
	}

	public void setSupergroupid(String supergroupid) {
		this.supergroupid = supergroupid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

}