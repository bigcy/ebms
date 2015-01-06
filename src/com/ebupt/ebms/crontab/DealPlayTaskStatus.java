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
		
		// ���µ�������е�����״̬Ϊ����
		String nowdate = TimeUtil.getDate();
		
		playListGroupDao.updatePlayListGroupStatusToRun(nowdate);

		// �����������е�����״̬Ϊִ�н���
		String yestarday = TimeUtil.getBeforeDate(1);
		
		playListGroupDao.updatePlayListGroupStatusToEnd(yestarday);

		log.info("End DealPlayTaskStatus Task...");
		
		// ����û�з�����������ʱ���ã������������������

	}

	
}
