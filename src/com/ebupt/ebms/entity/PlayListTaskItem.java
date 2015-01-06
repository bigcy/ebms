package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 * �����б�������ϸ��
 */
@Entity
@Table(name = "playlisttaskitem")
public class PlayListTaskItem {
	
	@Id
	@Column(length=32)
	private String serialno; // ��ˮ��

	@Column(length=32, nullable = false)
	private String playlistid; // ��������id����ͬ����id// �˴���ŵ���PlayListGroup��serialno

	@Column
	private Long csize; // ���ݴ�С����λ�ֽ�

	@Column(length=255, nullable = false)
	private String url; // ���ݷ�������

	@Column(length=32, nullable = false)
	private String md5; // ��Դmd5ֵ

	@Column(length=32, nullable = false)
	private String terminalid; // �ն�Id��

	@Column(length=4, nullable = false)
	private String status; // ����״̬��0���½�0000����������֪ͨ���ն������겥�����������0001��������ɣ��ն������겥�������е��������ݺ󷵻أ�01XX������ʧ��0101��Դ�ļ�������0102�����س�ʱ0103���˺�Ȩ�޴���0104���ļ�md5У�����0105�����ӳ�ʱ

	@Column(length=14, nullable = false)
	private String createtime; // ����ʱ��

	@Column(length=14)
	private String recvtime; // ����ʱ��

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
  