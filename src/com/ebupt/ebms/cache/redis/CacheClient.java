package com.ebupt.ebms.cache.redis;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CacheClient {

	private static Logger log = Logger.getLogger(CacheClient.class);

	private ConnectionPool pool = null;

	private static CacheClient instance = null;

	private CacheClientConf conf = null;
	
	private String configPath = "./conf/cacheConfig.xml";

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

	private CacheClient() {
	}

	public static CacheClient getInstance() {
		if (instance == null) {
			instance = new CacheClient();
		}
		return instance;
	}

	public void readConf(String confPath) {
		SAXReader reader = new SAXReader();
		Document doc = null;

		try {
			doc = reader.read(new File(confPath));
		} catch (DocumentException e) {
			// log.error(e.getStackTrace());
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		conf = new CacheClientConf();

		Element root = doc.getRootElement();
		Element client = root.element("Client");
		conf.setIp(client.element("ip").getText());
		conf.setPort(Integer.parseInt(client.element("port").getText()));
		conf.setMinCon(Integer.parseInt(client.element("minCon").getText()));
		conf.setMaxCon(Integer.parseInt(client.element("maxCon").getText()));
		conf.setMaxIdle(Integer.parseInt(client.element("maxIdle").getText()));
		log.info(conf.toString());
	}

	public boolean start() {
		log.info("-------------Redis Cache Pool Start...---------------");
		
		readConf(configPath);
		
		pool = ConnectionPool.getInstance();
		pool.setIp(conf.getIp());
		pool.setPort(conf.getPort());
		pool.setMinCon(conf.getMinCon());
		pool.setMaxCon(conf.getMaxCon());
		pool.setMaxIdle(conf.getMaxIdle() * 1000);
		return ConnectionPool.getInstance().init();
	}

	public void stop() {
		if (pool.isInit()) {
			pool.destroy();
		}
	}

	public RedisClient createRedisClient() {
		return new RedisClient();
	}

}
