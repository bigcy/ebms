package com.ebupt.ebms.service.task;   

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.dao.SoftInfoTaskItemDao;
import com.ebupt.ebms.entity.SoftInfoTaskItem;

/**   
 * @author lishuhua
 * Create time 2011-3-7
 * @version 1.0  
 */
@Service
public class SoftInfoTaskItemService {
	
	@Autowired
	@Qualifier("softInfoTaskItemDaoImpl")
	private SoftInfoTaskItemDao softInfoTaskItemDao;
	
	private static final Logger log = Logger.getLogger(SoftInfoTaskItemService.class);
	
	public SoftInfoTaskItem findSoft(String terminalId, String taskId, String version){
		log.debug("------Enter into SoftInfoTaskItemService findSoft method------");
		return softInfoTaskItemDao.findSoft(terminalId, taskId, version);
	}

	public String update(String terminalId, String softInfoId, String version,
			String status) {
		log.debug("------Enter into SoftInfoTaskItemService update method------");
		try{
			SoftInfoTaskItem st = softInfoTaskItemDao.findSoft(terminalId, softInfoId, version);
			
			if(st != null){
				log.debug("start update softinfo status");
//				st.setStatus(status);
//				st.setRecvtime(TimeUtil.getTime());
//				softInfoTaskItemDao.modify(st);
				softInfoTaskItemDao.updateItem(softInfoId, status, terminalId);
				log.debug("succeed to update softinfo status");
				return "0";
			}else{
				log.info("failed to update softinfo status, mismatch database");
				return "1";
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}
	
}
  