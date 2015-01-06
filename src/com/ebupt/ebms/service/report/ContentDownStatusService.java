package com.ebupt.ebms.service.report;   

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.ContentDao;
import com.ebupt.ebms.dao.ContentDownStatusDao;
import com.ebupt.ebms.dao.TemplateDao;
import com.ebupt.ebms.entity.Content;
import com.ebupt.ebms.entity.ContentDownStatus;
import com.ebupt.ebms.entity.Template;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**   
 * @author lishuhua
 * Create time 2011-3-4
 * @version 1.0  
 */
@Service
public class ContentDownStatusService {
	
	@Autowired
	@Qualifier("contentDownStatusDaoImpl")
	private ContentDownStatusDao contentDownStatusDao;
	
	@Autowired
	@Qualifier("contentDaoImpl")
	private ContentDao contentDao;
	
	@Autowired
	@Qualifier("templateDaoImpl")
	private TemplateDao templateDao;
	
	private static final Logger log = Logger.getLogger(ContentDownStatusService.class);
	
	public ContentDownStatus findContent(String terminalid, String contentid, String playlistid){
		log.debug("------Enter into ContentDownStatusService findContent method------");
		return contentDownStatusDao.findContent(terminalid, contentid, playlistid);
	}
	
	public String save(String terminalid, String contentid, String playlistid, String status) {
		log.debug("------Enter into ContentDownStatusService add method------");
		try{
			//根据contentid去素材和模板表里面去找对应的名称做资源名称
			Content content = contentDao.getContentType(contentid);
			Template template = templateDao.getTemplateById(contentid);
			String contenttitle = null;
			
			if(content != null){
				contenttitle = content.getContenttitle();
				SQLCache.putTermOperLog(terminalid, "素材["+contenttitle+"]下载完成");
			}else if(template != null){
				contenttitle = template.getName();
				SQLCache.putTermOperLog(terminalid, "模板["+contenttitle+"]下载完成");
			}
			
			ContentDownStatus contentDownStatus = new ContentDownStatus();
			
			contentDownStatus.setSerialno(NumberUtil.generatorId());
			contentDownStatus.setTerminalid(terminalid);
			contentDownStatus.setContentid(contentid);
			contentDownStatus.setContenttitle(contenttitle);
			contentDownStatus.setPlaylistid(playlistid);
			contentDownStatus.setRecvtime(TimeUtil.getTime());
			contentDownStatus.setStatus(status);
			
			contentDownStatusDao.add(contentDownStatus);
			log.debug("ContentDownStatusDao save success!");
			return "0";	
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}
	
	public String update(ContentDownStatus content){
		log.debug("------Enter into ContentDownStatusService update method------");
		try{
			contentDownStatusDao.modify(content);
			return "0";
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}
	
}
  