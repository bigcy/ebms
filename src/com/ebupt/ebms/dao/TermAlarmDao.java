package com.ebupt.ebms.dao;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.TermAlarm;

/**   
 * @author lishuhua
 * Create time 2011-03-08
 * @version 1.0  
 */
public interface TermAlarmDao extends Dao {

	public TermAlarm findAlarm(String terminalId, String alarmcode, String time);
	
}