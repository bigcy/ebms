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

//权限信息表
@Entity
@Table(name = "powerinfo")
public class PowerInfo {
	
	/*
	 * 权限Id
	 */
	@Id
	@Column(length = 32)
	private String powerId;

	/*
	 * 父节点Id
	 * 一级父节点为：-1
	 */
	@Column(length = 32,nullable=false)
	private String superPowerId;
	
	/*
	 * 权限名称
	 */
	@Column(length = 30,nullable=false)
	private String powerName;
	
	/*
	 * 权限级别：
	 * 1：一级
	 * 2：二级
	 * 3：三级
	 * 4：.......
	 */
	@Column(length = 1,nullable=false)
	private String powerLevel;
	
	/*
	 * 图标路径
	 */
	@Column(length = 100,nullable=true)
	private String powerIcon;
	
	/*
	 * 后台对应文件
	 */
	@Column(length = 32,nullable=true)
	private String controller;
	
	/*
	 * 后台对应方法
	 */
	@Column(length = 32,nullable=true)
	private String action;
	
	/*
	 * 前台JS处理方法
	 */
	@Column(length = 100,nullable=true)
	private String jsfunction;
	
	/*
	 * 专属于哪些系统角色 例sysrole1,role2，这里是把系统角色拥有的权限给保存起来，便于初始化数据的需要。
	 */
	@Column(length = 200,nullable=true)
	private String onlyhave;
	
	/*
	 * 是否隐藏：0：显示1：隐藏
	 * 隐藏时管理员可以拥有该权限的功能，但是在权限管理中不能看到和分配该权限。
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
  