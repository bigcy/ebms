package com.ebupt.ebms.servlet.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebupt.ebms.cache.TaskInfoCache;
import com.ebupt.ebms.cache.TermInfoCache;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.service.task.TaskSelectService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.TimeUtil;
import com.ebupt.ebms.utils.XMLUtil;

/**
 * @author QiChen Create on 2011-3-8
 * @version 1.0
 */
@WebServlet("/getTask")
public class TaskSelectServlet extends AuthServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(TaskSelectServlet.class);

	@Override
	public String doAuthGetService(HttpServletRequest req, HttpServletResponse resp, String terminalId) {
		log.debug("Terminalid:" + terminalId + " enter into TaskSelectServlet");
		TaskSelectService taskSelectService = (TaskSelectService) BeanUtil.getBean(getServletContext(), "taskSelectService");

		// �ӻ����ж�ȡ��terminalId������
		String taskFlag = TaskInfoCache.getTaskFlag(terminalId);

		/************************* ���Ӷ��ն�����ʱ�������ù��ܣ�2014-02-25 begin ******************************/
		String startstr = MainConfig.getInstance().getStatisticsstarttime();
		String endstr = MainConfig.getInstance().getStatisticsendtime();
		if ("".equals(startstr) || startstr == null){
			startstr = "0600";
		}
		if ("".equals(endstr) || endstr == null){
			endstr = "2000";
		}

		int starttime = Integer.parseInt(startstr);
		int endtime = Integer.parseInt(endstr);

		if (starttime < 600){
			starttime = 600;
		}

		if (endtime > 2000){
			endtime = 2000;
		}

		// ͳ�ƿ�ʼʱ�䲻���ڽ���ʱ��ſ��Խ���ͳ�ƣ���Ⱥʹ��ڶ�����ͳ��
		if (starttime < endtime){
			int nowhm = Integer.parseInt(TimeUtil.getHHmm());

			// 6��~20��֮�䶼����£����෶Χ��յ����¼
			if (starttime <= nowhm && nowhm <= endtime){
				// ��ȡ�ϴ��ն˵���ѯʱ��
				String getlasttime = TermInfoCache.getTermSelectTime(terminalId);

				if (!"".equals(getlasttime) && getlasttime != null){
					String nowdate = TimeUtil.getDate();
					log.info("getlasttime is:" + getlasttime);
					String termdate = getlasttime.substring(0, 10).replace("-", "");
					log.info("termdate is:" + termdate);

					if (nowdate.equals(termdate)){
						String getnowtime = TimeUtil.getFormatTime();

						try{
							String getTermOnlineTime = TermInfoCache.getTermOnlineTime(terminalId);
							log.info("last onlinetime is:" + getTermOnlineTime);

							DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

							Date lasttime = df.parse(getlasttime);
							Date nowtime = df.parse(getnowtime);
							long timecontinue = (nowtime.getTime() - lasttime.getTime()) / 1000; // ����ת��Ϊ��

							if (!"".equals(getTermOnlineTime) && getTermOnlineTime != null){
								long newinttime = Long.parseLong(getTermOnlineTime) + timecontinue;
								String newOnlineTime = String.valueOf(newinttime);
								log.info("now  onlinetime is:" + newOnlineTime);

								// ������ʱ���ܴ�����ѯ���ʱ�䣬Ŀǰ�Ȱ�30s����
								if (timecontinue <= 30){
									TermInfoCache.setTermOnlineTime(terminalId, newOnlineTime);
								} else{
									log.info("timecontinue is" + timecontinue + " bigger than select time 30s, data will be not record!");
								}
							} else{
								TermInfoCache.setTermOnlineTime(terminalId, "0");
							}
						} catch (Exception e){
							e.printStackTrace();
						}

					}
				}
			} else{
				TermInfoCache.setTermOnlineTime(terminalId, "0");
			}
		}
		/************************* ���Ӷ��ն�����ʱ�������ù��ܣ�2014-02-25 end ********************************/

		// ��¼��ѯʱ�䵽������
		String selecttime = TimeUtil.getFormatTime();
		TermInfoCache.setTermSelectTime(terminalId, selecttime);

		log.info("Terminalid:" + terminalId + " TaskFlag:" + taskFlag);
		// resp.setHeader("taskFlag", taskFlag); ����Ҫ����
		String resxml = null;
		if (!taskFlag.equals("0")){
			// ����taskFlag���������е�����ö��
			List<TaskFlag> taskFlags = taskSelectService.parseTaskFlag(taskFlag);
			// ƴװXML
			resxml = taskSelectService.writeXML(getServletContext(), taskFlags, terminalId);
			log.info("Terminalid:" + terminalId + " TaskFlags:" + taskFlags);

		} else{
			Document document = DocumentHelper.createDocument();
			Element command = document.addElement("command");
			Element tasklist = command.addElement("tasklist");
			Element taskcount = tasklist.addElement("taskcount");
			taskcount.setText("0"); // ��ʼ��Ϊ��

			resxml = XMLUtil.formatXML(document);
			// SQLCache.putTermOperLog(terminalId, "������ѯ��������");
		}
		log.info("Terminalid:" + terminalId + " XML:" + resxml);
		log.debug("Terminalid:" + terminalId + " exit TaskSelectServlet");
		return resxml;
	}

	public enum TaskFlag {
		// ���ֱ�ʾ��nλΪ1�����д�1��ʼ���У�
		// 1-16 �Ͱ�λ
		/** ������������ */
		PlayTask(1),
		/** ����������� */
		SoftUpdateTask(2),
		/** ��ʱ��Ϣ���� */
		InstantMessagingTask(4),
		/** ���������� */
		ConfigTask(5),
		/** ����Ԥ������ */
		WeatherTask(6),
		/** ����������� */
		PlayTaskControl(7),
		/** �ն�������Ϣ�ϱ� */
		TerminalInfoLoad(8),
		/** �ն˹���״̬�ϱ� */
		TermianlStatusLoad(9),
		/** �ڲ������ϱ����� */
		PlayingContentUpLoad(10),
		/** ʵʱ�������� */
		InstantDataTask(11),
		/** �ϱ���־���� */
		LogFileUpLoad(12),
		/** ʵʱ�������13 */
		InstantMonitor(13),

		// 17-32 �߰�λ , ����������
		/** ���� */
		Restart(17),
		/** ���� */
		Sleep(18),
		/** ���� */
		Awake(19),
		/** ������գ���ʽ���� */
		DiskFormat(20);

		private int flag;

		private TaskFlag(int flag) {
			this.flag = flag;
		}// ˽�й��졣

		public int getFlag() {
			return flag;
		}
	}
}