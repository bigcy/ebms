package com.ebupt.ebms.crontab;

import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.TermOperLogDao;
import com.ebupt.ebms.entity.TermOperLog;

public class TermOperLogTimerTask extends TimerTask {

	private static final Logger log = Logger.getLogger(TermOperLogTimerTask.class);

	@Autowired
	@Qualifier("termOperLogDaoImpl")
	private TermOperLogDao termOperLogDao;

	@Override
	public void run() {
		TermOperLog operlog = SQLCache.getTermOperLog();
		if (operlog != null) {
			try {
				termOperLogDao.add(operlog);
				log.debug("Execute TermOperLog Add,Terminalid=" + operlog.getTerminalid());
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
				log.debug("Execute TermOperLog Add Error,Terminalid=" + operlog.getTerminalid() + " Id=" + operlog.getSerialno());
				//将没有正常执行的TermOperLog送回队列
				//SQLCache.putTermOperLog(operlog);
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
