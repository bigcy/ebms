package com.ebupt.ebms.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.WorkStatusDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.TermWorking;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author lishuhua Create time 2011-03-08
 * @version 1.0
 */
@Repository
public class WorkStatusDaoImpl extends DaoSupport implements WorkStatusDao {

	private static Logger log = Logger.getLogger(WorkStatusDaoImpl.class);
	
	@Override
	public void updateItem(String taskid, String status, String terminalId) {
		String sql = "update TermWorking set status = '" + status + "',reporttime = '" + TimeUtil.getTime()
				+ "' where taskid = '" + taskid + "' and terminalid = '" + terminalId + "'";
		SQLCache.put(sql);
		// this.excuteSql(sql);
	}

	@Override
	public void delWorkStatus(String terminalId) {
		try {
			String sql = "delete from TermWorking where terminalid = '" + terminalId + "'";
			this.excuteSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

	}

	@Override
	public TermWorking findWorkStatus(String terminalId) {
		try {
			String sql = "from TermWorking where terminalid = '" + terminalId + "'";
			return (TermWorking) this.findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;

	}

}
