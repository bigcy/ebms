package com.ebupt.ebms.service.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.dao.PlayListGroupDao;
import com.ebupt.ebms.dao.PlayListTaskItemDao;
import com.ebupt.ebms.dao.TermGroupItemDao;
import com.ebupt.ebms.entity.PlayListGroup;
import com.ebupt.ebms.entity.PlayListTaskItem;
import com.ebupt.ebms.entity.TermGroupItem;

/**
 * @author lishuhua
 * Create time 2011-04-25
 * @version 1.0
 */
@Service
public class ResourcesyncService {

	@Autowired
	@Qualifier("playListTaskItemDaoImpl")
	private PlayListTaskItemDao playListTaskItemDao;
	
	@Autowired
	@Qualifier("termGroupItemDaoImpl")
	private TermGroupItemDao termGroupItemDao;
	
	@Autowired
	@Qualifier("playListGroupDaoImpl")
	private PlayListGroupDao playListGroupDao;
	
	private static final Logger log = Logger.getLogger(ResourcesyncService.class);
	
	public List<TermGroupItem> getGroupItemsByTerminalId(String terminalid){
		log.debug("------Enter into ResourcesyncService getGroupItemsByTerminalId method------");
		try{
			return termGroupItemDao.getItemsByTermId(terminalid);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	public List<PlayListTaskItem> getPlayListTaskItems(String terminalid, String playlistid){
		log.debug("------Enter into ResourcesyncService getPlayListTaskItems method------");
		try{			
			return playListTaskItemDao.findItemsByTidPid(terminalid, playlistid);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	public List<PlayListGroup> getPlayListGroup(String groupid) {
		log.debug("------Enter into ResourcesyncService getPlayListGroup method------");
		try{
			return playListGroupDao.findPlayListGroup(groupid);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}
	
}
