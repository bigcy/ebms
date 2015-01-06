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
//终端上报配置信息表
@Entity
@Table(name = "termconfigreport")
public class TermConfigReport {

	/*
	 * 终端账号
	 */
	@Id
	@Column(length = 32,nullable=false)
	private String terminalid;
	
	/*
	 * 配置信息上报时间
	 */
	@Column(length = 14,nullable=false)
	private String reporttime;
	
	/*
	 * Ip地址
	 */
	@Column(length = 15)
	private String ip;
	
	/*
	 * Mac地址
	 */
	@Column(length = 20)
	private String mac;
	
	/*
	 * 网关
	 */
	@Column(length = 15,nullable=true)
	private String gateway;
	
	/*
	 * DNS
	 */
	@Column(length = 15,nullable=true)
	private String dns;
	
	/*
	 * 终端软件版本
	 */
	@Column(length = 10,nullable=true)
	private String appversion;
	
	/*
	 * 开机时间：HH:MM:SS
	 */
	@Column(length = 8,nullable=true)
	private String startuptime;
	
	/*
	 * 关机时间：HH:MM:SS
	 */
	@Column(length = 8,nullable=true)
	private String shutdowntime;
	
	/*
	 * 硬盘告警阀值，单位（MB）
	 */
	@Column(length = 8,nullable=true)
	private String diskspacealarm;
	
	/*
	 * 播控服务器ip
	 */
	@Column(length = 15,nullable=true)
	private String serverip;
	
	/*
	 * 播控服务器端口
	 */
	@Column(length = 5,nullable=true)
	private String serverport;
	
	@Column(length=15)
	private String redisip; // redis服务器ip

	@Column(length=5)
	private String redisport; // redis服务器端口

	@Column(length=4)
	private String maxmonitortime; // 最大监控时间，单位（s）
	
	/*
	 * 终端音量0-100
	 */
	@Column(length = 3,nullable=true)
	private String volume;
	
	/*
	 * 下载服务器地址
	 */
	@Column(length = 100,nullable=true)
	private String downloadserver;
	
	/*
	 * 终端下载速率：kb/s
	 */
	@Column(length = 8,nullable=true)
	private String downloadrate;
	
	/*
	 * 下载时间段：00:00:00-08:00:00,12:00:00-13:00:00,18:00:00-24:00:00
	 */
	@Column(length = 255,nullable=true)
	private String downloadtime;
	
	/*
	 * 轮询时间间隔，单位（s）
	 */
	@Column(length = 4,nullable=true)
	private String selectinterval;
	
	/*
	 * 日志服务器路径终端上报日志的服务器
	 */
	@Column(length = 255,nullable=true)
	private String logserver;
	
	/*
	 * 日志上传时间,格式: HH:MM:SS
	 */
	@Column(length = 8,nullable=true)
	private String uploadlogtime;
	
	/*
	 * 日志保留时间（运行日志、播放日志、下载日志、调试日志）
	 * 单位：天  如7天
	 */
	@Column(length = 4,nullable=true)
	private String keeplogtime;
	
	/*
	 * 日志级别（分4个级别，debug;info;warn;error）
	 */
	@Column(length = 6,nullable=true)
	private String loglevel;
	
	/*
	 * 显示器排放模式（横屏或竖屏）1：横屏；2竖屏
	 */
	@Column(length = 2,nullable=true)
	private String placementmodel;
	
	/*
	 * 外接设备类型；用于工程安装时反馈当前终端接的是哪个设备，设备用4位编码标识；如
	 * 0001：电视机
	 * 0002：LED
	 */
	@Column(length = 4,nullable=true)
	private String exdevicetype;
	
	/**
	 * 终端输出分辨率，如：1024x768
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
  