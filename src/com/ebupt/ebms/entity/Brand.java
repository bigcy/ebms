package com.ebupt.ebms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lishuhua Create time 2012-09-20
 * @version 1.0
 * Ʒ�Ʊ�
 */
@Entity
@Table(name = "brand")
public class Brand {

	/*
	 * Ʒ��id
	 */
	@Id
	@Column(length = 32)
	private String brandid;

	/*
	 * ����ʵ�����壬Ĭ��1
	 */
	@Column(length = 1)
	private String brandlevel = "1";

	/*
	 *Ʒ������
	 */
	@Column(length = 32, nullable = false)
	private String name;

	/*
	 * ������Ʒ�ƵĲ���Ա
	 */
	@Column(length = 32)
	private String operatorid;

	/*
	 * ����ʱ��
	 */
	@Column(length = 14, nullable = false)
	private String createtime;

	/*
	 * ��Ʒ�Ƶ��ϼ�id��Ŀǰ��дĬ��ֵ-1
	 */
	@Column(length = 32)
	private String superbrandid;

	/*
	 * Ʒ�Ƶ���ϸ����
	 */
	@Column(length = 255, nullable = true)
	private String description;

	/*
	 * Ʒ�����ͣ�����ʵ���ô���Ĭ��0
	 */
	@Column(length = 1)
	private String type;

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getBrandlevel() {
		return brandlevel;
	}

	public void setBrandlevel(String brandlevel) {
		this.brandlevel = brandlevel;
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

	public String getSuperbrandid() {
		return superbrandid;
	}

	public void setSuperbrandid(String superbrandid) {
		this.superbrandid = superbrandid;
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


}
