package com.ebupt.ebms.dao;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.AlarmCodeValue;

/**   
 * @author lishuhua
 * Create time 2011-03-14
 * @version 1.0  
 */
public interface AlarmCodeValueDao extends Dao {

	public AlarmCodeValue findCode(String alarmcode);
	
}