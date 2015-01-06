package com.ebupt.ebms.cache.redis;

public class CacheClientConf {

	private String ip = "10.1.70.142";

	private int port = 6379;

	private int minCon = 3;

	private int maxCon = 10;

	private int maxIdle = 1 * 60 * 1000;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMinCon() {
		return minCon;
	}

	public void setMinCon(int minCon) {
		this.minCon = minCon;
	}

	public int getMaxCon() {
		return maxCon;
	}

	public void setMaxCon(int maxCon) {
		this.maxCon = maxCon;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("\r\n----------CacheConfig--------------\r\n");
		s.append("----------IP: " + this.getIp() + "---------\r\n");
		s.append("----------Port: " + this.getPort() + "---------\r\n");
		s.append("----------MinCon: " + this.getMinCon() + "---------\r\n");
		s.append("----------MaxCon: " + this.getMaxCon() + "---------\r\n");
		s.append("----------MaxIdle: " + this.getMaxIdle() + "---------\r\n");
		return s.toString();
	}

}
