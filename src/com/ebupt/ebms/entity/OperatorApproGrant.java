package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2012-02-13
 * @version 1.0  
 * 3.6.	����Ա������Ȩ��Ӧ��ϵ��
 */

@Entity
@Table(name = "operatorapprogrant")
public class OperatorApproGrant {
	
	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * ������
	 */
	@Column(length = 32,nullable = false)
	private String approver;

	/*
	 * ��Ȩ������
	 */
	@Column(length = 32,nullable = false)
	private String grantapprover;
	
	/*
	 * ��Ȩʱ��
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

	public String getGrantapprover() {
		return grantapprover;
	}

	public void setGrantapprover(String grantapprover) {
		this.grantapprover = grantapprover;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
  