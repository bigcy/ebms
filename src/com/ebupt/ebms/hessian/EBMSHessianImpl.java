package com.ebupt.ebms.hessian;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import com.caucho.hessian.server.HessianServlet;
import com.ebupt.ebms.cache.TaskInfoCache;
import com.ebupt.ebms.cache.TermInfoCache;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.entity.PlayListGroup;
import com.ebupt.ebms.service.task.ContinueGetSnapshotService;
import com.ebupt.ebms.service.task.MailService;
import com.ebupt.ebms.service.task.SoftInfoService;
import com.ebupt.ebms.service.task.TaskCreateService;
import com.ebupt.ebms.service.task.TaskSelectService;
import com.ebupt.ebms.servlet.task.TaskSelectServlet.TaskFlag;
import com.ebupt.ebms.servlet.task.TaskStatusServlet.TaskType;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ConvertVideoFormatThread;
import com.ebupt.ebms.utils.FFMpegUtil;
import com.ebupt.ebms.utils.ImageMagicUtil;

/**
 * @author QiChen Create on 2011-3-16
 * @version 1.0
 */
@WebServlet(urlPatterns = { "/hessian" })
public class EBMSHessianImpl extends HessianServlet implements EBMSHessian {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(EBMSHessianImpl.class);

	@Override
	public String setTasksByGroupids(String groupids, String taskType, String taskIds, String operatorid) {

		log.info("Enter into EBMSHessianImpl setTasksByGroupids method...");
		log.info("groupids:" + groupids + " taskType:" + taskType + " taskIds:" + taskIds + " operatorid:" + operatorid);

		if (groupids == null || "".equals(groupids) || taskType == null || "".equals(taskType) || taskIds == null
				|| "".equals(taskIds)) {
			return "false";
		} else {

			// 1. 取得所有群组ID下，不重复的终端。
			TaskCreateService taskCreateService = (TaskCreateService) BeanUtil.getBean(this.getServletContext(),
					"taskCreateService");

			for (String groupid : groupids.split(",")) {
				String resourceXMLPath = null;
				String status = null;
				if (taskType.equals(TaskType.PlayTask.getType())) {
					// 存入PlayList的XML文件并获取resource的XMLtaskId还是使用PlayListGroup的serialno
					resourceXMLPath = taskCreateService.generateContentXML(groupid, taskIds);
				} else if (taskType.equals(TaskType.PlayTaskControl.getType())) {
					// 取到serialno对应的playlistid
					PlayListGroup plg = taskCreateService.getPlaylistidBySerialno(taskIds);
					// taskIds = plg.getPlaylistid();taskId还是使用serialno
					status = plg.getStatus();
					if ("P".equalsIgnoreCase(status)) {
						status = "1";// 暂停
					} else if ("R".equalsIgnoreCase(status)) {
						status = "2";// 恢复
					} else if ("C".equalsIgnoreCase(status)) {
						status = "3";// 撤销
					}
				}
				Set<String> terminalIds = null;
				if (operatorid != null) {
					terminalIds = taskCreateService.getDistinctTremids(this.getServletContext(), groupid, operatorid);
				} else {
					terminalIds = taskCreateService.getDistinctTremids(this.getServletContext(), groupid);
				}

				log.info("groupid:" + groupid + " terminalIds:" + terminalIds);
				// 2. 调用setControlsByTerminalIds
				for (String terminalId : terminalIds) {
					setTasksByTerminalIds(terminalId, taskType, taskIds, resourceXMLPath, status);
				}
			}

		}
		log.debug("Exit TasksHessianImpl setTasksByGroupids method...");
		return "true";
	}

	@Override
	public String setTasksByTerminalIds(String terminalId, String taskType, String taskIds, String resourceXMLPath,
			String status) {

		log.debug("Enter into TasksHessianImpl setTasksByTerminalIds method...");

		TaskSelectService taskSelectService = (TaskSelectService) BeanUtil.getBean(this.getServletContext(),
				"taskSelectService");
		TaskCreateService taskCreateService = (TaskCreateService) BeanUtil.getBean(this.getServletContext(),
				"taskCreateService");

		if (terminalId == null || "".equals(terminalId) || taskType == null || "".equals(taskType) || taskIds == null
				|| "".equals(taskIds)) {
			return "false";
		} else {
			Set<TaskFlag> tasksSet = new HashSet<TaskFlag>();
			// 添加新的任务
			// 控制类任务特殊处理
			if (taskType.equals(TaskType.Controls.getType())) {
				for (String taskid : taskIds.split(",")) {
					for (TaskFlag flag : TaskFlag.values()) {
						if (flag.getFlag() == Integer.valueOf(taskid) + 17) {
							tasksSet.add(flag);
						}
					}
				}
			} else {
				for (TaskType type : TaskType.values()) {
					if (type.getType().equals(taskType)) { // 数字-->文字
						for (TaskFlag flag : TaskFlag.values()) {
							if (flag.toString().equals(type.toString())) { // 文字对比
								switch (flag) {
								case PlayTask:
									if (resourceXMLPath != null && !"".equals(resourceXMLPath))
										taskCreateService.savePlayListTaskItem(terminalId, taskIds, resourceXMLPath);
									break;
								case PlayTaskControl:
									if (status != null && !"".equals(status))
										taskCreateService.updatePlayListTaskItem(terminalId, taskIds, status);
									break;
								case ConfigTask:
									taskCreateService.saveConfigTaskItem(terminalId, taskIds);
									break;
								case SoftUpdateTask:
									taskCreateService.saveSoftUpdateTaskItem(terminalId, taskIds);
									break;
								case WeatherTask:
									taskCreateService.saveWeatherTaskItem(terminalId, taskIds);
									break;
								case LogFileUpLoad:
									taskCreateService.saveLogFileUpLoad(terminalId, taskIds);
									break;
								case PlayingContentUpLoad:
									taskCreateService.saveTermPlaying(terminalId, taskIds);
									break;
								case TermianlStatusLoad:
									taskCreateService.saveTermWorking(terminalId, taskIds);
									break;
								default:
									break;
								}
								// 将相应任务放到set
								tasksSet.add(flag);
							}
						}
					}
				}
			}
			// 将任务进行置位
			taskSelectService.setTaskFlag(terminalId, tasksSet);
		}

		log.debug("Exit TasksHessianImpl setTasksByTerminalIds method...");
		return "true";
	}

