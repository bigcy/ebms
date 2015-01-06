package com.ebupt.ebms.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.ConfigReportDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.TermConfigReport;

/**
 * @author lishuhua Create time 2011-3-7
 * @version 1.0
 */
@Repository
public class ConfigReportDaoImpl extends DaoSupport implements ConfigReportDao {

	private static Logger log = Logger.getLogger(ConfigReportDaoImpl.class);
	
	@Override
	public TermConfigReport findreport(String terminalId) {
		try {
			if (terminalId != null) {
				String sql = "from TermConfigReport where terminalid = '" + terminalId + "'";
				return (TermConfigReport) this.findSingleObject(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

}
