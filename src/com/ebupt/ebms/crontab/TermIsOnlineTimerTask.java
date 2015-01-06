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
							// 对超过一小时未连接的终端发起告警
							content = "终端" + terminalid + "已大约" + interval + "小时未连接过平台，最后一次连接时间：" + selecttime;
//							termAlarmService.save(terminalid, "1004", TimeUtil.getTime(), "已大约" + interval
//									+ "小时未连接过平台，最后一次连接时间：" + selecttime);
							log.warn("alarm:" + content);
							// 重启stbclient
							FileUtil.writeStringToFile(MainConfig.getInstance().getWebPath() + "/shell/" + terminalid
									+ ".sh", "killall stbclient");
						} else {
							// 自动清除未连接的告警
//							termAlarmService.deleteTermAlarm(terminalid, "1004");

						}
					} else {
						content = "终端" + terminalid + "始终未连接过平台";
						log.warn("alarm:" + content);
//						termAlarmService.save(terminalid, "1004", TimeUtil.getTime(), "始终未连接过平台，请检查网络！");
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
				mailService.sendMail(email, "无主题", content, null);
			}
		}
	}

}
