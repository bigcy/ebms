package com.ebupt.ebms.dao;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.TermWorking;

/**   
 * @author lishuhua
 * Create time 2011-03-08
 * @version 1.0  
 */
public interface WorkStatusDao extends Dao {

	public void updateItem(String taskid, String status, String terminalId);

	public void delWorkStatus(String terminalId);

	public TermWorking findWorkStatus(String terminalId);
	
}