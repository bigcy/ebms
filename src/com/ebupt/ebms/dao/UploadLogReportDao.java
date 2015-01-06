package com.ebupt.ebms.dao;


import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.UploadLogReport;

/**   
 * @author lishuhua
 * Create time 2011-3-23
 * @version 1.0  
 */
//日志上传完成报告Dao
public interface UploadLogReportDao extends Dao {

	public UploadLogReport findUploadLog(String terminalId, String taskid);

	public List<UploadLogReport> findAllLogReport(String terminalId);

	public void updateItem(String taskid, String status, String terminalId);
	
}