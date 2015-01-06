package com.ebupt.ebms.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.AlarmCodeValueDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.AlarmCodeValue;

/**
 * @author lishuhua Create time 2011-03-14
 * @version 1.0
 */
@Repository
public class AlarmCodeValueDaoImpl extends DaoSupport implements AlarmCodeValueDao {

	private static Logger log = Logger.getLogger(AlarmCodeValueDaoImpl.class);
	
	@Override
	public AlarmCodeValue findCode(String alarmcode) {
		if (alarmcode != null && !"".equals(alarmcode)) {
			try {
				String sql = "from AlarmCodeValue where alarmcode = '" + alarmcode + "'";
				return (AlarmCodeValue) this.findSingleObject(sql);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}
		}
		return null;
	}

}
