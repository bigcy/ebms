package com.ebupt.ebms.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.TermAlarmDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.TermAlarm;

/**
 * @author lishuhua Create time 2011-03-08
 * @version 1.0
 */
@Repository
public class TermAlarmDaoImpl extends DaoSupport implements TermAlarmDao {

	private static Logger log = Logger.getLogger(TermAlarmDaoImpl.class);
	
	@Override
	public TermAlarm findAlarm(String terminalId, String alarmcode, String time) {
		try {
			String sql = "from TermAlarm where terminalid = '" + terminalId + "' and alarmcode = '" + alarmcode
					+ "' and time = '" + time + "'";
			return (TermAlarm) this.findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

}
