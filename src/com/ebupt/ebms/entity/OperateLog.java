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
//������־��
@Entity
@Table(name = "operatelog")
public class OperateLog{

	@Id
	@Column(length = 32)
	private String serialno; // ��ˮ��
	
	@Column(length = 32 , nullable = false)
	private String operatorid;// ����Աid
	
	@Column(length = 14 , nullable = false)
	private String operatetime;// ����ʱ��
	
	@Column(length = 255 , nullable = false)
	private String description;// ������ϸ
	
//	@Column(length = 1)
//	private String flag;// ��־λ��ȡֵ0��1��Ŀǰֻ�Ը�������Ϣ���زļ�������صļ�¼��־Ϊ1������Ĭ��Ϊnull
	
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
  