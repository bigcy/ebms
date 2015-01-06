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
 * 终端配置信息表
 */
@Entity
@Table(name = "termconfig")
public class TermConfig {
	
	@Id
	@Column(length=32, nullable = false)
	private String termconfigid; // 配置信息id

	@Column(length=14, nullable = false)
	private String createtime; // 配置信息设置时间

	@Column(length=8)
	private String startuptime; // 开机时间：HH:MM:SS

	@Column(length=8)
	private String shutdowntime; // 关机时间：HH:MM:SS

	@Column(length=8)
	private String diskspacealarm; // 硬盘告警阀值，单位（MB）

	@Column(length=15)
	private String serverip; // 播控服务器ip

	@Column(length=5)
	private String serverport; // 播控服务器端口
	
	@Column(length=15)
	private String redisip; // redis服务器ip

	@Column(length=5)
	private String redisport; // redis服务器端口

	@Column(length=4)
	private String maxmonitortime; // 最大监控时间，单位（s）

	@Column(length=3)
	private String volume; // 终端音量0-100

	/*
	 * 下载服务器地址
	 */
	@Column(length=100)
	private String downloadserver; 
	
	@Column(length=8)
	private String downloadrate; // 终端下载速率：kb/s

	@Column(length=255)
	private String downloadtime; // 下载时间段：00:00:00-08:00:00,12:00:00-13:00:00,18:00:00-24:00:00

	@Column(length=4)
	private String selectinterval; // 轮询时间间隔，单位（s）

	@Column(length=255)
	private String logserver; // 日志服务器路径终端上报日志的服务器

	@Column(length=8)
	private String uploadlogtime; // 日志上传时间,格式: HH:MM:SS

	@Column(length=4)
	private String keeplogtime; // 日志保留时间（运行日志、播放日志、下载日志、调试日志）单位：天如7天loglevel
	
	@Column(length = 6)
	private String loglevel; // 日志级别（分4个级别，debug;info;warn;error）

	@Column(length=1)
	private String placementmodel; // 显示器排放模式（横屏或竖屏）1：横屏；2竖屏
	
	/*
	 * 终端输出分辨率，如：1024x768
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
  