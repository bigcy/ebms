package com.ebupt.ebms.dao;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.TermConfigReport;

/**   
 * @author lishuhua
 * Create time 2011-3-7
 * @version 1.0  
 */
public interface ConfigReportDao extends Dao {

	public TermConfigReport findreport(String terminalId);
	
}