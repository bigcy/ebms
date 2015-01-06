package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**   
 * @author QiChen
 * Create on 2011-3-4
 * @version 1.0  
 * 
 * changed by lishuhua
 * change time 2011-04-01
 * ���������
 */
@Entity
@Table(name = "playlistitem")
public class PlayListItem {
	
	@Id
	@Column(length = 32)
	private String playlistitemid; 
	
	/* �����б�����id*/
	@Column(length = 32 , nullable = false)
	private String playlistid;
	
	/*
	 * ��ʼʱ���-��ĿID--ѭ������-����ʱ��㡣
	 * �磺����5�㵽6�㲥�Ž�ĿidΪ3��ѭ������Ϊ3�Ľ�Ŀ������4�㵽4��벥�Ž�ĿidΪ14��ѭ������Ϊ0�Ľ�Ŀ��
	 * 0500-3-3-0600��1600-14-0-1630
	 */
	//@Lob
	@Column(length = 4000)
	private String playseq;
	
	/* ��ʼʱ�� ����0900��2100�ֱ��ʾ�硢��9��  */
	@Column(length = 4 , nullable = false)
	private String starttime;
	
	/* ����ʱ�䣬ͬ��ʼʱ��  */
	@Column(length = 4 , nullable = false)
	private String endtime;
	
	/*
	 * �����ĸ��ӷ���������
	 */
	@Column(length = 32 , nullable = false)
	private String subtemplateid;

	public String getPlaylistitemid() {
		return playlistitemid;
	}
	
	// getter and setter...
	
	public void setPlaylistitemid(String playlistitemid) {
		this.playlistitemid = playlistitemid;
	}

	public String getPlaylistid() {
		return playlistid;
	}

	public void setPlaylistid(String playlistid) {
		this.playlistid = playlistid;
	}

	public String getPlayseq() {
		return playseq;
	}

	public void setPlayseq(String playseq) {
		this.playseq = playseq;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getSubtemplateid() {
		return subtemplateid;
	}

	public void setSubtemplateid(String subtemplateid) {
		this.subtemplateid = subtemplateid;
	}
	
}
  