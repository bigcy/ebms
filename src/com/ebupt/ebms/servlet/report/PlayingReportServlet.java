package com.ebupt.ebms.servlet.report;

import java.util.HashSet;
import java.util.Iterator;
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
import com.ebupt.ebms.entity.TermPlaying;
import com.ebupt.ebms.service.report.TermPlayingService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ConvertUtil;
import com.ebupt.ebms.utils.FileUtil;
import com.ebupt.ebms.utils.ServletUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author lishuhua Create time 2011-3-8
 * @version 1.0
 */
@WebServlet("/playingReport")
public class PlayingReportServlet extends AuthServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger
			.getLogger(PlayingReportServlet.class);

	@Override
	public String doAuthGetService(HttpServletRequest request,
			HttpServletResponse response, String terminalId) {

		log.debug("Terminalid:" + terminalId
				+ " enter into PlayingReportServlet");

		// 服务器处理结果,0 成功 ,1 失败
		String result = "1";
		String errorinfo = null;

		// 获取请求消息中的数据流
		String reqxml = ServletUtil.parseRequsetXML(request);
		log.info("Terminalid:" + terminalId + " reqxml:" + reqxml);
		String resxml = null;

		if (reqxml == null || "".equals(reqxml)) {
			log.error("Terminalid:" + terminalId
					+ " PlayingReportServlet reqxml==null");
			errorinfo = "Req xml in null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}

		String playlistid = null;
		String taskid = null;
		String time = null;
		String picture = null;

//		int count = 0;
		// 解析数据流中的xml信息
		Set<Content> hm = new HashSet<Content>();
		try {
			Document document = DocumentHelper.parseText(reqxml.trim());
			Element root = document.getRootElement();

			for (Iterator<?> iter = root.elementIterator(); iter.hasNext();) {
				Element element = (Element) iter.next();

				if (element.getName().equals("playlistid")) {
					playlistid = element.getTextTrim();
				}
				if (element.getName().equals("taskid")) {
					taskid = element.getTextTrim();
				}
				if (element.getName().equals("time")) {
					time = element.getTextTrim();
				}
				if (element.getName().equals("picture")) {
					picture = element.getTextTrim();
				}

				/*********************** 读取 **************************/
				if (element.getName().equals("content")) {
					Content content = new Content();
					for (Iterator<?> iterInner = element.elementIterator(); iterInner
							.hasNext();) {
						Element elementInner = (Element) iterInner.next();
						if (elementInner.getName().equals("contentid")) {
							content.setContentid(elementInner.getText());

						}
						if (elementInner.getName().equals("durtime")) {
							content.setDurtime(elementInner.getText());
						}
						if (elementInner.getName().equals("position")) {
							content.setPosition(elementInner.getText());
						}
					}
					hm.add(content);
				}

				/*********************** 结束 **************************/
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		// 获取任务id
		if (taskid == null) {
			log.error("Terminalid:" + terminalId
					+ " PlayingReportServlet taskid is null");
			errorinfo = "taskid is null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}

		// 获取播放任务id
		if (playlistid == null) {
			log.error("Terminalid:" + terminalId
					+ " PlayingReportServlet playlistid is null");
			errorinfo = "playlistid is null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}

		// 获取抓屏的时间
		if (time == null) {
			log.error("Terminalid:" + terminalId
					+ " PlayingReportServlet time is null");
			errorinfo = "time is null";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}

		// picture和content都不存在的情况
		if (picture == null && hm.size() < 1) {
			log.error("Terminalid:" + terminalId
					+ " PlayingReportServlet xml not complete");
			errorinfo = "xml not complete";
			resxml = ServletUtil.returnXMLMessage(result, errorinfo);
			return resxml;
		}
		SQLCache.putTermOperLog(terminalId, "上报当前正在播放的内容");
		try {
			int hmsize = hm.size();

			TermPlayingService termPlayingService = (TermPlayingService) BeanUtil
					.getBean(this.getServletContext(), "termPlayingService");
			TermPlaying termPlaying = termPlayingService.findCreateTime(
					terminalId, taskid);
			if (termPlaying != null) {
				String createTime = termPlaying.getCreatetime();
				if (picture != null && !"".equals(picture)) {

					/************************* picture *******************************/
					// excode picture
					// log.debug("Terminalid:" + terminalId + " received picture is : " + picture);
					byte[] pic = ConvertUtil.base64Decode(picture);

					// create file path defined :
					// webPath/snapshot/YYYYMMDD/terminalId/
					String path = MainConfig.getInstance().getWebPath()
							+ "/snapshot/" + TimeUtil.getDate() + "/"
							+ terminalId + "/";

					// file named by : yyyyMMddHHmmss.jpg
					String name = TimeUtil.getTime() + ".jpg";

					// create file in path , change value of picture to
					// path+name
					path = FileUtil.saveFile(path, name, pic);
					// 对picture重新赋值
					picture = "/snapshot/" + TimeUtil.getDate() + "/"
							+ terminalId + "/" + name;

					log.info("Terminalid:" + terminalId
							+ " new picture value is : " + picture);
					/************************** picture ******************************/
					if (hmsize > 0) {
						termPlayingService.del(terminalId, taskid);
						for (Content content : hm) {
							//根据素材Id判断素材的类型，如果是视频类型的，则调用
							termPlayingService.compositePic(path,content.getContentid(), content.getPosition(),content.getDurtime());
							
							result = termPlayingService.save(terminalId,
									taskid, playlistid, content.getContentid(),
									content.getDurtime(),
									content.getPosition(), picture, time,
									createTime);
						}
					} else {
						termPlayingService.del(terminalId, taskid);
						result = termPlayingService.save(terminalId, taskid,
								playlistid, null, null, null, picture, time,
								createTime);
					}// end if k>0

				} else {
					if (hmsize > 0) {
						termPlayingService.del(terminalId, taskid);
						for (Content content : hm) {
							result = termPlayingService.save(terminalId,
									taskid, playlistid, content.getContentid(),
									content.getDurtime(),
									content.getPosition(), null, time,
									createTime);
						}
					} else {
						log.error("Terminalid:"
								+ terminalId
								+ " PlayingReportServlet picture is null and content is null");
						errorinfo = "picture is null and content is null";
						resxml = ServletUtil
								.returnXMLMessage(result, errorinfo);
						return resxml;
					}

				}// end if picture not null

			} else {
				log.error("Terminalid:" + terminalId
						+ " database can not found the data needed");
				errorinfo = "database can not found terminal data of taskid :"
						+ taskid;
				resxml = ServletUtil.returnXMLMessage(result, errorinfo);
				return resxml;
			}// end if termPlayling not null

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		resxml = ServletUtil.returnXMLMessage(result, errorinfo);
		log.info("TerminalId: " + terminalId
				+ " PlayingReportServlet responseContent: " + resxml);

		// 返回处理的结果
		log.debug("Terminalid:" + terminalId
				+ " exit PlayingReportServlet, result is : " + result);
		return resxml;
	}

	class Content {
		private String contentid;
		private String durtime;
		private String position;

		public String getContentid() {
			return contentid;
		}

		public void setContentid(String contentid) {
			this.contentid = contentid;
		}

		public String getDurtime() {
			return durtime;
		}

		public void setDurtime(String durtime) {
			this.durtime = durtime;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

	}
}
