package com.ebupt.ebms.servlet.task;

import java.math.BigInteger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.cache.TaskInfoCache;
import com.ebupt.ebms.service.task.TaskStatusService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.servlet.task.TaskSelectServlet.TaskFlag;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ServletUtil;

/**
 * @author QiChen Create on 2011-3-9
 * @version 1.0
 */
@WebServlet("/taskReport")
public class TaskStatusServlet extends AuthServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(TaskStatusServlet.class);

	@Override
	public String doAuthGetService(HttpServletRequest req, HttpServletResponse resp, String terminalId) {

		log.debug("Terminalid:" + terminalId + " enter into TaskStatusServlet");

		// 服务器处理结果,0 成功 ,1 失败，这是最终的返回结果，根据res1 和res2 共同判断

		// 对数据库的操作是否成功
		String res1 = "1";
		// 对缓存的操作是否成功
		String res2 = "1";
		String errorinfo = null;

		// 获取请求消息中的数据流
		String reqxml = ServletUtil.parseRequsetXML(req);
		String resxml = null;
		log.info("Terminalid:" + terminalId + " reqxml:" + reqxml);
		if (reqxml == null || "".equals(reqxml)) {
			log.error("Terminalid:" + terminalId + " TaskStatusServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage("1", errorinfo);
			return resxml;
		}

		String taskid = null;
		String tasktype = null;
		String status = null;

		try {
			Document document = DocumentHelper.parseText(reqxml);
			Element root = document.getRootElement();
			if (root.elementText("taskid") != null && !"".equals(root.elementText("taskid"))) {
				taskid = root.elementText("taskid").trim();
			}
			if (root.elementText("tasktype") != null && !"".equals(root.elementText("tasktype"))) {
				tasktype = root.elementText("tasktype").trim();
			}
			if (root.elementText("status") != null && !"".equals(root.elementText("status"))) {
				status = root.elementText("status").trim();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		if (taskid == null || tasktype == null || status == null) {
			errorinfo = "Terminalid:" + terminalId + " TaskStatusServlet reqxml is error";
			log.error(errorinfo);
			resxml = ServletUtil.returnXMLMessage("1", errorinfo);
			return resxml;
		}

		log.info("Terminalid:" + terminalId + " taskid:" + taskid + " tasktype" + tasktype + " status" + status);

		TaskStatusService taskStatusService = (TaskStatusService) BeanUtil.getBean(getServletContext(),
				"taskStatusService");

		TaskType type = parseTaskType(tasktype);

		if (type == null) {
			errorinfo = "Terminalid:" + terminalId + " TaskStatusServlet tasktype:" + tasktype + " cann't find";
			log.error(errorinfo);
			resxml = ServletUtil.returnXMLMessage("1", errorinfo);
			return resxml;
		}

		/** 1.更新数据库（如果需要），2.把状态位的1-->0 */
		switch (type) {
		case ConfigTask:
			res1 = taskStatusService.updateConfigTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.ConfigTask);
			}
			SQLCache.putTermOperLog(terminalId, "收到配置任务[" + taskid + "]");
			break;
		case Controls:
			res1 = "0";
			if ("0".equals(res1)) {
				/** 特殊处理，解析出前两位的具体操作 */
				String flag = taskid.substring(0, 2);
				for (TaskFlag taskFlag : TaskFlag.values()) {
					if (taskFlag.getFlag() == Integer.valueOf(flag)) {
						res2 = this.updateTaskFlag(terminalId, taskFlag);
					}
				}
				if (TaskFlag.Restart.getFlag() == Integer.valueOf(flag)) {
					SQLCache.putTermOperLog(terminalId, "收到重启命令");
				} else if (TaskFlag.Sleep.getFlag() == Integer.valueOf(flag)) {
					SQLCache.putTermOperLog(terminalId, "收到休眠命令");
				} else if (TaskFlag.Awake.getFlag() == Integer.valueOf(flag)) {
					SQLCache.putTermOperLog(terminalId, "收到唤醒命令");
				} else if (TaskFlag.DiskFormat.getFlag() == Integer.valueOf(flag)) {
					SQLCache.putTermOperLog(terminalId, "收到磁盘清空命令");
				}
			}
			break;
		case InstantDataTask:
			res1 = "0";
			res2 = this.updateTaskFlag(terminalId, TaskFlag.InstantDataTask);
			break;
		case InstantMonitor:
			res1 = "0";
			res2 = this.updateTaskFlag(terminalId, TaskFlag.InstantMonitor);
			SQLCache.putTermOperLog(terminalId, "收到开启实时监控命令");
			break;
		case InstantMessagingTask:
			res1 = "0";
			res2 = this.updateTaskFlag(terminalId, TaskFlag.InstantMessagingTask);
			SQLCache.putTermOperLog(terminalId, "收到即时消息任务");
			break;
		case LogFileUpLoad:
			res1 = taskStatusService.updateLogFileUpLoad(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.LogFileUpLoad);
			}
			SQLCache.putTermOperLog(terminalId, "收到日志上传任务");
			break;
		case PlayingContentUpLoad:
			res1 = taskStatusService.updateTermPlaying(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.PlayingContentUpLoad);
			}
			SQLCache.putTermOperLog(terminalId, "收到在播内容上报任务");
			break;
		case PlayTask:
			res1 = taskStatusService.updatePlayTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.PlayTask);
			}
			SQLCache.putTermOperLog(terminalId, "收到播放任务["+taskid+"]");
			break;
		case PlayTaskControl:
			res1 = taskStatusService.updatePlayTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.PlayTaskControl);
			}
			SQLCache.putTermOperLog(terminalId, "收到更改播放任务状态["+taskid+"]");
			break;
		case SoftUpdateTask:
			res1 = taskStatusService.updateSoftUpdateTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.SoftUpdateTask);
			}
			SQLCache.putTermOperLog(terminalId, "收到软件升级任务["+taskid+"]");
			break;
		case TermianlStatusLoad:
			res1 = taskStatusService.updateTermWorking(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.TermianlStatusLoad);
			}
			SQLCache.putTermOperLog(terminalId, "收到工作状态上报任务");
			break;
		case TerminalInfoLoad:
			res1 = "0";
			res2 = this.updateTaskFlag(terminalId, TaskFlag.TerminalInfoLoad);
			SQLCache.putTermOperLog(terminalId, "收到配置信息上报任务");
			break;
		case WeatherTask:
			res1 = taskStatusService.updateWeatherTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.WeatherTask);
			}
			SQLCache.putTermOperLog(terminalId, "收到天气预报任务["+taskid+"]");
			break;
		default:
			break;
		}

		if ("0".equals(res1) && "0".equals(res2)) {
			log.debug("Terminalid:" + terminalId + " taskReport success");
			resxml = ServletUtil.returnXMLMessage("0", errorinfo);
		} else {
			log.debug("Terminalid:" + terminalId + " taskReport fail");
			resxml = ServletUtil.returnXMLMessage("1", errorinfo);
		}

		log.debug("Terminalid:" + terminalId + " exit TaskStatusServlet");
		return resxml;
	}

	/**
	 * 返回这次请求的TaskType
	 * 
	 * @param tasktype
	 * @return
	 */
	private TaskType parseTaskType(String tasktype) {
		for (TaskType taskType : TaskType.values()) {
			if (taskType.getType().equals(tasktype)) {
				return taskType;
			}
		}
		return null;
	}

	/**
	 * 把指定位置的1-->0
	 * 
	 * @param terminalId
	 * @param playtask
	 */
	private String updateTaskFlag(String terminalId, TaskFlag playtask) {
		String taskFlag = TaskInfoCache.getTaskFlag(terminalId);

		int position = playtask.getFlag();
		StringBuilder sb = new StringBuilder("1");
		for (int i = 0; i < position - 1; i++) {
			sb.append("0");
		}

		if (taskFlag.charAt(taskFlag.length() - playtask.getFlag()) == '0') {
			// 如果标志位已经为0，返回。
			return "0";
		}
		BigInteger _taskFlag = new BigInteger(taskFlag);
		_taskFlag = _taskFlag.subtract(new BigInteger(sb.toString()));

		log.debug("修改状态位：" + taskFlag + "--->" + _taskFlag);

		TaskInfoCache.setTaskFlag(terminalId, String.format("%032d", _taskFlag));
		return "0";
	}

	public enum TaskType {

		/** 播放内容任务 01 */
		PlayTask("01"),
		/** 软件升级任务 02 */
		SoftUpdateTask("02"),
		/** 控制类 03 */
		Controls("03"),
		/** 即时消息任务 04 */
		InstantMessagingTask("04"),
		/** 配置类任务 05 */
		ConfigTask("05"),
		/** 天气预报任务 06 */
		WeatherTask("06"),
		/** 播放任务控制 07 */
		PlayTaskControl("07"),
		/** 终端信息上报 08 */
		TerminalInfoLoad("08"),
		/** 终端工作状态上报 09 */
		TermianlStatusLoad("09"),
		/** 在播内容上报任务 10 */
		PlayingContentUpLoad("10"),
		/** 实时数据任务 11 */
		InstantDataTask("11"),
		/** 上报日志任务 12 */
		LogFileUpLoad("12"),
		/** 实时监控任务13 */
		InstantMonitor("13");

		private String type;

		private TaskType(String type) {
			this.type = type;
		}// 私有构造。

		public String getType() {
			return type;
		}
	}

}
