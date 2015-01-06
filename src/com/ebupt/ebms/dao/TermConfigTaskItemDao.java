package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.TermConfigTaskItem;

/**   
 * @author QiChen
 * Create on 2011-3-9
 * @version 1.0  
 */
public interface TermConfigTaskItemDao extends Dao {

	public List<TermConfigTaskItem> findItemByTerminalId(String terminalId);

	public void updateItem(String taskid, String status, String terminalId);

	//find termconfigtaskitem by terminalid and taskid
	public TermConfigTaskItem findConfigTaskBy2Tid(String terminalId, String taskIds);
	
	public void deleteTermConfigTaskItem(String terminalid);

}