package com.ebupt.ebms.servlet.report;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.entity.TermWorking;
import com.ebupt.ebms.service.report.WorkStatusService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ServletUtil;


/**   
 * @author lishuhua
 * Create time 2011-3-8
 * @version 1.0  
 */
@WebServlet("/workstatusReport")
public  class WorkStatusReportServlet extends AuthServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(WorkStatusReportServlet.class);


	@Override
	public String doAuthGetService(HttpServletRequest request, HttpServletResponse response,
			String terminalId){
		log.debug("Terminalid:" + terminalId +" enter into WorkStatusReportServlet");
		
		//服务器处理结果,0 成功 ,1 失败
		String result = "1";
		String errorinfo = null;
		
		//获取请求消息中的数据流
		String reqxml = ServletUtil.parseRequsetXML(request);
		log.info("Terminalid:" + terminalId +" reqxml:" + reqxml);
		String resxml = null;

		if (reqxml == null || "".equals(reqxml)){
			log.error("Terminalid:" + terminalId +" WorkStatusReportServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}		
		
		String playerstatus = null;
        String taskid = null;
        
		//解析数据流中的xml信息
		Set<Exdevice> hm=new HashSet<Exdevice>();     
        try {   
            Document document = DocumentHelper.parseText(reqxml.trim());  
            Element root = document.getRootElement();  
   
            for (Iterator<?> iter = root.elementIterator(); iter.hasNext();) { 
                Element element = (Element) iter.next(); 
                
                if(element.getName().equals("playerstatus")){                	
                	playerstatus = element.getTextTrim();
                }
                if(element.getName().equals("taskid")){                	
                	taskid = element.getTextTrim();
                }
                /*********************** 读取 **************************/
                    if(element.getName().equals("exdevice")){
                    	Exdevice exdevice = new Exdevice();
                    	 for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext();) {		 
    	                       Element elementInner = (Element) iterInner.next();
    	                       if(elementInner.getName().equals("type")){   
    	                    	   exdevice.setType(elementInner.getText());
    	                    	   
    	                       }
    	                       if(elementInner.getName().equals("status")){   
    	                    	   exdevice.setStatus(elementInner.getText());
    	                       }
                    	 } 
                    	 hm.add(exdevice);
                    } 
    		   /*********************** 结束 **************************/ 
           }        
        } catch (DocumentException e) {    
            e.printStackTrace();   
            log.error(e.getMessage(),e);
        } 
		
		if(playerstatus == null || taskid == null){
			log.error("Terminalid:" + terminalId +" WorkStatusReportServlet playerstatus or taskid is null");
			errorinfo = "playerstatus or taskid is null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}
		SQLCache.putTermOperLog(terminalId, "工作状态上报，状态["+playerstatus+"]");
		
		try {
			int hmsize = hm.size();
			WorkStatusService workStatusService = (WorkStatusService)BeanUtil.getBean(this.getServletContext(), "workStatusService");
			
			TermWorking termWorking = workStatusService.findWorkStatus(terminalId);
			
			if(termWorking != null){
				
				String taskId = termWorking.getTaskid();
				
				if((taskid).equals(taskId)){
					String createTime = termWorking.getCreatetime();
					
					if(hmsize > 0){
						workStatusService.delWorkStatus(terminalId);
						for(Exdevice exdevice : hm){
							result = workStatusService.save(terminalId, playerstatus, exdevice.getType(),exdevice.getStatus(), taskId, createTime);
						}					
					}else{
						workStatusService.delWorkStatus(terminalId);
						result = workStatusService.save(terminalId, playerstatus, null, null, taskId, createTime);
					}//end if m > 0
					
				}else{
					log.error("Terminalid:" + terminalId +" mismacth taskid with database, please check taskid of terminal workstatusreport");
					errorinfo = "mismatch taskid data,terminal report taskid is :" + taskid + " ,but the database taskId is :" + taskId;
				}//end taskid equals
				
			}else{
				log.error("Terminalid:" + terminalId +" have no record in database");
				errorinfo = "can not found need record in database";
			}//end if termworking not null
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		resxml = ServletUtil.returnXMLMessage(result, errorinfo);
		log.info("TerminalId: " + terminalId + " WordStatusReportServlet responseContent: " + resxml);
		
		//返回处理的结果
		log.debug("Terminalid:" + terminalId + " exit WorkStatusReportServlet, treatment result is : " + result);
		return resxml;
	}
	
	class  Exdevice{
		private String type;
		
		private String status;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
	}

}
