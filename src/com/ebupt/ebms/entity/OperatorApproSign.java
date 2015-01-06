package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2012-02-13
 * @version 1.0  
 * 操作员审批会签对应关系表
 */

@Entity
@Table(name = "operatorapprosign")
public class OperatorApproSign {
	
	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * 审批人
	 */
	@Column(length = 32,nullable = false)
	private String approver;

	/*
	 * 会签人
	 */
	@Column(length = 32,nullable = false)
	private String signer;
	
	/*
	 * 授权时间
	 */
	@Column(length = 14)
	private String createtime;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
  