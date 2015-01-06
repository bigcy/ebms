package com.ebupt.ebms.cache.redis;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

public class Connection {

	private static Logger log = Logger.getLogger(Connection.class);

	private String ip = null;

	private int port = 6379;

	private Jedis jedis = null;

	public Connection(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void open() {
		do {
			try {
				jedis = new Jedis(ip, port);
			} catch (Exception e) {
				log.info(e.getMessage());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				continue;
			}
		} while ((jedis == null));
		log.info("jedis get client instance!!!");
	}

	public void close() {
		if (jedis != null) {
			try {
				jedis.quit();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}
			jedis.disconnect();
			jedis = null;
		}
	}

	public void reconnect() {
		close();
		open();
	}

	public boolean checkActive() {
		if (jedis != null) {
			try {
				jedis.ping();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				return false;
			}
		}
		return false;
	}

	public Jedis getClient() {
		return jedis;
	}
}
