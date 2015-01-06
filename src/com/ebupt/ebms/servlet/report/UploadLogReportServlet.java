package com.ebupt.ebms.servlet.report;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.entity.UploadLogReport;
import com.ebupt.ebms.service.report.UploadLogReportService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ServletUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**   
 * @author lishuhua
 * Create time 2011-3-22
 * @version 1.0  
 */
@WebServlet("/uploadlogReport")
public  class UploadLogReportServlet extends AuthServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UploadLogReportServlet.class);

	@Override
	public String doAuthGetService(HttpServletRequest request,
			HttpServletResponse response, String terminalId){
		log.debug("Terminalid:" + terminalId +" enter into UploadLogReportServlet");
		
		//服务器处理结果,0 成功 ,1 失败
		String result = "1";
		String errorinfo = null;
		
		//获取请求消息中的数据流
		String reqxml = ServletUtil.parseRequsetXML(request);
		log.info("Terminalid:" + terminalId +" reqxml:" + reqxml);
		String resxml = null;

		if (reqxml == null || "".equals(reqxml)){
			log.error("Terminalid:" + terminalId +" UploadLogReportServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}
		
		//解析数据流中的xml信息,获取version
		String logtype = null;
		String taskid = null;
		String logname = null;
		try{
			Document document = DocumentHelper.parseText(reqxml.trim());
			Element root  = document.getRootElement();
			if(root.elementText("taskid") != null && !"".equals(root.elementText("taskid"))){				
				taskid = root.elementText("taskid").trim();
			}else{
				log.error("Terminalid:" + terminalId +" UploadLogReportServlet taskid is null");
				errorinfo = "UploadLogReportServlet taskid is null";
				resxml = ServletUtil.returnXMLMessage(result, errorinfo);
				return resxml;
			}
			if(root.elementText("logtype") != null && !"".equals(root.elementText("logtype"))){				
				logtype = root.elementText("logtype").trim();
			}else{
				log.error("Terminalid:" + terminalId +" UploadLogReportServlet logtype is null");
				errorinfo = "UploadLogReportServlet logtype is null";
				resxml = ServletUtil.returnXMLMessage(result, errorinfo);
				return resxml;
			}
			if(root.elementText("logname") != null && !"".equals(root.elementText("logname"))){				
				logname = root.elementText("logname").trim();
				//对错误情况的处理目前的意见是将logename保存成error，error文件时已经存在的一个文件
//				if("error".equalsIgnoreCase(logname)){
//					log.error("Terminalid:" + terminalId +" UploadLogReportServlet logname is error");
//					errorinfo = "UploadLogReportServlet logname is error";
//					resxml = ServletUtil.returnXMLMessage(result, errorinfo);
//					return resxml;
//				}
			}else{
				log.error("Terminalid:" + terminalId +" UploadLogReportServlet logname is null");
				errorinfo = "UploadLogReportServlet logname is null";
				resxml = ServletUtil.returnXMLMessage(result, errorinfo);
				return resxml;
			}
		}catch(DocumentException e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		SQLCache.putTermOperLog(terminalId, "完成日志["+logname+"]上传");	
		UploadLogReportService uploadLogReportService = (UploadLogReportService)BeanUtil.getBean(this.getServletContext(), "uploadLogReportService");
		UploadLogReport uploadLog = uploadLogReportService.findUploadLog(terminalId, taskid);
		
		if(uploadLog != null){
			
			if(logtype.equals(uploadLog.getLogtype())){
				uploadLog.setLogname(logname);
				//1：日志上传完成
				uploadLog.setStatus("1");
				uploadLog.setRecvtime(TimeUtil.getTime());
				result = uploadLogReportService.update(uploadLog);
			}else{
				log.error("Terminalid:" + terminalId +" match taskid but mismatch tasktype with database of uploadlogreport");
				errorinfo = "macth taskid but mismatch logtype , terminal logtype is :" + logtype + ",databse logtype is :" + uploadLog.getLogtype();
			}//end if equals compare
			
		}else{
			log.error("Terminalid:" + terminalId +" can not found match record of taskid");
			errorinfo = "terminal taskid mismatch with database taskid ,terminal taskid is : " + taskid;
		}//end uploadlog not null
			
		
		resxml = ServletUtil.returnXMLMessage(result, errorinfo);
		log.info("TerminalId: " + terminalId + " UploadLogReportServlet responseContent: " + resxml);
		
		//返回处理的结果
		log.debug("Terminalid:" + terminalId + " exit UploadLogReportServlet, treatment result is : " + result);
		return resxml;
	}
		
}
