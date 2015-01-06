package com.ebupt.ebms.service.report;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.dao.AlarmCodeValueDao;
import com.ebupt.ebms.dao.TermAlarmDao;
import com.ebupt.ebms.entity.AlarmCodeValue;
import com.ebupt.ebms.entity.TermAlarm;
import com.ebupt.ebms.utils.NumberUtil;

/**
 * @author lishuhua Create time 2011-3-7
 * @version 1.0
 */
@Service
public class TermAlarmService {

	@Autowired
	@Qualifier("termAlarmDaoImpl")
	private TermAlarmDao termAlarmDao;

	@Autowired
	@Qualifier("alarmCodeValueDaoImpl")
	private AlarmCodeValueDao alarmCodeValueDao;

	private static final Logger log = Logger.getLogger(TermAlarmService.class);

	public String save(String terminalId, String alarmcode, String time, String errorinfo) {
		log.debug("------Enter into TermAlarmService save method------");

		AlarmCodeValue code = alarmCodeValueDao.findCode(alarmcode);
		if (code != null) {
			TermAlarm alarm = termAlarmDao.findAlarm(terminalId, alarmcode, time);
			if (alarm != null) {
				log.error("failed to add alarminfo , database had already exist record");
				return "1";
			} else {
				try {
					log.debug("----- start TermAlarmService save method -----");
					TermAlarm termAlarm = new TermAlarm();
					termAlarm.setSerialno(NumberUtil.generatorId());
					termAlarm.setTerminalid(terminalId);
					termAlarm.setAlarmcode(alarmcode);
					termAlarm.setTime(time);
					if (errorinfo != null) {
						termAlarm.setAlarmvalue(errorinfo);
					} else {
						termAlarm.setAlarmvalue(code.getAlarmvalue());
					}
					termAlarm.setStatus("0");
					termAlarmDao.add(termAlarm);

					log.debug("TermAlarmService save success!");
					return "0";
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage(),e);
				}
			}
		} else {
			log.error("database alarmvalue does't exist alarmcode £º" + alarmcode + "info, please check alarminfo");
			return "1";
		}
		return "1";
	}
	
	public String deleteTermAlarm(String terminalid, String alarmcode) {
		log.debug("------Enter into TermAlarmService deleteTermAlarm method------");
		try {
			String sql = "delete from TermAlarm where terminalid = '" + terminalid + "' and alarmcode = '" + alarmcode
					+ "'";
			termAlarmDao.excuteSql(sql);
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		return "1";
	}

}
