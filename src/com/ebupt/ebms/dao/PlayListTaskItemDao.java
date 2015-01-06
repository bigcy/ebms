package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.PlayListTaskItem;

/**   
 * @author QiChen
 * Create on 2011-3-8
 * @version 1.0  
 */
public interface PlayListTaskItemDao extends Dao {

	public List<PlayListTaskItem> findNewTerminalId(String terminalId);

	public List<PlayListTaskItem> findItemsByTerminalId(String terminalId);

	public void updateItem(String taskid, String status, String terminalId);

	public PlayListTaskItem findPlayListTaskItem(String playlistid,
			String terminalId);

	public List<PlayListTaskItem> findItemsByTidPid(String terminalid,
			String playlistid);
	
	public void updatePlayListTaskItem(String terminalId, String playlistid, String status);
	
	public void updatePlayListTaskItem(String terminalId, String status);

}