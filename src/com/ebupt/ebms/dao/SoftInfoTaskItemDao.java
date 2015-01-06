package com.ebupt.ebms.dao;


import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.SoftInfoTaskItem;

/**   
 * @author lishuhua
 * Create time 2011-3-7
 * @version 1.0  
 */
//软件升级状态报告Dao
public interface SoftInfoTaskItemDao extends Dao {

	public SoftInfoTaskItem findSoft(String terminalId, String taskId, String version);

	

	public SoftInfoTaskItem findNewTerminalId(String terminalId);



	public void updateItem(String taskid, String status, String terminalId);


	//find softinfotaskitem by terminalid and taskid
	public SoftInfoTaskItem findSoftTaskBy2Tid(String terminalId, String taskIds);
	
	public void deleteSoftInfoTaskItem(String terminalid);
	
}