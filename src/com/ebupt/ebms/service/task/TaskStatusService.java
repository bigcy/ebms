package com.ebupt.ebms.service.task;   

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.dao.PlayListTaskItemDao;
import com.ebupt.ebms.dao.SoftInfoTaskItemDao;
import com.ebupt.ebms.dao.TermConfigTaskItemDao;
import com.ebupt.ebms.dao.TermPlayingDao;
import com.ebupt.ebms.dao.UploadLogReportDao;
import com.ebupt.ebms.dao.WeatherTaskItemDao;
import com.ebupt.ebms.dao.WorkStatusDao;

/**   
 * @author QiChen
 * Create on 2011-3-9
 * @version 1.0  
 */
@Service
public class TaskStatusService {
	
	private static final Logger log = Logger.getLogger(TaskStatusService.class);
	
	@Autowired
	@Qualifier("playListTaskItemDaoImpl")
	PlayListTaskItemDao playListTaskItemDao;
	
	@Autowired
	@Qualifier("softInfoTaskItemDaoImpl")
	SoftInfoTaskItemDao softInfoTaskItemDao;
	
	@Autowired
	@Qualifier("termConfigTaskItemDaoImpl")
	TermConfigTaskItemDao termConfigTaskItemDao;
	
	@Autowired
	@Qualifier("weatherTaskItemDaoImpl")
	WeatherTaskItemDao weatherTaskItemDao;
	
	@Autowired
	@Qualifier("uploadLogReportDaoImpl")
	UploadLogReportDao uploadLogReportDao;
	
	@Autowired
	@Qualifier("termPlayingDaoImpl")
	TermPlayingDao termPlayingDao;
	
	@Autowired
	@Qualifier("workStatusDaoImpl")
	WorkStatusDao workStatusDao;
	
	public String updatePlayTask(String taskid, String status, String terminalId) {
		try{
			playListTaskItemDao.updateItem(taskid, status, terminalId);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}

	public String updateSoftUpdateTask(String taskid, String status,
			String terminalId) {
		try{
			softInfoTaskItemDao.updateItem(taskid, status, terminalId);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}

	public String updateConfigTask(String taskid, String status, String terminalId) {
		try{
			termConfigTaskItemDao.updateItem(taskid, status, terminalId);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}

	public String updateWeatherTask(String taskid, String status,
			String terminalId) {
		try{
			weatherTaskItemDao.updateItem(taskid, status, terminalId);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}

	public String updateLogFileUpLoad(String taskid, String status,
			String terminalId) {
		try{
			uploadLogReportDao.updateItem(taskid, status, terminalId);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}
	
	public String updateTermPlaying(String taskid, String status,
			String terminalId) {
		try{			
			termPlayingDao.updateItem(taskid, status, terminalId);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}
	
	public String updateTermWorking(String taskid, String status,
			String terminalId) {
		try{
			workStatusDao.updateItem(taskid, status, terminalId);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}
	
	public String updatePlayListTaskItem(String terminalId, String status) {
		try {
			playListTaskItemDao.updatePlayListTaskItem(terminalId, status);
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}

}
  