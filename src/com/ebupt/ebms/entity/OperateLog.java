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
@Table(name = "operatelog")
public class OperateLog{

	@Id
	@Column(length = 32)
	private String serialno; // 流水号
	
	@Column(length = 32 , nullable = false)
	private String operatorid;// 操作员id
	
	@Column(length = 14 , nullable = false)
	private String operatetime;// 操作时间
	
	@Column(length = 255 , nullable = false)
	private String description;// 操作明细
	
//	@Column(length = 1)
//	private String flag;// 标志位，取值0和1，目前只对跟任务、消息、素材及审批相关的记录标志为1，其它默认为null
	
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

//	public String getFlag() {
//		return flag;
//	}
//
//	public void setFlag(String flag) {
//		this.flag = flag;
//	}
	
	
}
  