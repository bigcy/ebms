package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.PlayListGroupDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.PlayListGroup;
import com.ebupt.ebms.utils.ConvertUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author QiChen Create on 2011-3-17
 * @version 1.0
 */
@Repository
public class PlayListGroupDaoImpl extends DaoSupport implements PlayListGroupDao {
	
	private static Logger log = Logger.getLogger(PlayListGroupDaoImpl.class);

	@Override
	public List<PlayListGroup> find(String groupid, String playListid) {
		try {
			List<PlayListGroup> result = new ArrayList<PlayListGroup>();
			String sql = "from PlayListGroup where playlistid = '" + playListid + "' and groupid = '" + groupid
					+ "' and approvestatus = 'P' and status = 'N' ";
			for (Object obj : find(sql)) {
				if (obj instanceof PlayListGroup) {
					result.add((PlayListGroup) obj);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public List<PlayListGroup> findBetweenDate(String groupid, String startDate, String endDate) {
		try {
			List<PlayListGroup> result = new ArrayList<PlayListGroup>();
			String sql = "from PlayListGroup where groupid = '" + groupid + "' and startdate >= '" + startDate
					+ "' and enddate <='" + endDate + "'";
			for (Object obj : find(sql)) {
				if (obj instanceof PlayListGroup) {
					result.add((PlayListGroup) obj);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;

	}

	@Override
	public List<PlayListGroup> findPlayListGroup(String groupid) {
		try {
			List<PlayListGroup> results = new ArrayList<PlayListGroup>();
			String sql = "from PlayListGroup where groupid = '" + groupid + "' and status in ('N','R','P')";
			for (Object obj : find(sql)) {
				if (obj instanceof PlayListGroup) {
					results.add((PlayListGroup) obj);
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
	public PlayListGroup findPlayListGroupByPidAndGids(String playlistid, String groupids) {
		try {
			String[] _groupIds = groupids.split(",");
			String sql = null;
			if (_groupIds.length == 1) {
				sql = "from PlayListGroup where playlistid = '" + playlistid + "' and groupid = '" + _groupIds[0]
						+ "' and approvestatus = 'P' order by createtime DESC";
			} else {
				sql = "from PlayListGroup where playlistid = '" + playlistid + "' and groupid in "
						+ ConvertUtil.getSqlQuery(groupids) + " and approvestatus = 'P' order by createtime DESC";
			}
			log.info("SQL:" + sql);
			List<Object> objs = find(sql);
			if (objs != null && !objs.isEmpty()) {
				if (objs.get(0) instanceof PlayListGroup) {
					return (PlayListGroup) objs.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public void updatePlayListGroupStatus(String serialno, String startdate) {
		if (Long.parseLong(startdate) <= Long.parseLong(TimeUtil.getDate())) {
			String sql = "update PlayListGroup set status = 'R' where serialno = '" + serialno
					+ "' and approvestatus = 'P' and status = 'N' ";
			SQLCache.put(sql);
		}
	}
	
	@Override
	public void updatePlayListGroupStatusToRun(String nowdate) {
		
		String sql = "update PlayListGroup set status = 'R' where startdate = '" + nowdate
				+ "' and status = 'N' and approvestatus = 'P'";

		SQLCache.put(sql);
	}

	@Override
	public void updatePlayListGroupStatusToEnd(String yestarday) {

		String sql = "select playlistid from PlayListGroup where enddate = '" + yestarday
				+ "' and status in ('N','R','P')";
		try {
			for (Object obj : find(sql)) {
				// 先释放执行结束的播放计划对应的播放任务,针对每次查到的播放任务,都要进行释放
				sql = "update PlayList set usedcount = usedcount - 1 where playlistid = '" + (String) obj + "'";
				SQLCache.put(sql);
			}

			// 修改正在运行或暂停的任务状态位执行结束
			sql = "update PlayListGroup set status = 'E' where enddate <= '" + yestarday + "' and status in ('R','P')";
			SQLCache.put(sql);

			// 修改初始化的任务状态为撤销，审批状态为驳回
			sql = "update PlayListGroup set status = 'C', approvestatus = 'R' where enddate <= '" + yestarday
					+ "' and status = 'N' ";
			SQLCache.put(sql);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}

	@Override
	public PlayListGroup getPlayListGroupBySerialno(String serialno) {

		String sql = "from PlayListGroup where serialno = '" + serialno + "'";
		try {
			return (PlayListGroup) findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public String getPlaylistidBySerialno(String serialno) {
		String sql = "select playlistid from PlayListGroup where serialno = '" + serialno + "'";
		try {
			return (String) findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}
	
	@Override
	public String getTaskidByPlaylistid(String playlistid) {
		String sql = "select serialno from PlayListGroup where playlistid = '" + playlistid + "'";
		try {
			return (String) findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
