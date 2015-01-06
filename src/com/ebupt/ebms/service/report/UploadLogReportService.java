package com.ebupt.ebms.service.report;   

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.dao.UploadLogReportDao;
import com.ebupt.ebms.entity.UploadLogReport;

/**   
 * @author lishuhua
 * Create time 2011-3-23
 * @version 1.0  
 */
@Service
public class UploadLogReportService {
	
	@Autowired
	@Qualifier("uploadLogReportDaoImpl")
	private UploadLogReportDao uploadLogReportDao;
	
	private static final Logger log = Logger.getLogger(UploadLogReportService.class);
	
	public String update(UploadLogReport uploadLog){
		log.debug("------Enter into UploadLogReportService update method------");
		try{
			uploadLogReportDao.modify(uploadLog);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}

	public UploadLogReport findUploadLog(String terminalId, String taskid) {
		log.debug("------Enter into UploadLogReportService findUploadLog method------");
		return uploadLogReportDao.findUploadLog(terminalId, taskid);
	}
	
}
  