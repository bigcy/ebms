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

		// 从缓存中读取（terminalId）任务
		String taskFlag = TaskInfoCache.getTaskFlag(terminalId);

		/************************* 增加对终端在线时长的设置功能，2014-02-25 begin ******************************/
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

		// 统计开始时间不大于结束时间才可以进行统计，相等和大于都不做统计
		if (starttime < endtime){
			int nowhm = Integer.parseInt(TimeUtil.getHHmm());

			// 6点~20点之间都会更新，其余范围清空当天记录
			if (starttime <= nowhm && nowhm <= endtime){
				// 获取上次终端的轮询时间
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
							long timecontinue = (nowtime.getTime() - lasttime.getTime()) / 1000; // 毫秒转化为妙

							if (!"".equals(getTermOnlineTime) && getTermOnlineTime != null){
								long newinttime = Long.parseLong(getTermOnlineTime) + timecontinue;
								String newOnlineTime = String.valueOf(newinttime);
								log.info("now  onlinetime is:" + newOnlineTime);

								// 交互的时间差不能大于轮询间隔时间，目前先按30s计算
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
		/************************* 增加对终端在线时长的设置功能，2014-02-25 end ********************************/

		// 记录轮询时间到缓存中
		String selecttime = TimeUtil.getFormatTime();
		TermInfoCache.setTermSelectTime(terminalId, selecttime);

		log.info("Terminalid:" + terminalId + " TaskFlag:" + taskFlag);
		// resp.setHeader("taskFlag", taskFlag); 不需要返回
		String resxml = null;
		if (!taskFlag.equals("0")){
			// 解析taskFlag，返回所有的任务枚举
			List<TaskFlag> taskFlags = taskSelectService.parseTaskFlag(taskFlag);
			// 拼装XML
			resxml = taskSelectService.writeXML(getServletContext(), taskFlags, terminalId);
			log.info("Terminalid:" + terminalId + " TaskFlags:" + taskFlags);

		} else{
			Document document = DocumentHelper.createDocument();
			Element command = document.addElement("command");
			Element tasklist = command.addElement("tasklist");
			Element taskcount = tasklist.addElement("taskcount");
			taskcount.setText("0"); // 初始化为零

			resxml = XMLUtil.formatXML(document);
			// SQLCache.putTermOperLog(terminalId, "任务轮询，无任务。");
		}
		log.info("Terminalid:" + terminalId + " XML:" + resxml);
		log.debug("Terminalid:" + terminalId + " exit TaskSelectServlet");
		return resxml;
	}

	public enum TaskFlag {
		// 数字表示第n位为1（序列从1开始排列）
		// 1-16 低八位
		/** 播放内容任务 */
		PlayTask(1),
		/** 软件升级任务 */
		SoftUpdateTask(2),
		/** 即时消息任务 */
		InstantMessagingTask(4),
		/** 配置类任务 */
		ConfigTask(5),
		/** 天气预报任务 */
		WeatherTask(6),
		/** 播放任务控制 */
		PlayTaskControl(7),
		/** 终端配置信息上报 */
		TerminalInfoLoad(8),
		/** 终端工作状态上报 */
		TermianlStatusLoad(9),
		/** 在播内容上报任务 */
		PlayingContentUpLoad(10),
		/** 实时数据任务 */
		InstantDataTask(11),
		/** 上报日志任务 */
		LogFileUpLoad(12),
		/** 实时监控任务13 */
		InstantMonitor(13),

		// 17-32 高八位 , 控制类任务
		/** 重启 */
		Restart(17),
		/** 休眠 */
		Sleep(18),
		/** 唤醒 */
		Awake(19),
		/** 磁盘清空（格式化） */
		DiskFormat(20);

		private int flag;

		private TaskFlag(int flag) {
			this.flag = flag;
		}// 私有构造。

		public int getFlag() {
			return flag;
		}
	}
}