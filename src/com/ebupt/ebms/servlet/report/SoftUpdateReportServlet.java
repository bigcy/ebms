package com.ebupt.ebms.servlet.report;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.service.task.SoftInfoTaskItemService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ServletUtil;

/**   
 * @author lishuhua
 * Create time 2011-3-7
 * @version 1.0  
 */
@WebServlet("/softupdateReport")
public  class SoftUpdateReportServlet extends AuthServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SoftUpdateReportServlet.class);

	@Override
	public String doAuthGetService(HttpServletRequest request,
			HttpServletResponse response, String terminalId){
		log.debug("Terminalid:" + terminalId +" enter into SoftUpdateReportServlet");
		
		//服务器处理结果,0 成功 ,1 失败
		String result = "1";
		String errorinfo = null;
		
		//获取请求消息中的数据流
		String reqxml = ServletUtil.parseRequsetXML(request);
		log.info("Terminalid:" + terminalId +" reqxml:" + reqxml);
		String resxml = null;

		if (reqxml == null || "".equals(reqxml)){
			log.error("Terminalid:" + terminalId +" SoftUpdateServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}
		
		//解析数据流中的xml信息,获取version
		String version = null;
		String taskId = null;
		String status = null;
		try{
			Document document = DocumentHelper.parseText(reqxml.trim());
			Element root  = document.getRootElement();
			if(root.elementText("taskid") != null && !"".equals(root.elementText("taskid"))){				
				taskId = root.elementText("taskid").trim();
			}else{
				log.error("Terminalid:" + terminalId +" SoftUpdateServlet taskid is null");
				errorinfo = "taskid is null";
				this.lostdata(response, result, errorinfo);
			}
			if(root.elementText("version") != null && !"".equals(root.elementText("version"))){				
				version = root.elementText("version").trim();
			}else{
				log.error("Terminalid:" + terminalId +" SoftUpdateServlet version is null");
				errorinfo = "version null";
				this.lostdata(response, result, errorinfo);
			}
			if(root.elementText("status") != null && !"".equals(root.elementText("status"))){				
				status = root.elementText("status").trim();
			}else{
				log.error("Terminalid:" + terminalId +" SoftUpdateServlet status is null");
				errorinfo = "status is null";
				this.lostdata(response, result, errorinfo);
			}
		}catch(DocumentException e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		SQLCache.putTermOperLog(terminalId, "上报软件升级["+taskId+"]状态["+status+"]");
		SoftInfoTaskItemService softInfoTaskItemService = (SoftInfoTaskItemService)BeanUtil.getBean(this.getServletContext(), "softInfoTaskItemService");
		String softInfoId = taskId;
		
		//平台处理终端的请求
		result = softInfoTaskItemService.update(terminalId,softInfoId,version,status);
		
		if("1".equals(result)){
			errorinfo = "failed to update softinfo status, mismatch database";
		}
		resxml = ServletUtil.returnXMLMessage(result, errorinfo);
		log.info("TerminalId: " + terminalId + " SoftUpdateReportServlet responseContent: " + resxml);
		
		//返回处理的结果
		log.debug("Terminalid:" + terminalId + " exit SoftUpdateReportServlet, treatment result is : " + result);
		return resxml;
	}
	
	public void lostdata(HttpServletResponse response, String result, String errorinfo){
		try {
			response.getWriter().write(ServletUtil.returnXMLMessage(result, errorinfo)); // 失败
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return;
	}
	
		
}
