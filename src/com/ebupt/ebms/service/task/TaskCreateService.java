package com.ebupt.ebms.service.task;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.cache.TaskInfoCache;
import com.ebupt.ebms.cache.TermInfoCache;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.dao.ContentDao;
import com.ebupt.ebms.dao.PlayListDao;
import com.ebupt.ebms.dao.PlayListGroupDao;
import com.ebupt.ebms.dao.PlayListItemDao;
import com.ebupt.ebms.dao.PlayListTaskItemDao;
import com.ebupt.ebms.dao.ProgramDao;
import com.ebupt.ebms.dao.ProgramItemDao;
import com.ebupt.ebms.dao.SoftInfoDao;
import com.ebupt.ebms.dao.SoftInfoTaskItemDao;
import com.ebupt.ebms.dao.SubTemplateDao;
import com.ebupt.ebms.dao.TemplateDao;
import com.ebupt.ebms.dao.TermConfigTaskItemDao;
import com.ebupt.ebms.dao.TermPlayingDao;
import com.ebupt.ebms.dao.UploadLogReportDao;
import com.ebupt.ebms.dao.WeatherTaskItemDao;
import com.ebupt.ebms.dao.WorkStatusDao;
import com.ebupt.ebms.entity.Content;
import com.ebupt.ebms.entity.PlayList;
import com.ebupt.ebms.entity.PlayListGroup;
import com.ebupt.ebms.entity.PlayListItem;
import com.ebupt.ebms.entity.PlayListTaskItem;
import com.ebupt.ebms.entity.Program;
import com.ebupt.ebms.entity.ProgramItem;
import com.ebupt.ebms.entity.SoftInfo;
import com.ebupt.ebms.entity.SoftInfoTaskItem;
import com.ebupt.ebms.entity.SubTemplate;
import com.ebupt.ebms.entity.Template;
import com.ebupt.ebms.entity.TermConfigTaskItem;
import com.ebupt.ebms.entity.TermPlaying;
import com.ebupt.ebms.entity.TermWorking;
import com.ebupt.ebms.entity.UploadLogReport;
import com.ebupt.ebms.entity.WeatherTaskItem;
import com.ebupt.ebms.service.task.xml.FileXML;
import com.ebupt.ebms.service.task.xml.PlayListXML;
import com.ebupt.ebms.service.task.xml.ResourceXML;
import com.ebupt.ebms.utils.FileUtil;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.TimeUtil;
import com.ebupt.ebms.utils.MD5Util;
import com.ebupt.ebms.utils.XMLUtil;

/**
 * @author QiChen Create on 2011-3-14
 * @version 1.0
 */
@Service
public class TaskCreateService {

	private static final Logger log = Logger.getLogger(TaskCreateService.class);

	@Autowired
	@Qualifier("playListTaskItemDaoImpl")
	private PlayListTaskItemDao playListTaskItemDao;

	@Autowired
	@Qualifier("playListDaoImpl")
	private PlayListDao playListDao;

	@Autowired
	@Qualifier("playListGroupDaoImpl")
	private PlayListGroupDao playListGroupDao;

	@Autowired
	@Qualifier("templateDaoImpl")
	private TemplateDao templateDao;

	@Autowired
	@Qualifier("playListItemDaoImpl")
	private PlayListItemDao playListItemDao;

	@Autowired
	@Qualifier("subTemplateDaoImpl")
	private SubTemplateDao subTemplateDao;

	@Autowired
	@Qualifier("programDaoImpl")
	private ProgramDao programDao;

	@Autowired
	@Qualifier("programItemDaoImpl")
	private ProgramItemDao programItemDao;

	@Autowired
	@Qualifier("contentDaoImpl")
	private ContentDao contentDao;

	@Autowired
	@Qualifier("termConfigTaskItemDaoImpl")
	private TermConfigTaskItemDao termConfigTaskItemDao;

	@Autowired
	@Qualifier("softInfoDaoImpl")
	private SoftInfoDao softInfoDao;

	@Autowired
	@Qualifier("weatherTaskItemDaoImpl")
	private WeatherTaskItemDao weatherTaskItemDao;

