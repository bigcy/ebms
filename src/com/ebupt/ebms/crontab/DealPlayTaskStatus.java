package com.ebupt.ebms.crontab;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ebupt.ebms.dao.PlayListGroupDao;
import com.ebupt.ebms.utils.TimeUtil;

public class DealPlayTaskStatus {

	private static Logger log = Logger.getLogger(DealPlayTaskStatus.class);

	@Autowired
	@Qualifier("playListGroupDaoImpl")
	private PlayListGroupDao playListGroupDao;

	public void updatePlayTaskStatus() {
		log.info("Begin DealPlayTaskStatus Task...");
		
		// 更新当天待运行的任务状态为运行
		String nowdate = TimeUtil.getDate();
		
		playListGroupDao.updatePlayListGroupStatusToRun(nowdate);

		// 更新昨天运行的任务状态为执行结束
		String yestarday = TimeUtil.getBeforeDate(1);
		
		playListGroupDao.updatePlayListGroupStatusToEnd(yestarday);

		log.info("End DealPlayTaskStatus Task...");
		
		// 处理没有发布的任务，暂时不用，后续如果有问题增加

	}

	
}