	@Override
	public String validation(String groupIds, String playlistid, String startDate, String endDate, String priority) {
		log.debug("Enter into TasksHessianImpl validation method...");

		if (groupIds == null || "".equals(groupIds))
			return "true|is ok";

		String[] groupidStrings = groupIds.split(",");
		if (groupidStrings.length == 0)
			return "true|is ok";
		TaskCreateService taskCreateService = (TaskCreateService) BeanUtil.getBean(this.getServletContext(),
				"taskCreateService");

		/* 本次新建PlayList属性 */
		String result = taskCreateService.validation(groupIds, playlistid, startDate, endDate, priority);

		try {
			result = new String(result.getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		log.debug("Exit TasksHessianImpl validation method...");
		return result;
	}

	@Override
	public void setTaskFlag(String terminalId, String flag) {
		TaskInfoCache.setTaskFlag(terminalId, flag);
	}

	@Override
	public String getTaskFlag(String terminalId) {
		return TaskInfoCache.getTaskFlag(terminalId);
	}

	@Override
	public boolean deleteValueByKey(String key) {
		return TaskInfoCache.deleteValueByKey(key);
	}

	public String getVideoThumb(String name, String size) {
		log.info("Enter into EBMSHessianImpl getVideoThumb method...");
		log.info("name:" + name + " size:" + size);
		if (name == null || size == null)
			return "false";
		String thumbPath = FFMpegUtil.getVideoThumb(name, size);
		log.info("thumbPath:" + thumbPath);

		if (!name.endsWith("flv"))
			new ConvertVideoFormatThread(name, "530x400").start();

		// 返回视频播放时长
		String timeLength = FFMpegUtil.getVideoLength(name);
		return timeLength;
	}

	@Override
	public String getTermSelectTime(String terminalId) {
		if (terminalId == null)
			return "false";
		return TermInfoCache.getTermSelectTime(terminalId);
	}

	@Override
	public String softupdate(String factoryid, String version, String path) {
		log.info("Enter into EBMSHessianImpl softupdate method...");
		log.info(" version:" + version + " path:" + path);
		if (version == null || "".equals(version) || path == null || "".equals(path))
			return "false";

		SoftInfoService softInfoService = (SoftInfoService) BeanUtil.getBean(this.getServletContext(),
				"softInfoService");
		return softInfoService.softupdate(factoryid, version, path);
	}

	@Override
	public String composite(String srcPath, String midPath, String destPath, String position) {
		return ImageMagicUtil.composite(srcPath, midPath, destPath, position);
	}

	@Override
	public String continueGetSnapshot(String terminalId, String flag) {
		log.info("TerminalId=" + terminalId + " flag=" + flag + " continueGetSnapshot");
		if (terminalId == null || "".equals(terminalId) || flag == null || "".equals(flag))
			return "false";
		ContinueGetSnapshotService continueGetSnapshotService = (ContinueGetSnapshotService) BeanUtil.getBean(
				this.getServletContext(), "continueGetSnapshotService");
		return continueGetSnapshotService.continueGetSnapshot(terminalId, flag);
	}

	@Override
	public String sendMail(String to, String subject, String content) {
		if (to == null || "".equals(to) || subject == null || "".equals(subject) || content == null
				|| "".equals(content))
			return "param error";

		try {
			subject = new String(subject.getBytes("ISO-8859-1"), "UTF-8");
			content = new String(content.getBytes("ISO-8859-1"), "UTF-8");

			if (MainConfig.getInstance().isUseMail()) {
				MailService mailService = (MailService) BeanUtil.getBean(this.getServletContext(), "mailService");
				mailService.sendMail(to, subject, content, null);
			} else {
				log.info("SendMail: To=" + to + " Subject=" + subject + " Content=" + content
						+ ",Just called,not really send!");
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "true";
	}

}
