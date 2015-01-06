package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.UploadLogReportDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.UploadLogReport;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author lishuhua Create time 2011-3-23
 * @version 1.0
 */
@Repository
public class UploadLogReportDaoImpl extends DaoSupport implements UploadLogReportDao {

	private static Logger log = Logger.getLogger(UploadLogReportDaoImpl.class);
	
	@Override
	public UploadLogReport findUploadLog(String terminalId, String taskid) {
		try {
			String sql = "from UploadLogReport where taskid = '" + taskid + "' and terminalid = '" + terminalId + "'";
			List<UploadLogReport> result = new ArrayList<UploadLogReport>();
			for (Object obj : find(sql)) {
				if (obj instanceof UploadLogReport) {
					result.add((UploadLogReport) obj);
				}
			}
			return result.isEmpty() ? null : result.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;

	}

	@Override
	public List<UploadLogReport> findAllLogReport(String terminalId) {
		try {
			List<UploadLogReport> result = new ArrayList<UploadLogReport>();
			String sql = "from UploadLogReport where terminalid = '" + terminalId + "' and status = '0'";
			for (Object obj : find(sql)) {
				if (obj instanceof UploadLogReport) {
					result.add((UploadLogReport) obj);
				}
			}
			return result.isEmpty() ? null : result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void updateItem(String taskid, String status, String terminalId) {
		String sql = "update UploadLogReport set status = '" + status + "', recvtime = '" + TimeUtil.getTime()
				+ "' where taskid = '" + taskid + "' and terminalid = '" + terminalId + "' ";
		SQLCache.put(sql);
	}

}
