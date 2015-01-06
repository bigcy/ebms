package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 * 播放列表任务明细表
 */
@Entity
@Table(name = "playlisttaskitem")
public class PlayListTaskItem {
	
	@Id
	@Column(length=32)
	private String serialno; // 流水号

	@Column(length=32, nullable = false)
	private String playlistid; // 播放任务id，等同任务id// 此处存放的是PlayListGroup的serialno

	@Column
	private Long csize; // 内容大小，单位字节

	@Column(length=255, nullable = false)
	private String url; // 内容访问链接

	@Column(length=32, nullable = false)
	private String md5; // 资源md5值

	@Column(length=32, nullable = false)
	private String terminalid; // 终端Id号

	@Column(length=4, nullable = false)
	private String status; // 任务状态：0：新建0000：收完任务通知（终端下载完播放任务后反馈）0001：下载完成（终端下载完播放任务中的所有内容后返回）01XX：下载失败0101：源文件不存在0102：下载超时0103：账号权限错误0104：文件md5校验错误0105：连接超时

	@Column(length=14, nullable = false)
	private String createtime; // 创建时间

	@Column(length=14)
	private String recvtime; // 接收时间

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
	}

	public Long getCsize() {
		return csize;
	}

	public void setCsize(Long csize) {
		this.csize = csize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getRecvtime() {
		return recvtime;
	}

	public void setRecvtime(String recvtime) {
		this.recvtime = recvtime;
	}
}
  