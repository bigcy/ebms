package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.TermPlayingDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.TermPlaying;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author lishuhua Create time 2011-03-08
 * @version 1.0
 */
@Repository
public class TermPlayingDaoImpl extends DaoSupport implements TermPlayingDao {

	private static Logger log = Logger.getLogger(TermPlayingDaoImpl.class);

	@Override
	public List<TermPlaying> findTermPlaying(String terminalId) {
		try {
			List<TermPlaying> results = new ArrayList<TermPlaying>();
			String sql = "from TermPlaying where terminalid = '" + terminalId + "'";
			for (Object obj : find(sql)) {
				if (obj instanceof TermPlaying) {
					results.add((TermPlaying) obj);
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
		String sql = "update TermPlaying set status = '" + status + "' , recvtime = '" + TimeUtil.getTime()
				+ "' where taskid = '" + taskid + "' and terminalid = '" + terminalId + "'";
		SQLCache.put(sql);
		// this.excuteSql(sql);
	}

	@Override
	public void delTermPlaying(String terminalId, String taskid) {
		try {
			String sql = "delete from TermPlaying where terminalid = '" + terminalId + "' and taskid = '" + taskid
					+ "'";
			this.excuteSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}

	@Override
	public void delTermPlaying(String terminalId) {
		try {
			String sql = "delete from TermPlaying where terminalid = '" + terminalId + "'";
			this.excuteSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

	}

	@Override
	public TermPlaying findTermPlaying(String terminalId, String taskid) {
		try {
			String sql = "from TermPlaying where terminalid = '" + terminalId + "' and taskid = '" + taskid + "'";
			return (TermPlaying) this.findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void updatePicture(String terminalId, String picture, String time) {
		String sql = "update TermPlaying set picture = '" + picture + "',time = '" + time
				+ "' where  terminalid = '" + terminalId + "'";
		this.excuteSql(sql);
	}
}
