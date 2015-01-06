package com.ebupt.ebms.service.task;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.cache.TaskInfoCache;
import com.ebupt.ebms.cache.TermInfoCache;
import com.ebupt.ebms.dao.ImessageDao;
import com.ebupt.ebms.dao.ImessageGroupDao;
import com.ebupt.ebms.dao.PlayListDao;
import com.ebupt.ebms.dao.PlayListGroupDao;
import com.ebupt.ebms.dao.PlayListTaskItemDao;
import com.ebupt.ebms.dao.SoftInfoDao;
import com.ebupt.ebms.dao.SoftInfoTaskItemDao;
import com.ebupt.ebms.dao.TermConfigDao;
import com.ebupt.ebms.dao.TermConfigTaskItemDao;
import com.ebupt.ebms.dao.TermGroupItemDao;
import com.ebupt.ebms.dao.TermPlayingDao;
import com.ebupt.ebms.dao.UploadLogReportDao;
import com.ebupt.ebms.dao.WeatherDao;
import com.ebupt.ebms.dao.WeatherTaskItemDao;
import com.ebupt.ebms.dao.WorkStatusDao;
import com.ebupt.ebms.entity.Imessage;
import com.ebupt.ebms.entity.ImessageGroup;
import com.ebupt.ebms.entity.PlayListGroup;
import com.ebupt.ebms.entity.PlayListTaskItem;
import com.ebupt.ebms.entity.SoftInfo;
import com.ebupt.ebms.entity.SoftInfoTaskItem;
import com.ebupt.ebms.entity.TermConfig;
import com.ebupt.ebms.entity.TermConfigTaskItem;
import com.ebupt.ebms.entity.TermPlaying;
import com.ebupt.ebms.entity.TermWorking;
import com.ebupt.ebms.entity.UploadLogReport;
import com.ebupt.ebms.entity.Weather;
import com.ebupt.ebms.entity.WeatherTaskItem;
import com.ebupt.ebms.servlet.task.TaskSelectServlet.TaskFlag;
import com.ebupt.ebms.servlet.task.TaskStatusServlet.TaskType;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.XMLUtil;

/**
 * @author QiChen Create on 2011-3-8
 * @version 1.0
 */
@Service
public class TaskSelectService {

	private static final Logger log = Logger.getLogger(TaskSelectService.class);

	@Autowired
	@Qualifier("playListTaskItemDaoImpl")
	PlayListTaskItemDao playListTaskItemDao;

	@Autowired
	@Qualifier("softInfoTaskItemDaoImpl")
	SoftInfoTaskItemDao softInfoTaskItemDao;

	@Autowired
	@Qualifier("softInfoDaoImpl")
	SoftInfoDao softInfoDao;

	@Autowired
	@Qualifier("imessageDaoImpl")
	ImessageDao imessageDao;

	@Autowired
	@Qualifier("imessageGroupDaoImpl")
	ImessageGroupDao imessageGroupDao;

	@Autowired
	@Qualifier("termGroupItemDaoImpl")
	TermGroupItemDao termGroupItemDao;

	@Autowired
	@Qualifier("weatherDaoImpl")
	WeatherDao weatherDao;

	@Autowired
	@Qualifier("weatherTaskItemDaoImpl")
	WeatherTaskItemDao weatherTaskItemDao;

	@Autowired
	@Qualifier("termConfigTaskItemDaoImpl")
	TermConfigTaskItemDao termConfigTaskItemDao;

	@Autowired
	@Qualifier("termConfigDaoImpl")
	TermConfigDao termConfigDao;

	@Autowired
	@Qualifier("uploadLogReportDaoImpl")
	UploadLogReportDao uploadLogReportDao;

	@Autowired
	@Qualifier("playListDaoImpl")
	PlayListDao playListDao;

	@Autowired
	@Qualifier("playListGroupDaoImpl")
	PlayListGroupDao playListGroupDao;

	@Autowired
	@Qualifier("termPlayingDaoImpl")
	TermPlayingDao termPlayingDao;

