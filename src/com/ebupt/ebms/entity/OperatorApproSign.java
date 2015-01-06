package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2012-02-13
 * @version 1.0  
 * ����Ա������ǩ��Ӧ��ϵ��
 */

@Entity
@Table(name = "operatorapprosign")
public class OperatorApproSign {
	
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
	 * ��ǩ��
	 */
	@Column(length = 32,nullable = false)
	private String signer;
	
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
  