package com.ebupt.ebms.servlet.report;   

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.dao.PlayListGroupDao;
import com.ebupt.ebms.entity.ContentDownStatus;
import com.ebupt.ebms.service.report.ContentDownStatusService;
import com.ebupt.ebms.service.report.PlayListStatusService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ServletUtil;

/**   
 * @author lishuhua
 * Create on 2011-04-06
 * @version 1.0 
 *  
 */
@WebServlet("/playlistReport")
public class PlayListStatusServlet extends AuthServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(PlayListStatusServlet.class);
	
	
	@Override
	public String doAuthGetService(HttpServletRequest request, HttpServletResponse response, String terminalId) {
		
		log.debug("Terminalid:" + terminalId +" enter into PlayListStatusServlet");
		
		//服务器处理结果,0 成功 ,1 失败
		String result = "1";
		String res1 = "1";
		String res2 = "1";
		String errorinfo = null;
		
		//获取请求消息中的数据流
		String reqxml = ServletUtil.parseRequsetXML(request);
		log.info("Terminalid:" + terminalId +" reqxml:" + reqxml);
		String resxml = null;

		if (reqxml == null || "".equals(reqxml)){
			log.error("Terminalid:" + terminalId +" PlayListStatusServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}
		
		String taskid = null;
		String status = null;
		HashMap<String,String> hm = new HashMap<String,String>();
		
		try{
			Document document = DocumentHelper.parseText(reqxml.trim());   
            Element root = document.getRootElement(); 
            
            if(root.elementText("taskid") != null && !"".equals(root.elementText("taskid"))){				
            	taskid = root.elementText("taskid").trim();
			}else{
				if (root.elementText("playlistid") != null && !"".equals(root.elementText("playlistid"))) {
					String playlistid = root.elementText("playlistid").trim();
					PlayListGroupDao playListGroupDao = (PlayListGroupDao) BeanUtil.getBean(getServletContext(),
							"playListGroupDaoImpl");
					taskid = playListGroupDao.getTaskidByPlaylistid(playlistid);
					log.info("Playlistid=" + playlistid + ",Taskid=" + taskid);
					if (null == taskid) {
						log.error("Terminalid:" + terminalId + " PlayListid:" + playlistid + " taskid==null");
						errorinfo = "playlistid cann't find in platform";
						resxml = ServletUtil.returnXMLMessage(result, errorinfo);
						return resxml;
					}
				} else {
					log.error("Terminalid:" + terminalId + " PlayListStatusServlet playlistid is null");
					errorinfo = "playlistid is null";
					resxml = ServletUtil.returnXMLMessage(result, errorinfo);
					return resxml;
				}
			}
			if(root.elementText("status") != null && !"".equals(root.elementText("status"))){				
				status = root.elementText("status").trim();
			}else{
				log.error("Terminalid:" + terminalId +" PlayListStatusServlet status is null");
				errorinfo = "status is null";
				resxml = ServletUtil.returnXMLMessage(result, errorinfo);
				return resxml;
			}
			
			/*********************** 读取 **************************/
			int count = 0;
            // 遍历根结点command的所有孩子节点 
			for (Iterator<?> iter = root.elementIterator(); iter.hasNext();) { 
                Element element = (Element) iter.next(); 
                if(element.getName().equals("detail")){
                	 for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext();) { 
	                       Element elementInner = (Element) iterInner.next();  
	                       	if(elementInner.attribute("id") != null && elementInner.attribute("status") != null){  
	                       		hm.put("id"+count, elementInner.attribute("id").getValue());
	                       		hm.put("status"+count, elementInner.attribute("status").getValue());
	                       		count++;
	                       	}
                	 } 
                } 
           } 
		   /*********************** 结束 **************************/
		}catch(DocumentException e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		SQLCache.putTermOperLog(terminalId, "上报播放任务["+taskid+"]状态["+status+"]");
		try{
			PlayListStatusService playListStatusService = (PlayListStatusService)BeanUtil.getBean(getServletContext(), "playListStatusService");
			ContentDownStatusService contentDownStatusService = (ContentDownStatusService)BeanUtil.getBean(getServletContext(), "contentDownStatusService");
			
			res1 = playListStatusService.updatePlayTask(taskid, status, terminalId);
			
			if("1".equals(res1)){
				log.error("Terminalid:" + terminalId +" playlistid can not found or value not correct...");
				errorinfo = "playlistid can not found or value not correct...";
				resxml = ServletUtil.returnXMLMessage(result, errorinfo);
				return resxml;
			}

			/********************* 为了兼容集团协议做修改 ************************/
			if ("0001".equals(status) && 0 == hm.size()) {
				// todo需要根据playlistid找出所有的素材，并更新ContentDownStatus表
				res2 = "0";
			} else {
				/*************************** 存取素材下载日志开始 **********************************/
				for(int i = 0; i < hm.size()/2 ; i++){				
					ContentDownStatus contentDownStatus = contentDownStatusService.findContent(terminalId, hm.get("id"+i), taskid);
					if(contentDownStatus != null){
						contentDownStatus.setStatus(hm.get("status"+i));
						res2 = contentDownStatusService.update(contentDownStatus);
					}else{
						res2 = contentDownStatusService.save(terminalId, hm.get("id"+i), taskid, hm.get("status"+i));
					}
				}
				/*************************** 存取素材下载日志结束 **********************************/
			}
			
			
			log.info("PlayListStatusServlet,res1=" + res1 + ",res2=" + res2);
			if("1".equals(res2)){
				log.error("Terminalid:" + terminalId +" contentdownstaus save failed ...");
				errorinfo = "contentdownstaus save failed ...";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		
		//只有更新播放列表任务明细表中的任务状态成功并且素材下载日志也成功时才返回成功
		if("0".equals(res1) && "0".equals(res2)){
			result = "0";
		}
		
		resxml = ServletUtil.returnXMLMessage(result, errorinfo);
		log.info("TerminalId: " + terminalId + " PlayListStatusServlet responseContent: " + resxml);

		log.debug("Terminalid:" + terminalId + " exit PlayListStatusServlet");
		return resxml;
	}
	
}
  