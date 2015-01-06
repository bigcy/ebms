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
		//����Ҫ����������֮ǰִ��һ�Σ�������20��֮��ִ��һ�Σ�����������������ݣ���������ͳ�ƣ���������ƻ�һСʱִ��һ��
		onlineTimeDao.createOrUpdateOnlineTime(nowdate);
		log.info("End Statistics Terminal OnlineTime Task...");
	}
}
