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
	private String serialno; // 流水号

	@Column(length=32, nullable = false)
	private String softinfoid; // 软件信息id

	@Column(length=32, nullable = false)
	private String groupid; // 群组ID

	@Column(length=14, nullable = false)
	private String createtime; // 创建时间

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
  