package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 */
@Entity
@Table(name = "softinfogroup")
public class SoftInfoGroup {
	
	@Id
	@Column(length=32)
	private String serialno; // ��ˮ��

	@Column(length=32, nullable = false)
	private String softinfoid; // �����Ϣid

	@Column(length=32, nullable = false)
	private String groupid; // Ⱥ��ID

	@Column(length=14, nullable = false)
	private String createtime; // ����ʱ��

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getSoftinfoid() {
		return softinfoid;
	}

	public void setSoftinfoid(String softinfoid) {
		this.softinfoid = softinfoid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}


}
  