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

		// ������������,0 �ɹ� ,1 ʧ�ܣ��������յķ��ؽ��������res1 ��res2 ��ͬ�ж�

		// �����ݿ�Ĳ����Ƿ�ɹ�
		String res1 = "1";
		// �Ի���Ĳ����Ƿ�ɹ�
		String res2 = "1";
		String errorinfo = null;

		// ��ȡ������Ϣ�е�������
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

		/** 1.�������ݿ⣨�����Ҫ����2.��״̬λ��1-->0 */
		switch (type) {
		case ConfigTask:
			res1 = taskStatusService.updateConfigTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.ConfigTask);
			}
			SQLCache.putTermOperLog(terminalId, "�յ���������[" + taskid + "]");
			break;
		case Controls:
			res1 = "0";
			if ("0".equals(res1)) {
				/** ���⴦��������ǰ��λ�ľ������ */
				String flag = taskid.substring(0, 2);
				for (TaskFlag taskFlag : TaskFlag.values()) {
					if (taskFlag.getFlag() == Integer.valueOf(flag)) {
						res2 = this.updateTaskFlag(terminalId, taskFlag);
					}
				}
				if (TaskFlag.Restart.getFlag() == Integer.valueOf(flag)) {
					SQLCache.putTermOperLog(terminalId, "�յ���������");
				} else if (TaskFlag.Sleep.getFlag() == Integer.valueOf(flag)) {
					SQLCache.putTermOperLog(terminalId, "�յ���������");
				} else if (TaskFlag.Awake.getFlag() == Integer.valueOf(flag)) {
					SQLCache.putTermOperLog(terminalId, "�յ���������");
				} else if (TaskFlag.DiskFormat.getFlag() == Integer.valueOf(flag)) {
					SQLCache.putTermOperLog(terminalId, "�յ������������");
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
			SQLCache.putTermOperLog(terminalId, "�յ�����ʵʱ�������");
			break;
		case InstantMessagingTask:
			res1 = "0";
			res2 = this.updateTaskFlag(terminalId, TaskFlag.InstantMessagingTask);
			SQLCache.putTermOperLog(terminalId, "�յ���ʱ��Ϣ����");
			break;
		case LogFileUpLoad:
			res1 = taskStatusService.updateLogFileUpLoad(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.LogFileUpLoad);
			}
			SQLCache.putTermOperLog(terminalId, "�յ���־�ϴ�����");
			break;
		case PlayingContentUpLoad:
			res1 = taskStatusService.updateTermPlaying(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.PlayingContentUpLoad);
			}
			SQLCache.putTermOperLog(terminalId, "�յ��ڲ������ϱ�����");
			break;
		case PlayTask:
			res1 = taskStatusService.updatePlayTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.PlayTask);
			}
			SQLCache.putTermOperLog(terminalId, "�յ���������["+taskid+"]");
			break;
		case PlayTaskControl:
			res1 = taskStatusService.updatePlayTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.PlayTaskControl);
			}
			SQLCache.putTermOperLog(terminalId, "�յ����Ĳ�������״̬["+taskid+"]");
			break;
		case SoftUpdateTask:
			res1 = taskStatusService.updateSoftUpdateTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.SoftUpdateTask);
			}
			SQLCache.putTermOperLog(terminalId, "�յ������������["+taskid+"]");
			break;
		case TermianlStatusLoad:
			res1 = taskStatusService.updateTermWorking(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.TermianlStatusLoad);
			}
			SQLCache.putTermOperLog(terminalId, "�յ�����״̬�ϱ�����");
			break;
		case TerminalInfoLoad:
			res1 = "0";
			res2 = this.updateTaskFlag(terminalId, TaskFlag.TerminalInfoLoad);
			SQLCache.putTermOperLog(terminalId, "�յ�������Ϣ�ϱ�����");
			break;
		case WeatherTask:
			res1 = taskStatusService.updateWeatherTask(taskid, status, terminalId);
			if ("0".equals(res1)) {
				res2 = this.updateTaskFlag(terminalId, TaskFlag.WeatherTask);
			}
			SQLCache.putTermOperLog(terminalId, "�յ�����Ԥ������["+taskid+"]");
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
	 * ������������TaskType
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
	 * ��ָ��λ�õ�1-->0
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
			// �����־λ�Ѿ�Ϊ0�����ء�
			return "0";
		}
		BigInteger _taskFlag = new BigInteger(taskFlag);
		_taskFlag = _taskFlag.subtract(new BigInteger(sb.toString()));

		log.debug("�޸�״̬λ��" + taskFlag + "--->" + _taskFlag);

		TaskInfoCache.setTaskFlag(terminalId, String.format("%032d", _taskFlag));
		return "0";
	}

	public enum TaskType {

		/** ������������ 01 */
		PlayTask("01"),
		/** ����������� 02 */
		SoftUpdateTask("02"),
		/** ������ 03 */
		Controls("03"),
		/** ��ʱ��Ϣ���� 04 */
		InstantMessagingTask("04"),
		/** ���������� 05 */
		ConfigTask("05"),
		/** ����Ԥ������ 06 */
		WeatherTask("06"),
		/** ����������� 07 */
		PlayTaskControl("07"),
		/** �ն���Ϣ�ϱ� 08 */
		TerminalInfoLoad("08"),
		/** �ն˹���״̬�ϱ� 09 */
		TermianlStatusLoad("09"),
		/** �ڲ������ϱ����� 10 */
		PlayingContentUpLoad("10"),
		/** ʵʱ�������� 11 */
		InstantDataTask("11"),
		/** �ϱ���־���� 12 */
		LogFileUpLoad("12"),
		/** ʵʱ�������13 */
		InstantMonitor("13");

		private String type;

		private TaskType(String type) {
			this.type = type;
		}// ˽�й��졣

		public String getType() {
			return type;
		}
	}

}
