package com.ebupt.ebms.cache.redis;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

public class ConnectionPool implements ConnectionPoolMBean {

	private static Logger log = Logger.getLogger(ConnectionPool.class);

	private String ip = "10.1.70.142";

	private int port = 6379;

	private int minCon = 2;

	private int maxCon = 10;

	private long maxIdle = 1000 * 60 * 5; // max idle time for avail sockets

	private final static int step = 2; // pool busy, create socket step

	private boolean isinit = false;

	private boolean start = true;

	private Timer timer = null;

	private static ConnectionPool instance;

	// connection status
	private ConcurrentMap<Connection, Integer> pool = new ConcurrentHashMap<Connection, Integer>();

	private ConcurrentLinkedQueue<Connection> activePool = new ConcurrentLinkedQueue<Connection>();

	private ConnectionPool() {
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public boolean init() {
		log.info("init minCon: " + minCon + " maxCon: " + maxCon);
		if (minCon > 0 && minCon <= maxCon) {
			for (int i = 0; i < minCon; i++) {
				if (!applyNewConnection()) {
					return false;
				}
			}
		} else {
			return false;
		}

		Calendar cal = new GregorianCalendar();
		Date date = cal.getTime();
		TimerTask task = new PoolManager();
		timer = new Timer("PoolManager");
		timer.schedule(task, date);
		isinit = true;
		return true;
	}

	public void destroy() {
		start = false;
		if (timer != null) {
			timer.cancel();
		}
		Iterator<Connection> iter = pool.keySet().iterator();
		while (iter.hasNext()) {
			Connection con = iter.next();
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}
		}
		pool.clear();
		isinit = false;
	}

	public Connection get() {
		Connection con = null;
		while (con == null) {
			con = activePool.poll();
			if (con == null && !applyNewConnection()) { // 没有连接，且申请不到连接
				waitTime(50);
			}
		}
		pool.replace(con, ConnectionStatus.ACTIVE.status(),
				ConnectionStatus.BUSY.status());
		return con;
	}

	public void put(Connection con) {
		pool.replace(con, ConnectionStatus.BUSY.status(),
				ConnectionStatus.ACTIVE.status());
		activePool.add(con);
	}

	private boolean applyNewConnection() {
		if (pool.size() < maxCon) {
			Connection con = createConnection();
			try {
				con.open();
				pool.put(con, ConnectionStatus.ACTIVE.status());
				activePool.add(con);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
				return false;
			}
			return true;
		} else {
			// log.info("!!!applyNewConnection size to Max : " + pool.size());
		}
		return false;
	}

	private Connection createConnection() {
		return new Connection(ip, port);
	}

	class PoolManager extends TimerTask {

		private int freeCount = 0;

		private int sleepTime = 5000;

		@Override
		public void run() {
			log.info("-----------------pool manager start...------------------");

			while (start) {

				waitTime(sleepTime);

				// 链路检查
				for (int i = 0; i < activePool.size(); i++) {
					Connection con = activePool.poll();
					if (con != null && !con.checkActive()) {
						log.info("====pool reconnect!!!");
						con.reconnect();
					}
					activePool.add(con);
				}

				// 释放过长时间闲置的资源
				if (pool.size() > minCon && (activePool.size() - step) > 0) {
					freeCount++;
				} else {
					freeCount = 0;
				}
				if ((freeCount * sleepTime) > maxIdle) {
					int c = 0;
					while (c < step) {
						if (deadConnection()) {
							c++;
						}
					}
					freeCount = 0;
				}

			}
		}
	}

	private void waitTime(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}

	private boolean deadConnection() {
		Connection con = activePool.poll();
		if (con != null) {
			pool.remove(con, ConnectionStatus.ACTIVE.status());
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}
			return true;
		}
		return false;
	}

	public int getPoolTotalSize() {
		return pool.size();
	}

	public int getPoolActiveSize() {
		return activePool.size();
	}

	public int getPoolBusySize() {
		int size = 0;
		Iterator<Connection> iter = pool.keySet().iterator();
		while (iter.hasNext()) {
			Connection con = iter.next();
			Integer status = pool.get(con);
			if (status != null && status.equals(ConnectionStatus.BUSY.status())) {
				size++;
			}
		}
		return size;
	}

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

	public long getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(long maxIdle) {
		this.maxIdle = maxIdle;
	}

	public boolean isInit() {
		return isinit;
	}
}
