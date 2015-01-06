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
 * �ն�������Ϣ��
 */
@Entity
@Table(name = "termconfig")
public class TermConfig {
	
	@Id
	@Column(length=32, nullable = false)
	private String termconfigid; // ������Ϣid

	@Column(length=14, nullable = false)
	private String createtime; // ������Ϣ����ʱ��

	@Column(length=8)
	private String startuptime; // ����ʱ�䣺HH:MM:SS

	@Column(length=8)
	private String shutdowntime; // �ػ�ʱ�䣺HH:MM:SS

	@Column(length=8)
	private String diskspacealarm; // Ӳ�̸澯��ֵ����λ��MB��

	@Column(length=15)
	private String serverip; // ���ط�����ip

	@Column(length=5)
	private String serverport; // ���ط������˿�
	
	@Column(length=15)
	private String redisip; // redis������ip

	@Column(length=5)
	private String redisport; // redis�������˿�

	@Column(length=4)
	private String maxmonitortime; // �����ʱ�䣬��λ��s��

	@Column(length=3)
	private String volume; // �ն�����0-100

	/*
	 * ���ط�������ַ
	 */
	@Column(length=100)
	private String downloadserver; 
	
	@Column(length=8)
	private String downloadrate; // �ն��������ʣ�kb/s

	@Column(length=255)
	private String downloadtime; // ����ʱ��Σ�00:00:00-08:00:00,12:00:00-13:00:00,18:00:00-24:00:00

	@Column(length=4)
	private String selectinterval; // ��ѯʱ��������λ��s��

	@Column(length=255)
	private String logserver; // ��־������·���ն��ϱ���־�ķ�����

	@Column(length=8)
	private String uploadlogtime; // ��־�ϴ�ʱ��,��ʽ: HH:MM:SS

	@Column(length=4)
	private String keeplogtime; // ��־����ʱ�䣨������־��������־��������־��������־����λ������7��loglevel
	
	@Column(length = 6)
	private String loglevel; // ��־���𣨷�4������debug;info;warn;error��

	@Column(length=1)
	private String placementmodel; // ��ʾ���ŷ�ģʽ��������������1��������2����
	
	/*
	 * �ն�����ֱ��ʣ��磺1024x768
	 */
	@Column(length=9)
	private String resolution;
	
	public String getTermconfigid() {
		return termconfigid;
	}

	public void setTermconfigid(String termconfigid) {
		this.termconfigid = termconfigid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLoglevel() {
		return loglevel;
	}

	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}


	public String getStartuptime() {
		return startuptime;
	}

	public void setStartuptime(String startuptime) {
		this.startuptime = startuptime;
	}

	public String getShutdowntime() {
		return shutdowntime;
	}

	public void setShutdowntime(String shutdowntime) {
		this.shutdowntime = shutdowntime;
	}

	public String getDiskspacealarm() {
		return diskspacealarm;
	}

	public void setDiskspacealarm(String diskspacealarm) {
		this.diskspacealarm = diskspacealarm;
	}

	public String getServerip() {
		return serverip;
	}

	public void setServerip(String serverip) {
		this.serverip = serverip;
	}

	public String getServerport() {
		return serverport;
	}

	public void setServerport(String serverport) {
		this.serverport = serverport;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getDownloadtime() {
		return downloadtime;
	}

	public void setDownloadtime(String downloadtime) {
		this.downloadtime = downloadtime;
	}

	public String getSelectinterval() {
		return selectinterval;
	}

	public void setSelectinterval(String selectinterval) {
		this.selectinterval = selectinterval;
	}

	public String getLogserver() {
		return logserver;
	}

	public void setLogserver(String logserver) {
		this.logserver = logserver;
	}

	public String getUploadlogtime() {
		return uploadlogtime;
	}

	public void setUploadlogtime(String uploadlogtime) {
		this.uploadlogtime = uploadlogtime;
	}

	public String getKeeplogtime() {
		return keeplogtime;
	}

	public void setKeeplogtime(String keeplogtime) {
		this.keeplogtime = keeplogtime;
	}

	public String getPlacementmodel() {
		return placementmodel;
	}

	public void setPlacementmodel(String placementmodel) {
		this.placementmodel = placementmodel;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getDownloadrate() {
		return downloadrate;
	}

	public void setDownloadrate(String downloadrate) {
		this.downloadrate = downloadrate;
	}

	public String getDownloadserver() {
		return downloadserver;
	}

	public void setDownloadserver(String downloadserver) {
		this.downloadserver = downloadserver;
	}

	public String getRedisip() {
		return redisip;
	}

	public void setRedisip(String redisip) {
		this.redisip = redisip;
	}

	public String getRedisport() {
		return redisport;
	}

	public void setRedisport(String redisport) {
		this.redisport = redisport;
	}

	public String getMaxmonitortime() {
		return maxmonitortime;
	}

	public void setMaxmonitortime(String maxmonitortime) {
		this.maxmonitortime = maxmonitortime;
	}

}
  