	@Autowired
	@Qualifier("workStatusDaoImpl")
	WorkStatusDao workStatusDao;

	/**
	 * ����taskFlag ����redis�����λ�ã����ض�Ӧλ�õ�ö��
	 * 
	 * @param taskFlag
	 * @return
	 */
	public List<TaskFlag> parseTaskFlag(String taskFlag) {

		String _taskFlag = String.format("%032d", new BigInteger(taskFlag));

		List<TaskFlag> _taskLists = new ArrayList<TaskFlag>();
		for (int i = 0; i < 32; i++) {
			Character c = _taskFlag.charAt(i);
			if (c == '1') {
				for (TaskFlag t : TaskFlag.values()) {
					if (t.getFlag() == 32 - i) {
						_taskLists.add(t);
					}
				}
			}
		}

		return _taskLists;
	}

	/**
	 * �����ն�����λ
	 */
	public void setTaskFlag(String terminalId, Set<TaskFlag> setFlags) {
		String taskFlag = TaskInfoCache.getTaskFlag(terminalId);
		List<TaskFlag> taskFlags = this.parseTaskFlag(taskFlag);
		Set<TaskFlag> tasksSet = new HashSet<TaskFlag>();
		tasksSet.addAll(taskFlags); // ԭ�е���λ
		tasksSet.addAll(setFlags); // ��������λ

		BigInteger bigInt = new BigInteger("0");
		for (TaskFlag flag : tasksSet) {
			String str = "1";
			for (int i = 0; i < flag.getFlag() - 1; i++) {
				str = str + "0";
			}
			bigInt = bigInt.add(new BigInteger(str));
		}
		// �����������λ
		TaskInfoCache.setTaskFlag(terminalId, bigInt.toString());
		log.info("terminalId:" + terminalId + " taskFlag:" + bigInt.toString());

	}

