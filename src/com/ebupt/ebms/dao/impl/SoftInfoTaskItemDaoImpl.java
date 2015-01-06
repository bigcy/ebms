package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.SoftInfoTaskItemDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.SoftInfoTaskItem;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author lishuhua Create time 2011-3-7
 * @version 1.0
 */
@Repository
public class SoftInfoTaskItemDaoImpl extends DaoSupport implements SoftInfoTaskItemDao {

	private static Logger log = Logger.getLogger(SoftInfoTaskItemDaoImpl.class);
	
	@Override
	public SoftInfoTaskItem findSoft(String terminalId, String taskId, String version) {
		try {
			if (terminalId != null && !"".equals(terminalId) && taskId != null && !"".equals(taskId) && version != null
					&& !"".equals(version)) {
				String sql = "from SoftInfoTaskItem where terminalid = '" + terminalId + "' and softinfoid = '"
						+ taskId + "' and version = '" + version + "'";
				return (SoftInfoTaskItem) this.findSingleObject(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		return null;
	}

	@Override
	public SoftInfoTaskItem findNewTerminalId(String terminalId) {
		try {
			String sql = "from SoftInfoTaskItem where terminalid = '" + terminalId
					+ "' and status = '0' order by createtime Desc";
			List<SoftInfoTaskItem> result = new ArrayList<SoftInfoTaskItem>();

			for (Object obj : find(sql)) {
				if (obj instanceof SoftInfoTaskItem) {
					result.add((SoftInfoTaskItem) obj);
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
	public void updateItem(String taskid, String status, String terminalId) {

		String sql = "update SoftInfoTaskItem set status = '" + status + "',recvtime = '" + TimeUtil.getTime()
				+ "' where softinfoid = '" + taskid + "' and terminalid = '" + terminalId + "'";
		SQLCache.put(sql);
		// this.excuteSql(sql);
	}

	@Override
	public SoftInfoTaskItem findSoftTaskBy2Tid(String terminalId, String taskIds) {
		try {
			String sql = "from SoftInfoTaskItem where terminalid = '" + terminalId + "' and softinfoid= '" + taskIds + "'";
			List<SoftInfoTaskItem> result = new ArrayList<SoftInfoTaskItem>();

			for (Object obj : find(sql)) {
				if (obj instanceof SoftInfoTaskItem) {
					result.add((SoftInfoTaskItem) obj);
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
	public void deleteSoftInfoTaskItem(String terminalid) {
		try{
			String sql = "delete from SoftInfoTaskItem where terminalid ='" + terminalid + "'";
			this.excuteSql(sql);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		
	}

}
