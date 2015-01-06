package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.PlayListGroup;

/**
 * @author QiChen Create on 2011-3-17
 * @version 1.0
 */
public interface PlayListGroupDao extends Dao {

	public List<PlayListGroup> find(String groupid, String playListid);

	public List<PlayListGroup> findBetweenDate(String groupid, String startDate, String endDate);

	public List<PlayListGroup> findPlayListGroup(String groupid);

	public PlayListGroup findPlayListGroupByPidAndGids(String playlistid, String groupids);

	public void updatePlayListGroupStatus(String serialno, String startdate);

	public void updatePlayListGroupStatusToRun(String nowdate);

	public void updatePlayListGroupStatusToEnd(String yestarday);

	public PlayListGroup getPlayListGroupBySerialno(String serialno);
	
	public String getPlaylistidBySerialno(String serialno);
	
	public String getTaskidByPlaylistid(String playlistid);

}