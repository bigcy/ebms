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

//Ȩ����Ϣ��
@Entity
@Table(name = "powerinfo")
public class PowerInfo {
	
	/*
	 * Ȩ��Id
	 */
	@Id
	@Column(length = 32)
	private String powerId;

	/*
	 * ���ڵ�Id
	 * һ�����ڵ�Ϊ��-1
	 */
	@Column(length = 32,nullable=false)
	private String superPowerId;
	
	/*
	 * Ȩ������
	 */
	@Column(length = 30,nullable=false)
	private String powerName;
	
	/*
	 * Ȩ�޼���
	 * 1��һ��
	 * 2������
	 * 3������
	 * 4��.......
	 */
	@Column(length = 1,nullable=false)
	private String powerLevel;
	
	/*
	 * ͼ��·��
	 */
	@Column(length = 100,nullable=true)
	private String powerIcon;
	
	/*
	 * ��̨��Ӧ�ļ�
	 */
	@Column(length = 32,nullable=true)
	private String controller;
	
	/*
	 * ��̨��Ӧ����
	 */
	@Column(length = 32,nullable=true)
	private String action;
	
	/*
	 * ǰ̨JS������
	 */
	@Column(length = 100,nullable=true)
	private String jsfunction;
	
	/*
	 * ר������Щϵͳ��ɫ ��sysrole1,role2�������ǰ�ϵͳ��ɫӵ�е�Ȩ�޸��������������ڳ�ʼ�����ݵ���Ҫ��
	 */
	@Column(length = 200,nullable=true)
	private String onlyhave;
	
	/*
	 * �Ƿ����أ�0����ʾ1������
	 * ����ʱ����Ա����ӵ�и�Ȩ�޵Ĺ��ܣ�������Ȩ�޹����в��ܿ����ͷ����Ȩ�ޡ�
	 */
	@Column(length = 1,nullable=true)
	private String ishidden;

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public String getSuperPowerId() {
		return superPowerId;
	}

	public void setSuperPowerId(String superPowerId) {
		this.superPowerId = superPowerId;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getPowerLevel() {
		return powerLevel;
	}

	public void setPowerLevel(String powerLevel) {
		this.powerLevel = powerLevel;
	}

	public String getPowerIcon() {
		return powerIcon;
	}

	public void setPowerIcon(String powerIcon) {
		this.powerIcon = powerIcon;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getJsfunction() {
		return jsfunction;
	}

	public void setJsfunction(String jsfunction) {
		this.jsfunction = jsfunction;
	}

	public String getOnlyhave() {
		return onlyhave;
	}

	public void setOnlyhave(String onlyhave) {
		this.onlyhave = onlyhave;
	}

	public String getIshidden() {
		return ishidden;
	}

	public void setIshidden(String ishidden) {
		this.ishidden = ishidden;
	}
	
}
  