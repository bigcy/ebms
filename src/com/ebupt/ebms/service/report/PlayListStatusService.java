package com.ebupt.ebms.service.report;   

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.dao.PlayListTaskItemDao;
import com.ebupt.ebms.entity.PlayListTaskItem;

/**   
 * @author QiChen
 * Create on 2011-3-9
 * @version 1.0  
 */
@Service
public class PlayListStatusService {
	
	@Autowired
	@Qualifier("playListTaskItemDaoImpl")
	PlayListTaskItemDao playListTaskItemDao;
	
	private static final Logger log = Logger.getLogger(PlayListStatusService.class);
	
	public String updatePlayTask(String taskid, String status,
			String terminalId) {
		log.debug("------Enter into PlayListStatusService updatePlayTask method------");
		try{			
			PlayListTaskItem item = playListTaskItemDao.findPlayListTaskItem(taskid, terminalId);
			if(item != null){
				item.setStatus(status);
				playListTaskItemDao.modify(item);
				return "0";
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}

}
  