package com.ebupt.ebms.entity;   

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-09-02
 * @version 1.0  
 */
//�ն�ƽ̨������־��
@Entity
@Table(name = "termoperlog")
public class TermOperLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6295758928536720715L;

	/*
	 * ��ˮ��
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * �ն�id
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * ����ʱ��
	 */
	@Column(length =14,nullable=false)
	private String time;
	
	/*
	 * ������ϸ
	 */
	@Column(length = 255,nullable=false)
	private String description;

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
  