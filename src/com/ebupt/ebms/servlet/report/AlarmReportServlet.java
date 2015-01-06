package com.ebupt.ebms.servlet.report;

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
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.service.report.TermAlarmService;
import com.ebupt.ebms.service.task.SmsService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.ServletUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author lishuhua Create time 2011-3-8
 * @version 1.0
 */
@WebServlet("/alarmReport")
public class AlarmReportServlet extends AuthServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AlarmReportServlet.class);

	@Override
	public String doAuthGetService(HttpServletRequest request, HttpServletResponse response, String terminalId) {

		log.debug("Terminalid:" + terminalId + " enter into AlarmReportServlet");

		// 服务器处理结果,0 成功 ,1 失败
		String result = "1";
		String errorinfo = null;

		// 获取请求消息中的数据流
		String reqxml = ServletUtil.parseRequsetXML(request);
		log.info("Terminalid:" + terminalId + " reqxml:" + reqxml);
		String resxml = null;

		if (reqxml == null || "".equals(reqxml)) {
			log.error("Terminalid:" + terminalId + " AlarmReportServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}

		// 解析数据流中的xml信息,获取version
		String alarmcode = null;
		String time = null;
		try {
			Document document = DocumentHelper.parseText(reqxml.trim());
			Element root = document.getRootElement();
			if (root.elementText("alarmcode") != null && !"".equals(root.elementText("alarmcode"))) {
				alarmcode = root.elementText("alarmcode").trim();
			} else {
				log.error("Terminalid:" + terminalId + " AlarmReportServlet alarmcode is null");
				errorinfo = "alarmcode is null";
				resxml = ServletUtil.returnXMLMessage(result, errorinfo);
				return resxml;
			}
			if (root.elementText("time") != null && !"".equals(root.elementText("time"))) {
				time = root.elementText("time").trim();
			} else {
				log.error("Terminalid:" + terminalId + " AlarmReportServlet time is null");
				errorinfo = "time is null";
				resxml = ServletUtil.returnXMLMessage(result, errorinfo);
				return resxml;
			}
			if (root.elementText("alarmvalue") != null && !"".equals(root.elementText("alarmvalue"))) {
				errorinfo = root.elementText("alarmvalue").trim();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		SQLCache.putTermOperLog(terminalId, "上报告警码[" + alarmcode + "]告警信息[" + errorinfo + "]");
		try {
			TermAlarmService termAlarmService = (TermAlarmService) BeanUtil.getBean(this.getServletContext(),
					"termAlarmService");
			result = termAlarmService.save(terminalId, alarmcode, time, errorinfo);

			SmsService smsService = (SmsService) BeanUtil.getBean(this.getServletContext(), "smsService");
			
			//暂时先不给操作员发告警，只给我们自己发，有需要再开启
//			Set<String> phones = TermInfoCache.getPhonesByTerminalid(this.getServletContext(), terminalId);
//			if (phones != null && phones.size() > 0) {
//				phones.addAll(NumberUtil.splitNumber(MainConfig.getInstance().getPhoneNumber()));
//				String content = "终端" + terminalId + "于" + TimeUtil.getFormatTime() + "上报告警码[" + alarmcode + "]告警信息["
//						+ errorinfo + "]，请及时处理！";
//				for (String phone : phones) {
//					smsService.sendSMS(phone, content);
//				}
//			}
			Set<String> phones = NumberUtil.splitNumber(MainConfig.getInstance().getPhoneNumber());
			if (phones != null && phones.size() > 0) {
				String content = "终端" + terminalId + "于" + TimeUtil.getFormatHour() + "上报告警码[" + alarmcode + "]告警信息["
						+ errorinfo + "]，请及时处理！";
//				String content = "终端" + terminalId + "于" + TimeUtil.getFormatHour() + "上报告警码[" + alarmcode + "]，请及时处理！";
				for (String phone : phones) {
					smsService.sendSMS(phone, content);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		resxml = ServletUtil.returnXMLMessage(result, errorinfo);
		log.info("TerminalId: " + terminalId + " AlarmReportServlet responseContent: " + resxml);
		// 返回处理的结果
		log.debug("Terminalid:" + terminalId + " exit AlarmReportServlet, the result is : " + result);
		return resxml;
	}

}
