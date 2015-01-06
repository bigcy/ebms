package com.ebupt.ebms.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.SoftInfoDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.SoftInfo;

/**
 * @author lishuhua Create time 2011-3-7
 * @version 1.0
 */
@Repository
public class SoftInfoDaoImpl extends DaoSupport implements SoftInfoDao {

	private static Logger log = Logger.getLogger(SoftInfoDaoImpl.class);
	
	@Override
	public SoftInfo findSoft(String version, String factoryid) {
		try {
			String sql = "from SoftInfo where version !='" + version + "' and factoryid = '"+ factoryid +"' order by version desc";
			return (SoftInfo) this.findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

}
