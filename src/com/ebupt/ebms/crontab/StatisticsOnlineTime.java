package com.ebupt.ebms.crontab;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ebupt.ebms.dao.OnlineTimeDao;
import com.ebupt.ebms.utils.TimeUtil;

public class StatisticsOnlineTime {
	private static Logger log = Logger.getLogger(StatisticsOnlineTime.class);

	@Autowired
	@Qualifier("onlineTimeDaoImpl")
	private OnlineTimeDao onlineTimeDao;

	public void termOnlineTime() {
		log.info("Begin Statistics Terminal OnlineTime Task...");
		String nowdate = TimeUtil.getDate();
		//至少要在早上六点之前执行一次，在晚上20点之后执行一次，早上是清除缓存数据，晚上是做统计，所以这个计划一小时执行一次
		onlineTimeDao.createOrUpdateOnlineTime(nowdate);
		log.info("End Statistics Terminal OnlineTime Task...");
	}
}