	@Autowired
	@Qualifier("softInfoTaskItemDaoImpl")
	private SoftInfoTaskItemDao softInfoTaskItemDao;

	@Autowired
	@Qualifier("uploadLogReportDaoImpl")
	private UploadLogReportDao uploadLogReportDao;

	@Autowired
	@Qualifier("termPlayingDaoImpl")
	private TermPlayingDao termPlayingDao;

	@Autowired
	@Qualifier("workStatusDaoImpl")
	private WorkStatusDao workStatusDao;

	public Set<String> getDistinctTremids(ServletContext sc, String groupids, String operatorid) {

		Set<String> terminalIds = new HashSet<String>();
		for (String groupid : groupids.split(",")) {
			Set<String> temp = TermInfoCache.getTerminalIdsByGroupId(sc, groupid, operatorid);
			terminalIds.addAll(temp);
		}
		return terminalIds;
	}
	
	public Set<String> getDistinctTremids(ServletContext sc, String groupids) {

		Set<String> terminalIds = new HashSet<String>();
		for (String groupid : groupids.split(",")) {
			Set<String> temp = TermInfoCache.getTerminalIdsByGroupId(sc, groupid);
			terminalIds.addAll(temp);
		}
		return terminalIds;
	}

	public String generateContentXML(String groupid, String taskIds) {

		// 这里任务id即是PlayListGroup的serialno,需要获取到播放任务id
		String serialno = taskIds;
		PlayListGroup pgroup = this.getPlaylistidBySerialno(serialno);
		if (pgroup == null)
			return null;
		String playListid = pgroup.getPlaylistid();

		// 将播放任务跟群组对应关系的状态更改
		playListGroupDao.updatePlayListGroupStatus(serialno, pgroup.getStartdate());

		// 使用如下组合作为资源路径的缓存键
		//String key = groupid + playListid;

		//String path = TaskInfoCache.getResourceXMLPath(key);

		//log.debug("generateContentXML,key:" + key + " ResourceXMLPath =" + path);
		
		String path = null;

		ResourceXML resourceXML = new ResourceXML();
		PlayList playList = (PlayList) playListDao.find(PlayList.class, playListid);
		if (playList == null) {
			log.error("Cann't find playListid:" + playListid + " in PlayList Table");
			return null;
		}

		// 找到播放任务对应的模板
		Template template = null;
		List<PlayListItem> playListItems = null;
		List<SubTemplate> subTemplates = null;
		try {
			template = (Template) templateDao.find(Template.class, playList.getTemplateid());
			if (template == null) {
				log.error("Cann't find templateid:" + playList.getTemplateid() + " in Template Table");
				return null;
			}
			playListItems = playListItemDao.findItems(playListid);
			if (playListItems == null || playListItems.size() == 0) {
				log.error("Cann't find playListid:" + playListid + " in PlayListItem Table");
				return null;
			}
			subTemplates = subTemplateDao.findSubtemplates(template.getTemplateid());
			if (subTemplates == null || subTemplates.size() == 0) {
				log.error("Cann't find templateid:" + template.getTemplateid() + " in SubTemplates Table");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		List<Element> playListElements = new ArrayList<Element>();

		/********************* PlayList begin **************************/

		String startdate = TimeUtil.getParseDate(pgroup.getStartdate());
		String enddate = TimeUtil.getParseDate(pgroup.getEnddate());
		String playmode = pgroup.getPriority() + "";
		Document document = DocumentHelper.createDocument();

		// PlayList 1级
		Element playListXML = document.addElement("playlist");
		playListXML.addAttribute("id", playListid);
		playListXML.addAttribute("name", playList.getName());
		playListXML.addAttribute("playmode", playmode);
		playListXML.addAttribute("startdate", startdate);
		playListXML.addAttribute("enddate", enddate);
		playListXML.addAttribute("continuous", "1");//针对北京移动跨天连续播放的需求进行特殊处理，1表示连续播放，0或不加表示间断播放
		playListXML.addAttribute("playtime", this.formatPlaytime(playList.getStarttime(), playList.getEndtime()));
		playListElements.add(playListXML);

		/********************* PlayList end **************************/

		Set<PlayListXML> playListSet = new HashSet<PlayListXML>();

		Map<String, FileXML> fileMap = new HashMap<String, FileXML>();

		/********************** Screen begin **************************/
		for (Element playlistelement : playListElements) {
			// Screen 2级
			Element screenXML = playlistelement.addElement("screen");

			screenXML.addAttribute("name", template.getName());
			screenXML.addAttribute("id", template.getTemplateid());
			screenXML.addAttribute("width", template.getWidth());
			screenXML.addAttribute("height", template.getHeight());

			if (template.getPath() != null) {
				String backGroundPath = template.getPath();// 前台存储相对路径
				String backGround = backGroundPath.substring(backGroundPath.lastIndexOf("/") + 1);
				screenXML.addAttribute("background", backGround);

				// 把背景图也放入发送到终端的队列
				FileXML filexml = new FileXML();
				filexml.setId(template.getTemplateid());
				filexml.setType("image");
				filexml.setMd5(MD5Util.FileMD5(MainConfig.getInstance().getWebPath() + File.separator + backGroundPath));
				//filexml.setName(backGround);
				filexml.setName(template.getName());
				filexml.setHref(backGroundPath);

				fileMap.put(filexml.getId(), filexml);
			}

			/*------------------- 针对模板中包含天气、时钟的情况处理 -----------------------*/
			for (SubTemplate sub : subTemplates) {
				//if ("time".equals(sub.getType()) || "weather".equals(sub.getType())) {
				if ("weather".equals(sub.getType())) {
					Element surfaceXML = screenXML.addElement("surface");
					surfaceXML.addAttribute("type", sub.getType());
					surfaceXML.addAttribute("id", sub.getSubtemplateid());
					surfaceXML.addAttribute("name", sub.getDescription());
					surfaceXML.addAttribute("level", sub.getZlevel());

					String[] position = sub.getPosition().split(",");
					if (position.length == 4) {
						surfaceXML.addAttribute("x", position[0]);
						surfaceXML.addAttribute("y", position[1]);
						surfaceXML.addAttribute("width", position[2]);
						surfaceXML.addAttribute("height", position[3]);
					}
					surfaceXML.addAttribute("alpha", sub.getAlpha());
					surfaceXML.addAttribute("hide", sub.getHide());

//					if ("time".equals(sub.getType())) {
						// 如果模板子项里面有time这个类型type，则进行下面的操作
//						Element timeXML = surfaceXML.addElement("time");
//						timeXML.addAttribute("fontcolor", "FFFFFF");
//						timeXML.addAttribute("fontname", "simhei");
//						timeXML.addAttribute("fontsize", "36");
//						timeXML.addAttribute("format", "YYYY年MM月DD日 星期WK hh:mm:ss");

//					} else if ("weather".equals(sub.getType())) {
						// 如果模板子项里面有weather这个类型type，则进行下面的操作
						Element weatherXML = surfaceXML.addElement("weather");
						weatherXML.addAttribute("fontcolor", "FFFFFF");
						weatherXML.addAttribute("fontname", "simhei");
						weatherXML.addAttribute("fontsize ", "18");
//					}
				}
			}

			/*------------------- Surface begin -----------------------*/
			// Surface 3级
			for (PlayListItem item : playListItems) {
				Element surfaceXML = screenXML.addElement("surface");
				SubTemplate subTemplate = (SubTemplate) subTemplateDao.findSubTemplate(item.getSubtemplateid());
				if (subTemplate == null)
					continue;
				if ("time".equals(subTemplate.getType()) || "weather".equals(subTemplate.getType())) {
					surfaceXML.addAttribute("type", subTemplate.getType());
				} else {
					surfaceXML.addAttribute("type", "mediaplay");
				}
				surfaceXML.addAttribute("id", subTemplate.getSubtemplateid());
				surfaceXML.addAttribute("name", subTemplate.getDescription());
				surfaceXML.addAttribute("level", subTemplate.getZlevel());

				String[] position = subTemplate.getPosition().split(",");
				if (position.length == 4) {
					surfaceXML.addAttribute("x", position[0]);
					surfaceXML.addAttribute("y", position[1]);
					surfaceXML.addAttribute("width", position[2]);
					surfaceXML.addAttribute("height", position[3]);
				}
				surfaceXML.addAttribute("alpha", subTemplate.getAlpha());
				surfaceXML.addAttribute("hide", subTemplate.getHide());

				String subTemType = subTemplate.getType();

				/********************** 对不同surface的处理 begin ************************/
				// 对不同类型的surface的处理，目前对time、weather和mediaplay做了处理
				String playSeq = item.getPlayseq();
				if (playSeq == null) {
					log.info("Playlistitemid:" + item.getPlaylistitemid() + " not have program");
					continue;
				}
				for (String seq : playSeq.split(",")) {
					/*-------------------- program begin---------------------*/
					// Program 4级(CLOB)
					// 0000-dcb363d7d38e48abb3b132c8324cc748-1-0100,0200-800c7f93fa154021b69de149d9c6f773-1-2400
					String[] programInfo = seq.split("-");

					Program program = null;
					List<ProgramItem> items = null;
					program = (Program) programDao.findProgramById(programInfo[1]);
					if (program == null) {
						log.info("Cann't find programid:" + programInfo[1] + " in Program Table");
						continue;
					}

					items = programItemDao.findItemsByProgramId(program.getProgramid());
					if (items == null || items.size() == 0) {
						log.info("Cann't find programid:" + program.getProgramid() + " in ProgramItem Table");
						continue;
					}

					if ("time".equals(subTemType)) {
						// 如果模板子项里面有time这个类型type，则进行下面的操作
						Element timeXML = surfaceXML.addElement("time");
						timeXML.addAttribute("fontcolor", items.get(0).getFgcolor());//"FFFFFF"
						timeXML.addAttribute("fontname", items.get(0).getFontname());//"simhei"
						timeXML.addAttribute("fontsize", items.get(0).getFontsize());//"36"
						timeXML.addAttribute("format", items.get(0).getTimeformat());

					} else if ("weather".equals(subTemType)) {
						// 如果模板子项里面有weather这个类型type，则进行下面的操作
						Element weatherXML = surfaceXML.addElement("weather");
						weatherXML.addAttribute("fontcolor", items.get(0).getFgcolor());
						weatherXML.addAttribute("fontname", items.get(0).getFontname());
						weatherXML.addAttribute("fontsize ", items.get(0).getFontsize());

					} else if ("realtimedata".equals(subTemType)) {
						// 如果模板子项里面有weather这个类型type，则进行下面的操作
						Element realtimedataXML = surfaceXML.addElement("realtimedata");
						realtimedataXML.addAttribute("fontcolor", items.get(0).getFgcolor());
						realtimedataXML.addAttribute("fontname", items.get(0).getFontname());
						realtimedataXML.addAttribute("fontsize ", items.get(0).getFontsize());

					} else if ("led".equals(subTemType)) {
						// LED暂时不支持
					} else {
						// 对mediaplay的处理
						Element programXML = surfaceXML.addElement("program");
						programXML.addAttribute("id", program.getProgramid());
						programXML.addAttribute("name", program.getName());
						//programXML.addAttribute("playtime", this.formatPlaytime(programInfo[0], programInfo[3]));北京移动不需要该字段
						// Content 衍生类 5级
						ContentType type = this.parseType(program.getType());
						if (type == null) {
							log.info("Undefined programe type:" + program.getType());
							continue;
						}
						switch (type) {
						case VIDEO:
							programXML.addAttribute("type", "video");
							for (ProgramItem pitem : items) {
								Content content = (Content) contentDao.find(Content.class, pitem.getContentid());

								if (content != null) {
									Element videoXml = null;
									String filePath = content.getPath();
									if("video".equalsIgnoreCase(content.getType())){
										videoXml = programXML.addElement("video");
										//增加对终端不支持ts之外视频时的特殊处理
										if(MainConfig.getInstance().isVideoConvert()){
											if (!filePath.endsWith("ts")){
												filePath = filePath.substring(0, filePath.lastIndexOf('.')) + ".ts";
												File file = new File(MainConfig.getInstance().getWebPath() + File.separator
														+ filePath);
												if(!file.exists()){
													filePath = content.getPath();
												}
											}
										}
									}else{
										videoXml = programXML.addElement("image");
									}
									//Element videoXml = programXML.addElement("video");
									videoXml.addAttribute("id", content.getContentid());
									videoXml.addAttribute("md5", content.getMd5());
									
									String filename = getFileName(filePath);
									videoXml.addAttribute("filename", filename);
									videoXml.addAttribute("timecount", pitem.getTimelength() + "");

									// 把内容放入发送到终端的队列
									FileXML filexml = new FileXML();
									filexml.setId(content.getContentid());
									filexml.setType(content.getType());
									filexml.setMd5(content.getMd5());
									filexml.setName(content.getContenttitle());
									filexml.setHref(filePath);

									fileMap.put(filexml.getId(), filexml);
								}
							}
							break;
						case PIC:
							programXML.addAttribute("type", "image");
							for (ProgramItem pitem : items) {

								Content content = (Content) contentDao.find(Content.class, pitem.getContentid());
								if (content != null) {
									Element imageXML = programXML.addElement("image");
									imageXML.addAttribute("id", content.getContentid());
									imageXML.addAttribute("md5", content.getMd5());
									String filePath = content.getPath();
									String filename = getFileName(filePath);
									imageXML.addAttribute("filename", filename);
									imageXML.addAttribute("timecount", pitem.getTimelength() + "");
									imageXML.addAttribute("showmode", pitem.getShowmode());

									// 把内容放入发送到终端的队列
									FileXML filexml = new FileXML();
									filexml.setId(content.getContentid());
									filexml.setType("image");
									filexml.setMd5(content.getMd5());
									filexml.setName(content.getContenttitle());
									filexml.setHref(filePath);

									fileMap.put(filexml.getId(), filexml);
								}
							}
							break;
						case AUDIO:
							programXML.addAttribute("type", "audio");
							for (ProgramItem pitem : items) {
								Content content = (Content) contentDao.find(Content.class, pitem.getContentid());
								if (content != null) {
									Element audioXML = programXML.addElement("audio");
									audioXML.addAttribute("id", content.getContentid());
									audioXML.addAttribute("md5", content.getMd5());
									String filePath = content.getPath();
									String filename = getFileName(filePath);
									audioXML.addAttribute("filename", filename);
									audioXML.addAttribute("timecount", pitem.getTimelength() + "");

									// 把内容放入发送到终端的队列
									FileXML filexml = new FileXML();
									filexml.setId(content.getContentid());
									filexml.setType(content.getType());
									filexml.setMd5(content.getMd5());
									filexml.setName(content.getContenttitle());
									filexml.setHref(filePath);

									fileMap.put(filexml.getId(), filexml);
								}

							}
							break;
						case TEXT:
							programXML.addAttribute("type", "text");
							for (ProgramItem programItem : items) {
								Content content = (Content) contentDao.find(Content.class, programItem.getContentid());
								if (content != null) {
									Element textXML = programXML.addElement("text");
									textXML.addAttribute("id", content.getContentid());
									textXML.addAttribute("md5", content.getMd5());
									String filePath = content.getPath();
									String filename = getFileName(filePath);
									textXML.addAttribute("filename", filename);
									textXML.addAttribute("timecount", programItem.getTimelength() + "");
									textXML.addAttribute("bgcolor", programItem.getBgcolor());
									textXML.addAttribute("fgcolor", programItem.getFgcolor());
									textXML.addAttribute("fontsize", programItem.getFontsize());
									textXML.addAttribute("fontname", programItem.getFontname());
									textXML.addAttribute("showmode", programItem.getShowmode());
									textXML.addAttribute("showspeed", programItem.getShowspeed());

									// 把内容放入发送到终端的队列
									FileXML filexml = new FileXML();
									filexml.setId(content.getContentid());
									filexml.setType(content.getType());
									filexml.setMd5(content.getMd5());
									filexml.setName(content.getContenttitle());
									filexml.setHref(filePath);

									fileMap.put(filexml.getId(), filexml);
								}
							}
							break;
						} // switch end ...
					}// end else
					/*-------------------- program end---------------------*/
				} // for end ...

				/********************** 对不同surface的处理 end ************************/
			}
			/*-------------------- Surface end ----------------------*/

			String playlistxml = XMLUtil.formatXML(playlistelement.getDocument());
			log.info("playlistXML" + playlistxml);

			// playlist.xml的名字以任务的名字命名
			String fileName = "P_" + playListid + ".xml";
			String subPath = File.separator + "playlist" + File.separator + TimeUtil.getDate() + File.separator
					+ groupid + File.separator;
			String playListXMLPath = FileUtil.saveFile(MainConfig.getInstance().getWebPath() + subPath, fileName,
					playlistxml);

			// 放入终端队列
			PlayListXML listXML = new PlayListXML();
			listXML.setId(playListid);
			listXML.setMd5(MD5Util.FileMD5(playListXMLPath));
			listXML.setName(fileName);
			listXML.setHref(subPath + fileName);
			listXML.setFiles(fileMap);

			// for resource xml to add playlistset
			playListSet.add(listXML);
		}

		/********************** Screen end **************************/

		if (playListSet.size() > 0) {
			/***************** resource xml begin *******************/
			resourceXML.setPlaylists(new ArrayList<PlayListXML>(playListSet));

			String xml = this.parseResourceXML(resourceXML, serialno);

			log.info("resourceXML == " + xml);

			// 将xml报文保存成文件
			String fileName = "R_" + serialno + ".xml";
			String subPath = File.separator + "playlist" + File.separator + TimeUtil.getDate() + File.separator
					+ groupid + File.separator;
			path = FileUtil.saveFile(MainConfig.getInstance().getWebPath() + subPath, fileName, xml);

			// 放入内存
			//TaskInfoCache.setResourceXMLPath(key, path);

			/***************** resource xml end *******************/
		} else {
			log.info("No data to generate resourceXML");
		}

		return path;
	}

	public String validation(String groupIds, String playlistid, String startDate, String endDate,String priority) {

		PlayList playList = (PlayList) playListDao.find(PlayList.class, playlistid);

		for (String groupid : groupIds.split(",")) {

			/* 判断日期 */
			List<PlayListGroup> playListGroups = playListGroupDao.findBetweenDate(groupid, startDate, endDate);

			if (playListGroups.isEmpty()) {
				// 日期没有冲突
				continue;
			}
			// 日期有冲突
			int starttime = Integer.valueOf(playList.getStarttime());
			int endtime = Integer.valueOf(playList.getEndtime());
			for (PlayListGroup playListGroup : playListGroups) {

				PlayList pList = (PlayList) playListDao.find(PlayList.class, playListGroup.getPlaylistid());
				int stime = Integer.valueOf(pList.getStarttime());
				int etime = Integer.valueOf(pList.getEndtime());

				if (stime > endtime || etime < starttime) {
					// do nothing ...
				} else {
					// 时间冲突，进行优先级判断
					if(Integer.parseInt(priority) < playListGroup.getPriority()){
						//待发布的优先级高
						continue;
					}else if(Integer.parseInt(priority) == playListGroup.getPriority()){
						//优先级相等，询问是否覆盖
						return "与群组【" + groupid + "】在时间【" + starttime + "~" + endtime + "】发生冲突，是否覆盖？";
					}else{
						//优先级低，直接拒绝
						return "与群组【" + groupid + "】在时间【" + starttime + "~" + endtime + "】发生冲突。";
					}
				}
			}
		}

		return "true|ok";
	}

	@SuppressWarnings("rawtypes")
	private String parseResourceXML(ResourceXML resourceXML,String taskid) {

		if (resourceXML != null) {
			Document document = DocumentHelper.createDocument();
			Element resource = document.addElement("resource");
			resource.addElement("taskid").setText(taskid);

			for (PlayListXML playListXML : resourceXML.getPlaylists()) {
				Element playlist = resource.addElement("playlist");

				playlist.addAttribute("id", playListXML.getId());
				playlist.addAttribute("name", playListXML.getName());
				playlist.addAttribute("href", playListXML.getHref());
				playlist.addAttribute("md5", playListXML.getMd5());

				Map<String, FileXML> files = playListXML.getFiles();
				if (files != null && files.size() > 0) {
					Iterator iter = files.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry element = (Map.Entry) iter.next();
						FileXML fileXML = (FileXML) element.getValue();
						Element file = playlist.addElement("file");

						file.addAttribute("id", fileXML.getId());
						file.addAttribute("type", fileXML.getType());
						file.addAttribute("name", fileXML.getName());
						file.addAttribute("md5", fileXML.getMd5());
						file.addAttribute("href", fileXML.getHref());
					}
				}

				Element downloadserver = resource.addElement("downloadserver");
				downloadserver.addElement("url").addText(MainConfig.getInstance().getUrlPath() + File.separator);

			}

			return XMLUtil.formatXML(document);
		}
		return null;
	}

	/**
	 * 解析类型，返回枚举
	 * 
	 * @param type
	 * @return
	 */
	private ContentType parseType(String type) {
		for (ContentType contentType : ContentType.values()) {
			if (contentType.getType().equalsIgnoreCase(type)) {
				return contentType;
			}
		}
		return null;
	}

	/**
	 * 格式化时间
	 * 
	 * @param starttime
	 *            0090
	 * @param endtime
	 *            0220
	 * @return 00:09-02:20
	 */
	private String formatPlaytime(String starttime, String endtime) {
		String _starttime = starttime.substring(0, 2) + ":" + starttime.substring(2, 4);
		String _endtime = endtime.substring(0, 2) + ":" + endtime.substring(2, 4);
		return _starttime + "-" + _endtime;
	}

	public enum ContentType {
		VIDEO("video"), PIC("pic"), AUDIO("audio"), TEXT("text");

		private String type;

		private ContentType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	public void saveConfigTaskItem(String terminalId, String taskIds) {

//		TermConfigTaskItem getItem = termConfigTaskItemDao.findConfigTaskBy2Tid(terminalId, taskIds);
//		// if exist change staus to create status 0
//		if (getItem != null) {
//			getItem.setStatus("0");
//			termConfigTaskItemDao.modify(getItem);
//		} else {
		termConfigTaskItemDao.deleteTermConfigTaskItem(terminalId);
		
		TermConfigTaskItem item = new TermConfigTaskItem();
		item.setCreatetime(TimeUtil.getTime());
		item.setSerialno(NumberUtil.generatorId());
		item.setStatus("0");
		item.setTermconfigid(taskIds);
		item.setTerminalid(terminalId);
		termConfigTaskItemDao.add(item);
//		}

	}

	public void saveSoftUpdateTaskItem(String terminalId, String taskIds) {

//		SoftInfoTaskItem getItem = softInfoTaskItemDao.findSoftTaskBy2Tid(terminalId, taskIds);
//		// if exist change status to create status 0
//		if (getItem != null) {
//			getItem.setStatus("0");
//			softInfoTaskItemDao.modify(getItem);
//		} else {
		//判断数据库中有没有记录，若有记录则先删除，之后再添加新的任务，防止冗余数据
		softInfoTaskItemDao.deleteSoftInfoTaskItem(terminalId);
		
		SoftInfoTaskItem item = new SoftInfoTaskItem();
		item.setCreatetime(TimeUtil.getTime());
		item.setSerialno(NumberUtil.generatorId());
		item.setStatus("0");
		item.setSoftinfoid(taskIds);
		item.setTerminalid(terminalId);

		SoftInfo softInfo = (SoftInfo) softInfoDao.find(SoftInfo.class, taskIds);
		item.setVersion(softInfo.getVersion());

		softInfoTaskItemDao.add(item);
		
//		}

	}

	public void saveWeatherTaskItem(String terminalId, String taskIds) {

		weatherTaskItemDao.deleteItem(terminalId);

		WeatherTaskItem item = new WeatherTaskItem();
		item.setCreatetime(TimeUtil.getTime());
		item.setSerialno(NumberUtil.generatorId());
		item.setStatus("0");
		item.setTerminalid(terminalId);
		item.setWeatherid(taskIds);

		weatherTaskItemDao.add(item);

	}

	public void savePlayListTaskItem(String terminalId, String taskIds, String xmlPath) {
		//此处taskIds为PlayListGroup里的serialno
		List<PlayListTaskItem> getItems = playListTaskItemDao.findItemsByTidPid(terminalId, taskIds);

		PlayListTaskItem getItem = null;

		if (getItems != null && getItems.size() > 0) {
			getItem = getItems.get(0);
		}

		if (getItem != null) {
			getItem.setStatus("0");
			getItem.setMd5(TaskInfoCache.getResourceXMLPathMD5(xmlPath));
			getItem.setCreatetime(TimeUtil.getTime());
			getItem.setUrl(generateURL(xmlPath));
			getItem.setCsize(TaskInfoCache.getResourceXMLPathSize(xmlPath));

			playListTaskItemDao.modify(getItem);
		} else {
			getItem = new PlayListTaskItem();
			getItem.setSerialno(NumberUtil.generatorId());
			getItem.setTerminalid(terminalId);
			getItem.setPlaylistid(taskIds);
			getItem.setStatus("0");
			getItem.setMd5(TaskInfoCache.getResourceXMLPathMD5(xmlPath));
			getItem.setCreatetime(TimeUtil.getTime());
			getItem.setUrl(generateURL(xmlPath));
			getItem.setCsize(TaskInfoCache.getResourceXMLPathSize(xmlPath));

			playListTaskItemDao.add(getItem);
		}

	}
	
	public void updatePlayListTaskItem(String terminalId, String taskIds, String status) {
		playListTaskItemDao.updatePlayListTaskItem(terminalId, taskIds, status);
	}

	public void saveLogFileUpLoad(String terminalId, String taskIds) {

		UploadLogReport getUpload = uploadLogReportDao.findUploadLog(terminalId, taskIds);

		if (getUpload != null) {
			getUpload.setStatus("0");
			uploadLogReportDao.modify(getUpload);
		} else {
			UploadLogReport log = new UploadLogReport();
			log.setSerialno(NumberUtil.generatorId());
			log.setCreatetime(TimeUtil.getTime());
			log.setLogtype(taskIds.toLowerCase());
			log.setStatus("0");
			log.setTaskid(NumberUtil.generatorId());
			log.setTerminalid(terminalId);
			uploadLogReportDao.add(log);
		}

	}

	public void saveTermPlaying(String terminalId, String taskIds) {
		try {
			termPlayingDao.delTermPlaying(terminalId);

			TermPlaying termPlaying = new TermPlaying();
			termPlaying.setSerialno(NumberUtil.generatorId());
			termPlaying.setTerminalid(terminalId);
			termPlaying.setTaskid(taskIds);
//			termPlaying.setPlaylistid(taskIds);
			termPlaying.setCreatetime(TimeUtil.getTime());
			termPlaying.setStatus("0");
			termPlayingDao.add(termPlaying);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

	}

	public void saveTermWorking(String terminalId, String taskIds) {
		try {
			workStatusDao.delWorkStatus(terminalId);

			TermWorking termWorking = new TermWorking();
			termWorking.setSerialno(NumberUtil.generatorId());
			termWorking.setTerminalid(terminalId);
			termWorking.setTaskid(taskIds);
			termWorking.setCreatetime(TimeUtil.getTime());
			termWorking.setStatus("0");
			workStatusDao.add(termWorking);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

	}
	
	public PlayListGroup getPlaylistidBySerialno(String serialno) {
		try {
			return playListGroupDao.getPlayListGroupBySerialno(serialno);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	private String getFileName(String filePath) {
		if (filePath != null)
			return filePath.substring(filePath.lastIndexOf("/") + 1);
		else
			return null;
	}

	/**
	 * 通过文件路径，返回文件下载的URL。
	 * 
	 * @param filePath
	 * @return
	 */
	private String generateURL(String filePath) {
		if (filePath.startsWith(MainConfig.getInstance().getWebPath())) {
			String subPath = filePath.substring(MainConfig.getInstance().getWebPath().length());
			return MainConfig.getInstance().getUrlPath() + File.separator + subPath;
		} else {
			return filePath;
		}
	}
}
