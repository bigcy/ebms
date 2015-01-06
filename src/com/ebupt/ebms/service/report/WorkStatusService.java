package com.ebupt.ebms.service.report;   

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.dao.WorkStatusDao;
import com.ebupt.ebms.entity.TermWorking;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**   
 * @author lishuhua
 * Create time 2011-3-7
 * @version 1.0  
 */
@Service
public class WorkStatusService {
	
	@Autowired
	@Qualifier("workStatusDaoImpl")
	private WorkStatusDao workStatusDao;
	
	private static final Logger log = Logger.getLogger(WorkStatusService.class);

	public String save(String terminalId, String playerstatus, String extype,
			String exstatus, String taskid, String createtime) {
		log.debug("------Enter into WorkStatusService save method------");
			 try{
					log.debug("----- start WorkStatusService save method -----");
					
					TermWorking working = new TermWorking();
					working.setSerialno(NumberUtil.generatorId());
					working.setTerminalid(terminalId);
					if(playerstatus != null && !"".equals(playerstatus)){							
						working.setPlayerstatus(playerstatus);
					}
					if(extype != null && !"".equals(extype)){							
						working.setExtype(extype);
					}
					if(exstatus != null && !"".equals(exstatus)){							
						working.setExstatus(exstatus);
					}
					working.setReporttime(TimeUtil.getTime());
					working.setStatus("1");
					working.setTaskid(taskid);
					working.setCreatetime(createtime);
					workStatusDao.add(working);

					log.debug("WorkStatusService save success!");
					return "0";	        
			 }catch(Exception e){
				 e.printStackTrace();
				 log.error(e.getMessage(),e);
			 }
			 return "1";
	}

	public TermWorking findWorkStatus(String terminalId) {
		log.debug("------Enter into WorkStatusService findWorkStatus method------");
		return (TermWorking)workStatusDao.findWorkStatus(terminalId);
	}

	public String update(String terminalId, String playerstatus, String extype,
			String exstatus) {
		log.debug("------Enter into WorkStatusService update method------");
		try{
			TermWorking termWorking = this.findWorkStatus(terminalId);
			if(termWorking != null){
				if(playerstatus != null && !"".equals(playerstatus)){
					termWorking.setPlayerstatus(playerstatus);
				}
				if(extype != null && !"".equals(extype)){
					termWorking.setExtype(extype);
				}
				if(exstatus != null && !"".equals(exstatus)){
					termWorking.setExstatus(exstatus);
				}
				termWorking.setStatus("1");
				termWorking.setReporttime(TimeUtil.getTime());
				workStatusDao.modify(termWorking);
				return "0";
			}else{
				log.info("数据库中没有相应的任务记录！");
				return "1";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}

	public void delWorkStatus(String terminalId) {
		log.debug("------Enter into WorkStatusService delWorkStatus method------");
		workStatusDao.delWorkStatus(terminalId);
	}

	
}
  