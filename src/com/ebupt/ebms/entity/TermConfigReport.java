package com.ebupt.ebms.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
 * @author lishuhua
 * Create time 2011-03-04
 * @version 1.0 
 */
//�ն��ϱ�������Ϣ��
@Entity
@Table(name = "termconfigreport")
public class TermConfigReport {

	/*
	 * �ն��˺�
	 */
	@Id
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * ������Ϣ�ϱ�ʱ��
	 */
	@Column(length = 14,nullable=false)
	private String reporttime;
	
	/*
	 * Ip��ַ
	 */
	@Column(length = 15)
	private String ip;
	
	/*
	 * Mac��ַ
	 */
	@Column(length = 20)
	private String mac;
	
	/*
	 * ����
	 */
	@Column(length = 15,nullable=true)
	private String gateway;
	
	/*
	 * DNS
	 */
	@Column(length = 15,nullable=true)
	private String dns;
	
	/*
	 * �ն�����汾
	 */
	@Column(length = 10,nullable=true)
	private String appversion;
	
	/*
	 * ����ʱ�䣺HH:MM:SS
	 */
	@Column(length = 8,nullable=true)
	private String startuptime;
	
	/*
	 * �ػ�ʱ�䣺HH:MM:SS
	 */
	@Column(length = 8,nullable=true)
	private String shutdowntime;
	
	/*
	 * Ӳ�̸澯��ֵ����λ��MB��
	 */
	@Column(length = 8,nullable=true)
	private String diskspacealarm;
	
	/*
	 * ���ط�����ip
	 */
	@Column(length = 15,nullable=true)
	private String serverip;
	
	/*
	 * ���ط������˿�
	 */
	@Column(length = 5,nullable=true)
	private String serverport;
	
	@Column(length=15)
	private String redisip; // redis������ip

	@Column(length=5)
	private String redisport; // redis�������˿�

	@Column(length=4)
	private String maxmonitortime; // �����ʱ�䣬��λ��s��
	
	/*
	 * �ն�����0-100
	 */
	@Column(length = 3,nullable=true)
	private String volume;
	
	/*
	 * ���ط�������ַ
	 */
	@Column(length = 100,nullable=true)
	private String downloadserver;
	
	/*
	 * �ն��������ʣ�kb/s
	 */
	@Column(length = 8,nullable=true)
	private String downloadrate;
	
	/*
	 * ����ʱ��Σ�00:00:00-08:00:00,12:00:00-13:00:00,18:00:00-24:00:00
	 */
	@Column(length = 255,nullable=true)
	private String downloadtime;
	
	/*
	 * ��ѯʱ��������λ��s��
	 */
	@Column(length = 4,nullable=true)
	private String selectinterval;
	
	/*
	 * ��־������·���ն��ϱ���־�ķ�����
	 */
	@Column(length = 255,nullable=true)
	private String logserver;
	
	/*
	 * ��־�ϴ�ʱ��,��ʽ: HH:MM:SS
	 */
	@Column(length = 8,nullable=true)
	private String uploadlogtime;
	
	/*
	 * ��־����ʱ�䣨������־��������־��������־��������־��
	 * ��λ����  ��7��
	 */
	@Column(length = 4,nullable=true)
	private String keeplogtime;
	
	/*
	 * ��־���𣨷�4������debug;info;warn;error��
	 */
	@Column(length = 6,nullable=true)
	private String loglevel;
	
	/*
	 * ��ʾ���ŷ�ģʽ��������������1��������2����
	 */
	@Column(length = 2,nullable=true)
	private String placementmodel;
	
	/*
	 * ����豸���ͣ����ڹ��̰�װʱ������ǰ�ն˽ӵ����ĸ��豸���豸��4λ�����ʶ����
	 * 0001�����ӻ�
	 * 0002��LED
	 */
	@Column(length = 4,nullable=true)
	private String exdevicetype;
	
	/**
	 * �ն�����ֱ��ʣ��磺1024x768
	 */
	@Column(length = 9,nullable=true)
	private String resolution;

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getDns() {
		return dns;
	}

	public void setDns(String dns) {
		this.dns = dns;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
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

	public String getLoglevel() {
		return loglevel;
	}

	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}

	public String getPlacementmodel() {
		return placementmodel;
	}

	public void setPlacementmodel(String placementmodel) {
		this.placementmodel = placementmodel;
	}

	public String getExdevicetype() {
		return exdevicetype;
	}

	public void setExdevicetype(String exdevicetype) {
		this.exdevicetype = exdevicetype;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getDownloadserver() {
		return downloadserver;
	}

	public void setDownloadserver(String downloadserver) {
		this.downloadserver = downloadserver;
	}

	public String getDownloadrate() {
		return downloadrate;
	}

	public void setDownloadrate(String downloadrate) {
		this.downloadrate = downloadrate;
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
  