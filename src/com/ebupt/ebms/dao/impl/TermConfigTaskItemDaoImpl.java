package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.TermConfigTaskItemDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.TermConfigTaskItem;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author QiChen Create on 2011-3-9
 * @version 1.0
 */
@Repository
public class TermConfigTaskItemDaoImpl extends DaoSupport implements TermConfigTaskItemDao {

	private static Logger log = Logger.getLogger(TermConfigTaskItemDaoImpl.class);
	
	@Override
	public List<TermConfigTaskItem> findItemByTerminalId(String terminalId) {
		try {
			String sql = "from TermConfigTaskItem where terminalid = '" + terminalId + "' and status = '0'";
			List<TermConfigTaskItem> items = new ArrayList<TermConfigTaskItem>();
			for (Object obj : find(sql)) {
				if (obj instanceof TermConfigTaskItem) {
					items.add((TermConfigTaskItem) obj);
				}
			}
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void updateItem(String taskid, String status, String terminalId) {
		String sql = "update TermConfigTaskItem set status = '" + status + "',recvtime = '" + TimeUtil.getTime()
				+ "' where termconfigid = '" + taskid + "' and terminalid = '" + terminalId + "'";

		SQLCache.put(sql);
		// this.excuteSql(sql);
	}

	@Override
	public TermConfigTaskItem findConfigTaskBy2Tid(String terminalId, String taskIds) {
		try {
			String sql = "from TermConfigTaskItem where terminalid = '" + terminalId + "' and termconfigid = '" + taskIds + "'";
			List<TermConfigTaskItem> items = new ArrayList<TermConfigTaskItem>();
			for (Object obj : find(sql)) {
				if (obj instanceof TermConfigTaskItem) {
					items.add((TermConfigTaskItem) obj);
				}
			}
			return items.isEmpty() ? null : items.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void deleteTermConfigTaskItem(String terminalid) {
		try{
			String sql = "delete from TermConfigTaskItem where terminalid = '" + terminalid + "'";
			this.excuteSql(sql);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		
	}

}
