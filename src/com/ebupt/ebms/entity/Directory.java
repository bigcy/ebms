package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-04-01
 * @version 1.0  
 */
@Entity
@Table(name = "directory")
public class Directory {

	/*
	 * Ŀ¼Id
	 */
	@Id
	@Column(length = 32)
	private String directoryid;
	
	/*
	 * ��Ŀ¼����Ŀ¼Ĭ��Ϊ-1
	 */
	@Column(length = 32,nullable=false)
	private String superdirectoryid;
	
	/*
	 * Ŀ¼����
	 */
	@Column(length = 32,nullable=false)
	private String name;
	
	/*
	 * Ŀ¼����
	 * 01���ز�Ŀ¼
	 * 02����ĿĿ¼
	 * 03��ģ��Ŀ¼
	 * 04����������Ŀ¼
	 */
	@Column(length = 2,nullable=false)
	private String type;
	
	/*
	 * ��Ŀ¼��Ӧ�Ĳ���Ա
	 */
	@Column(length = 32,nullable=false)
	private String operatorid;
	
	/*
	 * ����ʱ��
	 */
	@Column(length = 14,nullable=false)
	private String createtime;
	
	/*
	 * Ŀ¼����ϸ��������ע
	 */
	@Column(length = 255,nullable=true)
	private String description;

	public String getDirectoryid() {
		return directoryid;
	}

	public void setDirectoryid(String directoryid) {
		this.directoryid = directoryid;
	}

	public String getSuperdirectoryid() {
		return superdirectoryid;
	}

	public void setSuperdirectoryid(String superdirectoryid) {
		this.superdirectoryid = superdirectoryid;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
  