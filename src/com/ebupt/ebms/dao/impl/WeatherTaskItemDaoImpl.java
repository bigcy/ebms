package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.WeatherTaskItemDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.WeatherTaskItem;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author QiChen Create on 2011-3-8
 * @version 1.0
 */
@Repository
public class WeatherTaskItemDaoImpl extends DaoSupport implements WeatherTaskItemDao {

	private static Logger log = Logger.getLogger(WeatherTaskItemDaoImpl.class);
	
	@Override
	public List<WeatherTaskItem> findNewItemsByTerminalId(String terminalId) {
		try {
			String sql = "from WeatherTaskItem where terminalid = '" + terminalId + "' and status = '0'";
			List<WeatherTaskItem> itmes = new ArrayList<WeatherTaskItem>();
			for (Object obj : find(sql)) {
				if (obj instanceof WeatherTaskItem) {
					itmes.add((WeatherTaskItem) obj);
				}
			}
			return itmes;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;

	}

	@Override
	public void updateItem(String taskid, String status, String terminalId) {
		String sql = "update WeatherTaskItem set status = '" + status + "',recvtime = '" + TimeUtil.getTime()
				+ "'  where weatherid = '" + taskid + "' and terminalid = '" + terminalId + "'";
		SQLCache.put(sql);
		// this.excuteSql(sql);
	}

	@Override
	public void deleteItem(String terminalId) {
		try {
			String sql = "delete from WeatherTaskItem where terminalid = '" + terminalId + "'";
			this.excuteSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

	}

}
