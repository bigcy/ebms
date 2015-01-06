package com.ebupt.ebms.crontab;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.ExecuteSQLDao;

public class DealSQLTimerTask extends TimerTask {

	private static final Logger log = Logger.getLogger(DealSQLTimerTask.class);

	@Autowired
	@Qualifier("executeSQLDaoImpl")
	private ExecuteSQLDao dao;

	@Override
	public void run() {
		String sql = SQLCache.get();
		if (sql != null) {
			try {
				boolean result = dao.excuteSql(sql);
				log.info("ExecuteSQL:" + sql + " result:" + result);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("ExecuteSQL:" + sql);
				log.error(e.getMessage(), e);
				// 将没有正常执行的sql送回队列
				if (sql.startsWith("update")) {
					SQLCache.put(sql);
				}
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
