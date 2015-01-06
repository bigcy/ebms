package com.ebupt.ebms.servlet.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.entity.SoftInfo;
import com.ebupt.ebms.service.task.SoftInfoService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ServletUtil;
import com.ebupt.ebms.utils.XMLUtil;

/**
 * @author lishuhua Create time 2011-3-7
 * @version 1.0
 */
@WebServlet("/softupdate")
public class SoftUpdateServlet extends AuthServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SoftUpdateServlet.class);

	@Override
	public String doAuthGetService(HttpServletRequest request, HttpServletResponse response, String terminalId) {

		log.debug("Terminalid:" + terminalId + " enter into SoftUpdateServlet");

		// 服务器处理结果,0 成功 ,1 失败
		String result = "1";
		String errorinfo = null;

		// 获取请求消息中的数据流
		String reqxml = ServletUtil.parseRequsetXML(request);
		log.info("Terminalid:" + terminalId + " reqxml:" + reqxml);

		String resxml = null;

		if (reqxml == null || "".equals(reqxml)) {
			log.error("Terminalid:" + terminalId + " SoftUpdateServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}

		// 解析数据流中的xml信息,获取version
		String version = null;
		try {
			Document document = DocumentHelper.parseText(reqxml);
			Element root = document.getRootElement();
			if (root.elementText("version") != null && !"".equals(root.elementText("version"))) {
				version = root.elementText("version").trim();
			} else {
				log.error("Terminalid:" + terminalId + " SoftUpdateServlet version is null");
				errorinfo = "version is null";
				resxml = ServletUtil.returnXMLMessage(result, errorinfo);
				return resxml;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		SQLCache.putTermOperLog(terminalId, "请求软件升级，当前版本号[" + version + "]");
		/************* 对version格式为[厂商序号].[版本号]（版本号格式为x.x）的兼容性处理 ***************/
		// //used for judge version type
		// int count=0;
		//
		// for(int i = 0; i < version.length(); i++){
		// if(version.charAt(i) == '.'){
		// count++;
		// }
		// }
		//
		// if(count > 2){
		// log.error("Terminalid:" + terminalId
		// +" version type error,received version type is :" + version);
		// errorinfo = "version type error, received version type is :" +
		// version;
		// resxml = ServletUtil.returnXMLMessage(result, errorinfo);
		// return resxml;
		// }else if(count == 2){
		// version = version.substring(version.indexOf(".") + 1);
		// }
		/************ 对version格式为[厂商序号].[版本号]（版本号格式为x.x）的兼容性处理 ****************/

		// compare version with format x.y length of x is not limited , length
		// of y is 1~3
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,3})?$");
		Matcher match = pattern.matcher(version);

		if (match.matches()) {
			SoftInfoService softInfoService = (SoftInfoService) BeanUtil.getBean(this.getServletContext(),
					"softInfoService");
			String factoryid = terminalId.substring(3, 6);
			SoftInfo softInfo = (SoftInfo) softInfoService.findSoft(version,factoryid);
			try {
				if (softInfo != null) {
					String taskId = softInfo.getSoftinfoid();
					String size = softInfo.getSoftsize();
					String link = softInfo.getLink();
					String md5 = softInfo.getMd5();
					String description = softInfo.getDescription();
					// newversion is get from softinfo
					String newversion = softInfo.getVersion();
					// parse newversion to float
					float nv = Float.parseFloat(newversion);
					// parse version to float
					float v = Float.parseFloat(version);

					if (nv > v) {
						Document document = DocumentHelper.createDocument();
						Element config = document.addElement("command");
						// 平台成功将最新软件的信息返回给终端
						result = "0";
						try {
							config.addElement("result").addText(result);
							config.addElement("taskid").addText(taskId);
							config.addElement("version").addText(newversion);
							config.addElement("size").addText(size);
							config.addElement("link").addText(link);
							config.addElement("md5").addText(md5);
							if (description != null && !"".equals(description)) {
								config.addElement("description").addText(description);
							}
						} catch (Exception e) {
							e.printStackTrace();
							log.error(e.getMessage(),e);
						}

						// 将软件的信息 返回给终端
						resxml = XMLUtil.formatXML(document);
						log.info("TerminalId: " + terminalId + " SoftUpdateServlet responseContent: " + resxml);
						SQLCache.putTermOperLog(terminalId, "响应软件升级请求，新版本号[" + newversion + "]");
					} else {
						// 平台找不到高于请求的版本的软件信息，响应失败
						errorinfo = "cann't find version higger than request version=" + version;
						resxml = ServletUtil.returnXMLMessage(result, errorinfo);
						log.info("TerminalId: " + terminalId + " SoftUpdateServlet responseContent: " + resxml);
						SQLCache.putTermOperLog(terminalId, "没有比当前版本[" + version + "]更高的版本，不予升级");
					}
				} else {
					errorinfo = "cann't find version in platform database";
					resxml = ServletUtil.returnXMLMessage(result, errorinfo);
					log.info("TerminalId: " + terminalId + " SoftUpdateServlet responseContent: " + resxml);
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}
		} else {
			errorinfo = "request xml version number is error,version=" + version;
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			log.info("TerminalId: " + terminalId + " SoftUpdateServlet responseContent: " + resxml);
			SQLCache.putTermOperLog(terminalId, "请求的版本号[" + version + "]错误，平台没有匹配的版本信息");
		}

		log.debug("Terminalid:" + terminalId + " exit SoftUpdateServlet");
		return resxml;
	}
}
