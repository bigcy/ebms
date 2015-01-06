package com.ebupt.ebms.crontab;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ebupt.ebms.cache.TermInfoCache;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.dao.TerminalDao;
import com.ebupt.ebms.service.report.TermAlarmService;
import com.ebupt.ebms.service.task.MailService;
import com.ebupt.ebms.service.task.SmsService;
import com.ebupt.ebms.utils.FileUtil;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.TimeUtil;

public class TermIsOnlineTimerTask extends TimerTask {

	@Autowired
	@Qualifier("terminalDaoImpl")
	private TerminalDao terminalDao;

	@Autowired
	@Qualifier("smsService")
	private SmsService smsService;

	@Autowired
	@Qualifier("mailService")
	private MailService mailService;

	@Autowired
	@Qualifier("termAlarmService")
	private TermAlarmService termAlarmService;
	
	private static final Logger log = Logger.getLogger(TermIsOnlineTimerTask.class);

	@Override
	public void run() {
		int hour = new GregorianCalendar().get(Calendar.HOUR_OF_DAY);
		if (hour >= 9 && hour < 18) {
			ArrayList<String> terminalids = terminalDao.getAllTerminals();
			if (terminalids != null) {
				for (String terminalid : terminalids) {
					String content = null;
					String selecttime = TermInfoCache.getTermSelectTime(terminalid);
					if (selecttime != null) {
						Date dselecttime = TimeUtil.getParseTime(selecttime);
						long interval = (System.currentTimeMillis() - dselecttime.getTime()) / 3600000;
						if (interval >= 1) {
							// �Գ���һСʱδ���ӵ��ն˷���澯
							content = "�ն�" + terminalid + "�Ѵ�Լ" + interval + "Сʱδ���ӹ�ƽ̨�����һ������ʱ�䣺" + selecttime;
//							termAlarmService.save(terminalid, "1004", TimeUtil.getTime(), "�Ѵ�Լ" + interval
//									+ "Сʱδ���ӹ�ƽ̨�����һ������ʱ�䣺" + selecttime);
							log.warn("alarm:" + content);
							// ����stbclient
							FileUtil.writeStringToFile(MainConfig.getInstance().getWebPath() + "/shell/" + terminalid
									+ ".sh", "killall stbclient");
						} else {
							// �Զ����δ���ӵĸ澯
//							termAlarmService.deleteTermAlarm(terminalid, "1004");

						}
					} else {
						content = "�ն�" + terminalid + "ʼ��δ���ӹ�ƽ̨";
						log.warn("alarm:" + content);
//						termAlarmService.save(terminalid, "1004", TimeUtil.getTime(), "ʼ��δ���ӹ�ƽ̨���������磡");
					}

					if (content != null) {
						sendSms(content);
//						sendMail(content);
					}

				}
				terminalids.clear();
			}
		}

	}

	public void sendSms(String content) {
		Set<String> phones = NumberUtil.splitNumber(MainConfig.getInstance().getPhoneNumber());
		if (phones != null && phones.size() > 0) {
			for (String phone : phones) {
				smsService.sendSMS(phone, content);
			}
		}
	}

	public void sendMail(String content) {
		Set<String> emails = NumberUtil.splitNumber(MainConfig.getInstance().getEmail());
		if (emails != null && emails.size() > 0) {
			for (String email : emails) {
				mailService.sendMail(email, "������", content, null);
			}
		}
	}

}
