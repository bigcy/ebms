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
import com.ebupt.ebms.entity.TermConfigReport;
import com.ebupt.ebms.service.report.ConfigReportService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ServletUtil;
import com.ebupt.ebms.utils.TimeUtil;


/**   
 * @author lishuhua
 * Create time 2011-3-7
 * @version 1.0  
 */
@WebServlet("/configReport")
public  class ConfigReportServlet extends AuthServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConfigReportServlet.class);


	@Override
	public String doAuthGetService(HttpServletRequest request, HttpServletResponse response,
			String terminalId){
		
		log.debug("Terminalid:" + terminalId +" enter into ConfigReportServlet");
		
		//服务器处理结果,0 成功 ,1 失败
		String result = "1";
		String errorinfo = null;
		
		//获取请求消息中的数据流
		String reqxml = ServletUtil.parseRequsetXML(request);
		log.info("Terminalid:" + terminalId +" reqxml:" + reqxml);
		String resxml = null;

		if (reqxml == null || "".equals(reqxml)){
			log.error("Terminalid:" + terminalId +" ConfigReportServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}
		
		//解析数据流中的xml信息,获取version
		String ip = null;
		String mac = null;
		String gateway = null;
		String dns = null;
		String appversion = null;
		String startuptime = null;
		String shutdowntime = null;
		String diskspacealarm = null;
		String serverip = null;
		String serverport = null;
		String redisip = null;
		String redisport = null;
		String maxmonitortime = null;
		String volume = null;
		String downloadrate = null;
		String downloadtime = null;
		String selectinterval = null;
		String logserver = null;
		String uploadlogtime = null;
		String keeplogtime = null;
		String loglevel = null;
		String placementmodel = null;
		String exdevicetype = null;
		String resolution = null;
		try{
			Document document = DocumentHelper.parseText(reqxml.trim());
			Element root  = document.getRootElement();
			if(root.elementText("ip") != null && !"".equals(root.elementText("ip"))){				
				ip = root.elementText("ip").trim();
			}
			if(root.elementText("mac") != null && !"".equals(root.elementText("mac"))){				
				mac = root.elementText("mac").trim();
			}
			if(root.elementText("gateway") != null && !"".equals(root.elementText("gateway"))){				
				gateway = root.elementText("gateway").trim();
			}
			if(root.elementText("dns") != null && !"".equals(root.elementText("dns"))){				
				dns = root.elementText("dns").trim();
			}
			if(root.elementText("appversion") != null && !"".equals(root.elementText("appversion"))){				
				appversion = root.elementText("appversion").trim();
			}
			if(root.elementText("startuptime") != null && !"".equals(root.elementText("startuptime"))){				
				startuptime = root.elementText("startuptime").trim();
			}
			if(root.elementText("shutdowntime") != null && !"".equals(root.elementText("shutdowntime"))){				
				shutdowntime = root.elementText("shutdowntime").trim();
			}
			if(root.elementText("diskspacealarm") != null && !"".equals(root.elementText("diskspacealarm"))){				
				diskspacealarm = root.elementText("diskspacealarm").trim();
			}
			if(root.elementText("serverip") != null && !"".equals(root.elementText("serverip"))){				
				serverip = root.elementText("serverip").trim();
			}
			if(root.elementText("serverport") != null && !"".equals(root.elementText("serverport"))){				
				serverport = root.elementText("serverport").trim();
			}
			if(root.elementText("redisip") != null && !"".equals(root.elementText("redisip"))){				
				redisip = root.elementText("redisip").trim();
			}
			if(root.elementText("redisport") != null && !"".equals(root.elementText("redisport"))){				
				redisport = root.elementText("redisport").trim();
			}
			if(root.elementText("maxmonitortime") != null && !"".equals(root.elementText("maxmonitortime"))){				
				maxmonitortime = root.elementText("maxmonitortime").trim();
			}
			if(root.elementText("volume") != null && !"".equals(root.elementText("volume"))){				
				volume = root.elementText("volume").trim();
			}
			if(root.elementText("downloadrate") != null && !"".equals(root.elementText("downloadrate"))){				
				downloadrate = root.elementText("downloadrate").trim();
			}
			if(root.elementText("downloadtime") != null && !"".equals(root.elementText("downloadtime"))){				
				downloadtime = root.elementText("downloadtime").trim();
			}
			if(root.elementText("selectinterval") != null && !"".equals(root.elementText("selectinterval"))){				
				selectinterval = root.elementText("selectinterval").trim();
			}
			if(root.elementText("logserver") != null && !"".equals(root.elementText("logserver"))){				
				logserver = root.elementText("logserver").trim();
			}
			if(root.elementText("uploadlogtime") != null && !"".equals(root.elementText("uploadlogtime"))){				
				uploadlogtime = root.elementText("uploadlogtime").trim();
			}
			if(root.elementText("keeplogtime") != null && !"".equals(root.elementText("keeplogtime"))){				
				keeplogtime = root.elementText("keeplogtime").trim();
			}
			if(root.elementText("loglevel") != null && !"".equals(root.elementText("loglevel"))){				
				loglevel = root.elementText("loglevel").trim();
			}
			if(root.elementText("placementmodel") != null && !"".equals(root.elementText("placementmodel"))){				
				placementmodel = root.elementText("placementmodel").trim();
			}
			if(root.elementText("exdevicetype") != null && !"".equals(root.elementText("exdevicetype"))){				
				exdevicetype = root.elementText("exdevicetype").trim();
			}
			if(root.elementText("outputresolution") != null && !"".equals(root.elementText("outputresolution"))){				
				resolution = root.elementText("outputresolution").trim();
			}
		}catch(DocumentException e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		
		SQLCache.putTermOperLog(terminalId, "上报当前终端配置");
		ConfigReportService configReportService = (ConfigReportService)BeanUtil.getBean(this.getServletContext(), "configReportService");
		
		try {
			TermConfigReport tp = new TermConfigReport();
			
			tp.setTerminalid(terminalId);
			tp.setReporttime(TimeUtil.getTime());
			tp.setIp(ip);
			tp.setMac(mac);
			tp.setGateway(gateway);
			tp.setDns(dns);
			tp.setAppversion(appversion);
			tp.setStartuptime(startuptime);
			tp.setShutdowntime(shutdowntime);
			tp.setDiskspacealarm(diskspacealarm);
			tp.setServerip(serverip);
			tp.setServerport(serverport);
			tp.setRedisip(redisip);
			tp.setRedisport(redisport);
			tp.setMaxmonitortime(maxmonitortime);
			tp.setVolume(volume);
			tp.setDownloadrate(downloadrate);
			tp.setDownloadtime(downloadtime);
			tp.setSelectinterval(selectinterval);
			tp.setLogserver(logserver);
			tp.setUploadlogtime(uploadlogtime);
			tp.setKeeplogtime(keeplogtime);
			tp.setLoglevel(loglevel);
			tp.setPlacementmodel(placementmodel);
			tp.setExdevicetype(exdevicetype);
			tp.setResolution(resolution);
			
			result = configReportService.updateoradd(tp);	
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		
		resxml = ServletUtil.returnXMLMessage(result,errorinfo);
		log.info("TerminalId: " + terminalId + " ConfigReportServlet responseContent: " + resxml);
		
		//返回处理的结果
		log.debug("Terminalid:" + terminalId + " exit ConfigReportServlet result is : " + result);
		return resxml;
	}

}
