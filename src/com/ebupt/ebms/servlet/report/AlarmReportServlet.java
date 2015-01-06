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

		// ������������,0 �ɹ� ,1 ʧ��
		String result = "1";
		String errorinfo = null;

		// ��ȡ������Ϣ�е�������
		String reqxml = ServletUtil.parseRequsetXML(request);
		log.info("Terminalid:" + terminalId + " reqxml:" + reqxml);
		String resxml = null;

		if (reqxml == null || "".equals(reqxml)) {
			log.error("Terminalid:" + terminalId + " AlarmReportServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}

		// �����������е�xml��Ϣ,��ȡversion
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
		SQLCache.putTermOperLog(terminalId, "�ϱ��澯��[" + alarmcode + "]�澯��Ϣ[" + errorinfo + "]");
		try {
			TermAlarmService termAlarmService = (TermAlarmService) BeanUtil.getBean(this.getServletContext(),
					"termAlarmService");
			result = termAlarmService.save(terminalId, alarmcode, time, errorinfo);

			SmsService smsService = (SmsService) BeanUtil.getBean(this.getServletContext(), "smsService");
			
			//��ʱ�Ȳ�������Ա���澯��ֻ�������Լ���������Ҫ�ٿ���
//			Set<String> phones = TermInfoCache.getPhonesByTerminalid(this.getServletContext(), terminalId);
//			if (phones != null && phones.size() > 0) {
//				phones.addAll(NumberUtil.splitNumber(MainConfig.getInstance().getPhoneNumber()));
//				String content = "�ն�" + terminalId + "��" + TimeUtil.getFormatTime() + "�ϱ��澯��[" + alarmcode + "]�澯��Ϣ["
//						+ errorinfo + "]���뼰ʱ����";
//				for (String phone : phones) {
//					smsService.sendSMS(phone, content);
//				}
//			}
			Set<String> phones = NumberUtil.splitNumber(MainConfig.getInstance().getPhoneNumber());
			if (phones != null && phones.size() > 0) {
				String content = "�ն�" + terminalId + "��" + TimeUtil.getFormatHour() + "�ϱ��澯��[" + alarmcode + "]�澯��Ϣ["
						+ errorinfo + "]���뼰ʱ����";
//				String content = "�ն�" + terminalId + "��" + TimeUtil.getFormatHour() + "�ϱ��澯��[" + alarmcode + "]���뼰ʱ����";
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
		// ���ش���Ľ��
		log.debug("Terminalid:" + terminalId + " exit AlarmReportServlet, the result is : " + result);
		return resxml;
	}

}
