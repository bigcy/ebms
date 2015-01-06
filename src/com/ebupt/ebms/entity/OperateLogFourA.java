package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-2
 * @version 1.0  
 */
//操作日志表
@Entity
@Table(name = "operatelogfoura")
public class OperateLogFourA{

	@Id
	@Column(length = 32)
	private String serialno; // 流水号
	
	@Column(length = 32 , nullable = false)
	private String operatorid;// 操作员id
	
	@Column(length = 20 , nullable = false)
	private String operatetime;// 操作时间
	
	@Column(length = 255)
	private String description;// 操作明细
	
	@Column(length = 15 , nullable = false)
	private String clientip;// 客户端ip
	
	@Column(length = 6 , nullable = false)
	private String clientport;// 客户端端口
	
	@Column(length = 50 , nullable = false)
	private String controller;// 请求的类
	
	@Column(length = 50 , nullable = false)
	private String action;// 请求的方法
	
	@Column(length = 17)
	private String mac;// 客户端mac
	
	@Column(length = 50)
	private String cpu;// 客户端cpu
	
	@Column(length = 50 , nullable = false)
	private String optypeid;// 操作类型编码
	
	@Column(length = 50 , nullable = false)
	private String optypename;// 操作类型名称
	
	@Column(length = 50 , nullable = false)
	private String optable;// 操作影响到的数据表
	
	@Column(length = 100)
	private String opwhere;// 操作所涉及到的where条件
	
	@Column(length = 50)
	private String mainacctid;// 主账号
	
	@Column(length = 50)
	private String subacctname;// 主从账号名称
	
	@Column(length = 200)
	private String opcontent;// 操作内容
	
	@Column(length = 50)
	private String moduleid;// 模块ID
	
	@Column(length = 50)
	private String modulename;// 模块名称
	
	@Column(length = 1)
	private String flag;// 标志位，取值0和1，目前只对跟任务、消息、素材及审批相关的记录标志为1，其它默认为null

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClientip() {
		return clientip;
	}

	public void setClientip(String clientip) {
		this.clientip = clientip;
	}

	public String getClientport() {
		return clientport;
	}

	public void setClientport(String clientport) {
		this.clientport = clientport;
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

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOptypeid() {
		return optypeid;
	}

	public void setOptypeid(String optypeid) {
		this.optypeid = optypeid;
	}

	public String getOptypename() {
		return optypename;
	}

	public void setOptypename(String optypename) {
		this.optypename = optypename;
	}

	public String getOptable() {
		return optable;
	}

	public void setOptable(String optable) {
		this.optable = optable;
	}

	public String getOpwhere() {
		return opwhere;
	}

	public void setOpwhere(String opwhere) {
		this.opwhere = opwhere;
	}

	public String getMainacctid() {
		return mainacctid;
	}

	public void setMainacctid(String mainacctid) {
		this.mainacctid = mainacctid;
	}

	public String getSubacctname() {
		return subacctname;
	}

	public void setSubacctname(String subacctname) {
		this.subacctname = subacctname;
	}

	public String getOpcontent() {
		return opcontent;
	}

	public void setOpcontent(String opcontent) {
		this.opcontent = opcontent;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	
}
  