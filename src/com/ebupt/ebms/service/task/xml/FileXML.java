package com.ebupt.ebms.service.task.xml;
/**   
 * @author QiChen
 * Create on 2011-3-18
 * @version 1.0  
 */
public class FileXML {
	private String id;
	private String type;
	private String name;
	private String md5;
	private String href;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
}