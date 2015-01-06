package com.ebupt.ebms.cache.redis;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@WebServlet(name = "redisCacheStart", loadOnStartup = 1,urlPatterns = {"/redisCacheStart"})
public class RedisCacheStart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(CacheClient.class);

	private ConnectionPool pool = null;

	private static RedisCacheStart instance = null;

	private CacheClientConf conf = null;

	private String configPath;

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
	
	public void init() throws ServletException {
		log.info("-------------Redis Cache Pool Start...---------------");
		configPath = this.getServletContext().getRealPath("/")+ "WEB-INF" + File.separator + "redis.xml";
		instance = this;
		readConf(configPath);

		pool = ConnectionPool.getInstance();
		pool.setIp(conf.getIp());
		pool.setPort(conf.getPort());
		pool.setMinCon(conf.getMinCon());
		pool.setMaxCon(conf.getMaxCon());
		pool.setMaxIdle(conf.getMaxIdle() * 1000);
		ConnectionPool.getInstance().init();
	}

	public RedisCacheStart() {
	}
	
	public void destroy() {
		if (pool.isInit()) {
			pool.destroy();
		}
	}

	public static RedisCacheStart getInstance() {
		return instance;
	}

	public void readConf(String confPath) {
		SAXReader reader = new SAXReader();
		Document doc = null;

		try {
			doc = reader.read(new File(confPath));
		} catch (DocumentException e) {
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

	public static RedisClient createRedisClient() {
		return new RedisClient();
	}

	public void setKey(String key, String value) {
		createRedisClient().setObject(key, value);
	}

	public String getKey(String key) {
		return (String) createRedisClient().getObject(key);
	}

}