	/**
	 * ���ݴ�������TaskFlag�ļ��ϣ����е�����XML
	 * 
	 * @param sc
	 * @param taskFlags
	 * @param terminalId
	 * @return XML
	 */
	public String writeXML(ServletContext sc, List<TaskFlag> taskFlags, String terminalId) {

		Document document = DocumentHelper.createDocument();
		Element command = document.addElement("command");
		Element tasklist = command.addElement("tasklist");
		Element taskcount = tasklist.addElement("taskcount");
		taskcount.setText("0"); // ��ʼ��Ϊ�㣬�������ƥ���ϣ����һ
		for (TaskFlag taskFlag : taskFlags) {
			switch (taskFlag) {
			case PlayTask:
				List<Element> e1 = this.getPlayTaskItem(sc, terminalId);
				if (e1 != null && !e1.isEmpty()) {
					Integer size = Integer.valueOf(taskcount.getText()) + e1.size();
					taskcount.setText(size.toString());
					for (Element e : e1) {
						tasklist.add(e);
					}
				}
				break;
			case SoftUpdateTask:
				Element e2 = this.getSoftUpdateTaskItem(terminalId);
				if (e2 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e2);
				}
				break;
			case InstantMessagingTask:
				Element e4 = this.getInstantMessagingTaskItem(sc, terminalId);
				if (e4 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e4);
				}
				break;
			case ConfigTask:
				List<Element> e5 = this.getConfigTaskItem(terminalId);
				if (e5 != null && !e5.isEmpty()) {
					Integer size = Integer.valueOf(taskcount.getText()) + e5.size();
					taskcount.setText(size.toString());
					for (Element e : e5) {
						tasklist.add(e);
					}
				}
				break;
			case WeatherTask:
				List<Element> e6 = this.getWeatherTaskItem(terminalId);
				if (e6 != null && !e6.isEmpty()) {
					Integer size = Integer.valueOf(taskcount.getText()) + e6.size();
					taskcount.setText(size.toString());
					for (Element e : e6) {
						tasklist.add(e);
					}
				}
				break;
			case PlayTaskControl:
				List<Element> e7 = this.getPlayTaskControl(terminalId);
				if (e7 != null && !e7.isEmpty()) {
					Integer size = Integer.valueOf(taskcount.getText()) + e7.size();
					taskcount.setText(size.toString());
					for (Element e : e7) {
						tasklist.add(e);
					}
				}
				break;
			case TerminalInfoLoad:
				Element e8 = this.getTerminalInfoLoad(terminalId);
				if (e8 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e8);
					SQLCache.putTermOperLog(terminalId, "�·�������Ϣ�ϱ�����");
				}
				break;
				// �ն˹���״̬�ϱ�
			case TermianlStatusLoad:
				Element e9 = this.getTermianlStatusLoad(terminalId);
				if (e9 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e9);
					SQLCache.putTermOperLog(terminalId, "�·�����״̬�ϱ�����");
				}
				break;
				// �ն��ڲ������ϱ�
			case PlayingContentUpLoad:
				Element e10 = this.getPlayingContentUpLoad(terminalId);
				if (e10 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e10);
					SQLCache.putTermOperLog(terminalId, "�·��ڲ������ϱ�����");
				}
				break;
			case LogFileUpLoad:
				List<Element> e12 = this.getLogFileUpLoad(terminalId);
				if (e12 != null && !e12.isEmpty()) {
					Integer size = Integer.valueOf(taskcount.getText()) + e12.size();
					taskcount.setText(size.toString());
					for (Element e : e12) {
						tasklist.add(e);
					}
				}
				break;
			case InstantMonitor:
				Element e13 = this.getInstantMonitorElement(terminalId);
				if (e13 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e13);
					SQLCache.putTermOperLog(terminalId, "�·�����ʵʱ�������");
				}
				break;
			case InstantDataTask:
				// ��ʱ������
				break;
				
			/** ͬһ���� */
			case Restart: // ���������� 0
				Element e17 = this.getControlsElement(terminalId, TaskFlag.Restart.getFlag());
				if (e17 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e17);
					SQLCache.putTermOperLog(terminalId, "�·���������");
				}
				break;
			case Sleep: // ���������� 1
				Element e18 = this.getControlsElement(terminalId, TaskFlag.Sleep.getFlag());
				if (e18 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e18);
					SQLCache.putTermOperLog(terminalId, "�·���������");
				}
				break;
			case Awake: // ���������� 2
				Element e19 = this.getControlsElement(terminalId, TaskFlag.Awake.getFlag());
				if (e19 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e19);
					SQLCache.putTermOperLog(terminalId, "�·���������");
				}
				break;
			case DiskFormat: // ���������� 3
				Element e20 = this.getControlsElement(terminalId, TaskFlag.DiskFormat.getFlag());
				if (e20 != null) {
					Integer size = Integer.valueOf(taskcount.getText()) + 1;
					taskcount.setText(size.toString());
					tasklist.add(e20);
					SQLCache.putTermOperLog(terminalId, "�·������������");
				}
				break;
			default:
				break;
			}
		}
		// ���������Ϊ0�������Ӧ��λ
		if ("0".equals(taskcount.getTextTrim())) {
			TaskInfoCache.setTaskFlag(terminalId, "0");
		}
		
		return XMLUtil.formatXML(document);
	}

	// ����������Item��ʽ
	private List<Element> getPlayTaskItem(ServletContext sc, String terminalId) {

		ArrayList<Element> elements = new ArrayList<Element>();

		List<PlayListTaskItem> items = playListTaskItemDao.findNewTerminalId(terminalId);
		if (items == null || items.isEmpty()) {
			return null;
		}

		for (PlayListTaskItem item : items) {
			Element taskitem = new DOMElement("taskitem");
			taskitem.addElement("tasktype").addText(TaskType.PlayTask.getType());
			taskitem.addElement("taskid").addText(item.getPlaylistid());

			Element contents = taskitem.addElement("contents");

			PlayListGroup playListGroup = playListGroupDao.getPlayListGroupBySerialno(item.getPlaylistid());// �˴�item.getPlaylistid()����PlayListGroup��serialno
			Element content = contents.addElement("content");
			if (item.getCsize().toString() != null && !"".equals(item.getCsize().toString())) {
				content.addElement("csize").addText(item.getCsize().toString());
			} else {
				content.addElement("csize").addText("");
			}
			content.addElement("link").addText(item.getUrl());
			content.addElement("md5").addText(item.getMd5());

			if (playListGroup != null) {
				content.addElement("startdate").addText(playListGroup.getStartdate());
				content.addElement("enddate").addText(playListGroup.getEnddate());
				content.addElement("playmode").addText(String.valueOf(playListGroup.getPriority()));
			}
			elements.add(taskitem);
			SQLCache.putTermOperLog(terminalId, "�·���������ID[" + item.getPlaylistid()+"]");
		}

		return elements;
	}

	// �������������Item��ʽ
	private Element getSoftUpdateTaskItem(String terminalId) {
		SoftInfoTaskItem item = softInfoTaskItemDao.findNewTerminalId(terminalId);
		if (item != null) {
			SoftInfo softInfo = (SoftInfo) softInfoDao.find(SoftInfo.class, item.getSoftinfoid());
			if (softInfo != null) {
				Element taskitem = new DOMElement("taskitem");
				taskitem.addElement("tasktype").addText(TaskType.SoftUpdateTask.getType());
				taskitem.addElement("taskid").addText(item.getSoftinfoid());
				taskitem.addElement("version").addText(softInfo.getVersion());
				taskitem.addElement("link").addText(softInfo.getLink());
				SQLCache.putTermOperLog(terminalId, "�·������������[" + item.getSoftinfoid()+"]");
				return taskitem;
			}
		}
		return null;
	}

	// ����������
	private Element getControlsElement(String terminalId, Integer control) {

		Element taskitem = new DOMElement("taskitem");
		taskitem.addElement("tasktype").addText(TaskType.Controls.getType());
		// ��ͷ�������ʶλ �� �ϴ���ʱ�������
		taskitem.addElement("taskid").addText(control.toString() + NumberUtil.generatorId());
		taskitem.addElement("control").addText((control - 17) + "");
		return taskitem;
	}
	
	// ʵʱ���������
	private Element getInstantMonitorElement(String terminalId) {

		Element taskitem = new DOMElement("taskitem");
		taskitem.addElement("tasktype").addText(TaskType.InstantMonitor.getType());
		// ��ͷ�������ʶλ �� �ϴ���ʱ�������
		taskitem.addElement("taskid").addText(NumberUtil.generatorId());
		taskitem.addElement("cmd").addText("0");
		return taskitem;
	}

	// ��ʱ��Ϣ������
	private Element getInstantMessagingTaskItem(ServletContext sc, String terminalId) {

		String groupIds = TermInfoCache.getGroupsByTerminalId(sc, terminalId);

		if (groupIds != null && !groupIds.equals("")) {
			ImessageGroup imessageGroup = imessageGroupDao.findLastMessageByGroupIds(groupIds);

			if (imessageGroup != null) {
				Imessage imessage = (Imessage) imessageDao.find(Imessage.class, imessageGroup.getMessageid());

				if (imessage != null) {
					Element taskitem = new DOMElement("taskitem");
					taskitem.addElement("tasktype").addText(TaskType.InstantMessagingTask.getType());
					taskitem.addElement("taskid").addText(imessage.getMessageid());
					if (imessage.getFontsize() != null) {
						taskitem.addElement("fontsize").addText(imessage.getFontsize());
					} else {
						taskitem.addElement("fontsize").addText("");
					}
					if (imessage.getBgcolor() != null) {
						taskitem.addElement("bgcolor").addText(imessage.getBgcolor());
					} else {
						taskitem.addElement("bgcolor").addText("");
					}
					if (imessage.getFontcolor() != null) {
						taskitem.addElement("fontcolor").addText(imessage.getFontcolor());
					} else {
						taskitem.addElement("fontcolor").addText("");
					}
					if (imessage.getSpeed() != null) {
						taskitem.addElement("speed").addText(imessage.getSpeed());
					} else {
						taskitem.addElement("speed").addText("");
					}
					if (imessage.getCount() != null) {
						taskitem.addElement("count").addText(imessage.getCount());
					} else {
						taskitem.addElement("count").addText("");
					}
					if (imessage.getTimelength() != null) {
						taskitem.addElement("timelength").addText(imessage.getTimelength());
					} else {
						taskitem.addElement("timelength").addText("");
					}
					if (imessage.getSpeed() != null && imessage.getSpeed().equals("9")) {
						taskitem.addElement("message").addText("");
						SQLCache.putTermOperLog(terminalId, "������ʱ��Ϣ[" + imessage.getMessage() + "]");
					} else {
						taskitem.addElement("message").addText(imessage.getMessage());
						SQLCache.putTermOperLog(terminalId, "�·���ʱ��Ϣ[" + imessage.getMessage() + "]");
					}

					return taskitem;
				}
			}
		}
		return null;
	}

	// ����������Item��ʽ
	private List<Element> getConfigTaskItem(String terminalId) {
		List<TermConfigTaskItem> items = termConfigTaskItemDao.findItemByTerminalId(terminalId);
		List<Element> result = new ArrayList<Element>();
		if (items != null && !items.isEmpty()) {
			for (TermConfigTaskItem item : items) {
				if (item.getTermconfigid() != null && !"".equals(item.getTermconfigid())) {
					TermConfig termConfig = (TermConfig) termConfigDao.find(TermConfig.class, item.getTermconfigid());
					if (termConfig != null) {
						Element taskitem = new DOMElement("taskitem");
						taskitem.addElement("tasktype").addText(TaskType.ConfigTask.getType());
						taskitem.addElement("taskid").addText(item.getTermconfigid());
						Element config = taskitem.addElement("config");
						if (termConfig.getStartuptime() != null) {
							config.addElement("startuptime").addText(termConfig.getStartuptime());
						} else {
							config.addElement("startuptime").addText("");
						}
						if (termConfig.getShutdowntime() != null) {
							config.addElement("shutdowntime").addText(termConfig.getShutdowntime());
						} else {
							config.addElement("shutdowntime").addText("");
						}
						if (termConfig.getDiskspacealarm() != null) {
							config.addElement("diskspacealarm").addText(termConfig.getDiskspacealarm());
						} else {
							config.addElement("diskspacealarm").addText("");
						}
						if (termConfig.getServerip() != null) {
							config.addElement("serverip").addText(termConfig.getServerip());
						} else {
							config.addElement("serverip").addText("");
						}
						if (termConfig.getServerport() != null) {
							config.addElement("serverport").addText(termConfig.getServerport());
						} else {
							config.addElement("serverport").addText("");
						}
						if (termConfig.getSelectinterval() != null) {
							config.addElement("selectinterval").addText(termConfig.getSelectinterval());
						} else {
							config.addElement("selectinterval").addText("");
						}
						if (termConfig.getRedisip() != null) {
							config.addElement("redisip").addText(termConfig.getRedisip());
						} else {
							config.addElement("redisip").addText("");
						}
						if (termConfig.getRedisport() != null) {
							config.addElement("redisport").addText(termConfig.getRedisport());
						} else {
							config.addElement("redisport").addText("");
						}
						if (termConfig.getMaxmonitortime() != null) {
							config.addElement("maxmonitortime").addText(termConfig.getMaxmonitortime());
						} else {
							config.addElement("maxmonitortime").addText("");
						}
						if (termConfig.getVolume() != null) {
							config.addElement("volume").addText(termConfig.getVolume());
						} else {
							config.addElement("volume").addText("");
						}
						if (termConfig.getDownloadserver() != null) {
							config.addElement("downloadserver").addText(termConfig.getDownloadserver());
						} else {
							config.addElement("downloadserver").addText("");
						}
						if (termConfig.getDownloadrate() != null) {
							config.addElement("downloadrate").addText(termConfig.getDownloadrate());
						} else {
							config.addElement("downloadrate").addText("");
						}
						if (termConfig.getDownloadtime() != null) {
							config.addElement("downloadtime").addText(termConfig.getDownloadtime());
						} else {
							config.addElement("downloadtime").addText("");
						}
						if (termConfig.getLogserver() != null) {
							config.addElement("logserver").addText(termConfig.getLogserver());
						} else {
							config.addElement("logserver").addText("");
						}
						if (termConfig.getUploadlogtime() != null) {
							config.addElement("uploadlogtime").addText(termConfig.getUploadlogtime());
						} else {
							config.addElement("uploadlogtime").addText("");
						}
						if (termConfig.getKeeplogtime() != null) {
							config.addElement("keeplogtime").addText(termConfig.getKeeplogtime());
						} else {
							config.addElement("keeplogtime").addText("");
						}
						if (termConfig.getLoglevel() != null) {
							config.addElement("loglevel").addText(termConfig.getLoglevel());
						} else {
							config.addElement("loglevel").addText("");
						}
						if (termConfig.getPlacementmodel() != null) {
							config.addElement("placementmodel").addText(termConfig.getPlacementmodel());
						} else {
							config.addElement("placementmodel").addText("");
						}
						if (termConfig.getResolution() != null) {
							config.addElement("outputresolution").addText(termConfig.getResolution());
						} else {
							config.addElement("outputresolution").addText("");
						}
						result.add(taskitem);
						SQLCache.putTermOperLog(terminalId, "�·���������[" + item.getTermconfigid()+"]");
					}
				}
			}
			items.clear();
		}
		return result;
	}

	// ����Ԥ������
	private List<Element> getWeatherTaskItem(String terminalId) {
		List<WeatherTaskItem> items = weatherTaskItemDao.findNewItemsByTerminalId(terminalId);
		List<Element> result = new ArrayList<Element>();
		if (items != null && !items.isEmpty()) {
			for (WeatherTaskItem item : items) {
				if (item.getWeatherid() != null) {
					List<Weather> weathers = weatherDao.findWeathersById(item.getWeatherid());
					if (weathers != null && !weathers.isEmpty()) {
						Element taskitem = new DOMElement("taskitem");
						taskitem.addElement("tasktype").addText(TaskType.WeatherTask.getType());
						taskitem.addElement("taskid").addText(items.get(0).getWeatherid());
						Element weather = taskitem.addElement("weather");
						if (weathers.get(0).getReleasetime() != null) {
							weather.addElement("releasetime").addText(weathers.get(0).getReleasetime());
						} else {
							weather.addElement("releasetime").addText("");
						}
						if (weathers.get(0).getCity() != null) {
							weather.addElement("city").addText(weathers.get(0).getCity());
						} else {
							weather.addElement("city").addText("");
						}
						for (Weather w : weathers) {
							Element detail = weather.addElement("detail");
							if (w.getDatetime() != null) {
								detail.addElement("date").addText(w.getDatetime());
							} else {
								detail.addElement("date").addText("");
							}
							if (w.getMintemp() != null) {
								detail.addElement("mintemp").addText(w.getMintemp());
							} else {
								detail.addElement("mintemp").addText("");
							}
							if (w.getMaxtemp() != null) {
								detail.addElement("maxtemp").addText(w.getMaxtemp());
							} else {
								detail.addElement("maxtemp").addText("");
							}
							if (w.getWindlevel() != null) {
								detail.addElement("windlevel").addText(w.getWindlevel());
							} else {
								detail.addElement("windlevel").addText("");
							}
							if (w.getAirquality() != null) {
								detail.addElement("airquality").addText(w.getAirquality());
							} else {
								detail.addElement("airquality").addText("");
							}
							if (w.getWeatherstatus() != null) {
								detail.addElement("weatherstatus").addText(w.getWeatherstatus());
							} else {
								detail.addElement("weatherstatus").addText("");
							}
							if (w.getImage() != null) {
								detail.addElement("image").addText(w.getImage());
							} else {
								detail.addElement("image").addText("");
							}
						}
						result.add(taskitem);
						SQLCache.putTermOperLog(terminalId, "�·�����Ԥ������[" + item.getWeatherid()+"]");
					}
				}
			}
			items.clear();
		}
		return result;

	}

	// �������������
	private List<Element> getPlayTaskControl(String terminalId) {
		List<Element> elements = new ArrayList<Element>();
		List<PlayListTaskItem> items = playListTaskItemDao.findItemsByTerminalId(terminalId);
		if (items != null && !items.isEmpty()) {
			for (PlayListTaskItem item : items) {
				String playlistid = playListGroupDao.getPlaylistidBySerialno(item.getPlaylistid());
				if(playlistid == null)
					continue;
				Element taskitem = new DOMElement("taskitem");
				taskitem.addElement("tasktype").addText(TaskType.PlayTaskControl.getType());
				taskitem.addElement("taskid").addText(item.getPlaylistid());
				taskitem.addElement("playlistid").addText(playlistid);
				taskitem.addElement("cmd").addText(item.getStatus());
				elements.add(taskitem);
				String status = null;
				if("1".equals(item.getStatus())){
					status = "��ͣ";
				}else if ("2".equals(item.getStatus())){
					status = "�ָ�";
				}else if ("3".equals(item.getStatus())){
					status = "����";
				}else{
					status = "";
				}
				SQLCache.putTermOperLog(terminalId, "�·�["+status+"]��������[" + item.getPlaylistid()+"]");
			}
			items.clear();
		}
		return elements;

	}

	// �ն������ϱ�
	private Element getTerminalInfoLoad(String terminalId) {

		Element taskitem = new DOMElement("taskitem");
		taskitem.addElement("tasktype").addText(TaskType.TerminalInfoLoad.getType());
		taskitem.addElement("taskid").addText(NumberUtil.generatorId());
		return taskitem;
	}

	// �ն˹���״̬�ϱ�
	private Element getTermianlStatusLoad(String terminalId) {
		TermWorking workStatus = workStatusDao.findWorkStatus(terminalId);
		if(workStatus != null){
			Element taskitem = new DOMElement("taskitem");
			taskitem.addElement("tasktype").addText(TaskType.TermianlStatusLoad.getType());
			taskitem.addElement("taskid").addText(workStatus.getTaskid());
			return taskitem;
		}
		return null;
	}

	// �ն��ڲ������ϱ�
	private Element getPlayingContentUpLoad(String terminalId) {
		List<TermPlaying> termPlayings = termPlayingDao.findTermPlaying(terminalId);
		if (termPlayings != null && !termPlayings.isEmpty()) {
			TermPlaying termPlaying = termPlayings.get(0);
			Element taskitem = new DOMElement("taskitem");
			taskitem.addElement("tasktype").addText(TaskType.PlayingContentUpLoad.getType());
			taskitem.addElement("taskid").addText(termPlaying.getTaskid());
			return taskitem;
		}
		return null;
	}

	// ʵʱ����������
	// private Element getInstantDataTask(String terminalId) {
	// Element taskitem = new DOMElement("taskitem");
	// return taskitem;
	// }

	// �ն���־�ϱ�
	private List<Element> getLogFileUpLoad(String terminalId) {
		List<UploadLogReport> logs = uploadLogReportDao.findAllLogReport(terminalId);
		List<Element> elements = new ArrayList<Element>();
		if (logs != null && !logs.isEmpty()) {
			for (UploadLogReport log : logs) {
				Element taskitem = new DOMElement("taskitem");
				taskitem.addElement("tasktype").addText(TaskType.LogFileUpLoad.getType());
				taskitem.addElement("taskid").addText(log.getTaskid());
				taskitem.addElement("logtype").addText(log.getLogtype().toLowerCase());
				elements.add(taskitem);
				SQLCache.putTermOperLog(terminalId, "�·�["+log.getLogtype().toUpperCase()+"]��־�ϱ�����");
			}
			logs.clear();
			return elements;
		}
		return null;
	}

}
