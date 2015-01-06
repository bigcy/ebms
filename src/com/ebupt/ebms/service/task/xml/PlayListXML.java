package com.ebupt.ebms.service.task.xml;

import java.util.Map;

/**
 * @author QiChen Create on 2011-3-18
 * @version 1.0
 */
public class PlayListXML {

	private String id;
	private String name;
	private String md5;
	private String href;

	private Map<String, FileXML> files;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Map<String, FileXML> getFiles() {
		return files;
	}

	public void setFiles(Map<String, FileXML> files) {
		this.files = files;
	}
}
