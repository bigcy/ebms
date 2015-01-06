package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author zhangqian
 * Create time 2011-10-14
 * @version 1.0  
 */

//记录平台对终端发送的命令及响应
@Entity
@Table(name = "termcommand")
public class TermCommand {

	/*
	 * 流水号
	 */
	@Id
	@Column(length = 32)
	private String serialno;
	
	/*
	 * 终端账号
	 */
	@Column(length = 32,nullable=false)
	private String terminalid;

	/*
	 * 命令请求
	 */
	@Column(length = 255,nullable=false)
	private String command;
	
	/*
	 * 命令结果
	 */
	//@Lob
	@Column(length = 4000)
	private String result;
	
	/*
	 * 创建时间
	 */
	@Column(length = 14,nullable=false)
	private String createtime;

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

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
  