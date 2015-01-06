package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.TermPlaying;

/**   
 * @author lishuhua
 * Create time 2011-03-08
 * @version 1.0  
 */
public interface TermPlayingDao extends Dao {

	public List<TermPlaying> findTermPlaying(String terminalId);

	public void updateItem(String taskid, String status, String terminalId);
	
	public void updatePicture(String terminalId, String picture, String time);
	
	public void delTermPlaying(String terminalId);

	public void delTermPlaying(String terminalId, String taskid);

	public TermPlaying findTermPlaying(String terminalId, String taskid);
	
}