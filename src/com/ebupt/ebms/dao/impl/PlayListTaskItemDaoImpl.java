package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.PlayListTaskItemDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.PlayListTaskItem;
import com.ebupt.ebms.utils.ConvertUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author QiChen Create on 2011-3-8
 * @version 1.0
 */
@Repository
public class PlayListTaskItemDaoImpl extends DaoSupport implements PlayListTaskItemDao {

	private static Logger log = Logger.getLogger(PlayListTaskItemDaoImpl.class);

	@Override
	public List<PlayListTaskItem> findNewTerminalId(String terminalId) {
		try {
			List<PlayListTaskItem> results = new ArrayList<PlayListTaskItem>();

			String sql = "select a from PlayListTaskItem as a where terminalid = '" + terminalId + "' and status = '0'";

			for (Object obj : find(sql)) {
				if (obj instanceof PlayListTaskItem) {
					results.add((PlayListTaskItem) obj);
				}
			}

			return results;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public List<PlayListTaskItem> findItemsByTerminalId(String terminalId) {
		try {
			List<PlayListTaskItem> results = new ArrayList<PlayListTaskItem>();

			String sql = "from PlayListTaskItem where terminalid = '" + terminalId + "' and status in ('1','2','3')";

			for (Object obj : find(sql)) {
				if (obj instanceof PlayListTaskItem) {
					results.add((PlayListTaskItem) obj);
				}
			}

			return results;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void updateItem(String taskid, String status, String terminalId) {
		// 此处taskid为PlayListTaskItem表serialno
		String sql = "update PlayListTaskItem set status = '" + status + "',recvtime = '" + TimeUtil.getTime()
				+ "' where playlistid = '" + taskid + "' and terminalid = '" + terminalId + "'";
		SQLCache.put(sql);
		// this.excuteSql(sql);
	}

	@Override
	public PlayListTaskItem findPlayListTaskItem(String taskid, String terminalId) {
		try {
			if (terminalId != null && !"".equals(terminalId) && taskid != null && !"".equals(taskid)) {
				String sql = "from PlayListTaskItem where terminalid = '" + terminalId + "' and playlistid = '"
						+ taskid + "'";
				return (PlayListTaskItem) this.findSingleObject(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		return null;
	}

	@Override
	public List<PlayListTaskItem> findItemsByTidPid(String terminalid, String playlistid) {
		try {
			String sql = "from PlayListTaskItem where terminalid = '" + terminalid + "' and playlistid = '"
					+ playlistid + "'";
			List<PlayListTaskItem> results = new ArrayList<PlayListTaskItem>();
			for (Object obj : find(sql)) {
				if (obj instanceof PlayListTaskItem) {
					results.add((PlayListTaskItem) obj);
				}
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void updatePlayListTaskItem(String terminalId, String playlistid, String status) {
		try {
			String sql = "update PlayListTaskItem set status = '" + status + "' where terminalid = '" + terminalId
					+ "' and playlistid = '" + playlistid + "'";
			excuteSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}

	@Override
	public void updatePlayListTaskItem(String terminalId, String status) {
		try {
			// 将过期和撤销的任务滤掉
			String sql = "select distinct a.playlistid from PlayListTaskItem as a,PlayListGroup as b where a.terminalid = '"
					+ terminalId + "' and a.playlistid = b.serialno and b.status in ('N','R','P')";
			log.info("SQL=" + sql);
			ArrayList<String> result = new ArrayList<String>();
			for (Object obj : find(sql)) {
				result.add((String) obj);
			}
			if (result != null && result.size() > 0) {
				sql = "update PlayListTaskItem set status = '" + status + "' where terminalid = '" + terminalId
						+ "' and playlistid in " + ConvertUtil.getSqlQuery(result) + "";
				excuteSql(sql);
				log.info("SQL=" + sql);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}
}
