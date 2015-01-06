package com.ebupt.ebms.service.task;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.cache.TermInfoCache;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.entity.TermPlaying;
import com.ebupt.ebms.service.report.TermPlayingService;
import com.ebupt.ebms.utils.ConvertUtil;
import com.ebupt.ebms.utils.FileUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author zhangqian Create time 2011-12-3
 * @version 1.0
 */
@Service
public class ContinueGetSnapshotService {

	@Autowired
	@Qualifier("termPlayingService")
	private TermPlayingService termPlayingService;

	private static final Logger log = Logger.getLogger(ContinueGetSnapshotService.class);

	private ConcurrentHashMap<String, Timer> cacheTimer = new ConcurrentHashMap<String, Timer>();

	private ConcurrentHashMap<String, GetSrceenTimerTask> cacheTask = new ConcurrentHashMap<String, GetSrceenTimerTask>();

	public String continueGetSnapshot(String terminalId, String flag) {
		if ("1".equals(flag)) {
			// 先根据状态位判断该终端有没有在截屏
			if (TermInfoCache.getSnapshotFlag(terminalId) != null
					&& "1".equals(TermInfoCache.getSnapshotFlag(terminalId))) {
				// 如果在截屏，重新启动定时器
				shutdown(terminalId);
			}
			TermInfoCache.setSnapshotFlag(terminalId, flag);
			// 开始截屏
			Timer timer = new Timer();
			GetSrceenTimerTask task = new GetSrceenTimerTask(terminalId);
			timer.schedule(task, new Date());
			cacheTimer.put(terminalId, timer);
			cacheTask.put(terminalId, task);

			return "true";

		} else {
			// 停止截屏
			shutdown(terminalId);
			return "true";
		}
	}

	private void shutdown(String terminalId) {
		// 置标记位
		TermInfoCache.setSnapshotFlag(terminalId, "0");

		if (cacheTimer.get(terminalId) != null) {
			cacheTimer.get(terminalId).cancel();
			cacheTimer.remove(terminalId);
		}
		if (cacheTask.get(terminalId) != null) {
			cacheTask.get(terminalId).shutdown();
			cacheTask.get(terminalId).cancel();
			cacheTask.remove(terminalId);
		}
		log.info("-------RemoveTimerTasking-------TermianlId=" + terminalId);
	}

	class GetSrceenTimerTask extends TimerTask {

		private boolean state = true;

		private String terminalId = null;

		private int interval = 0;

		public GetSrceenTimerTask(String terminalId) {
			this.terminalId = terminalId;
		}

		public void shutdown() {
			state = false;
		}

		@Override
		public void run() {
			log.info("-------GetSrceenTimerTask start-------");
			while (state) {
				try {
					log.info("-------GetSrceenTimerTasking-------TermianlId=" + terminalId + " Interval="
							+ (interval + 1));
					String snapshot = TermInfoCache.getSnapshot(terminalId);
					if (snapshot != null) {
						log.debug("TerminalId=" + terminalId + " snapshot=" + snapshot);
						dealSnapshot(snapshot, terminalId);
					}
					Thread.sleep(5000);
					interval++;
					if (interval >= 30) {
						ContinueGetSnapshotService.this.shutdown(terminalId);
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage(),e);
					try {
						Thread.sleep(10 * 1000);
					} catch (InterruptedException ee) {
						ee.printStackTrace();
					}
				}
			}
		}

		public void dealSnapshot(String snapshot, String terminalId) {

			String playlistid = null;
			String taskid = null;
			String time = null;
			String picture = null;

			Set<Content> hm = new HashSet<Content>();
			try {
				Document document = DocumentHelper.parseText(snapshot.trim());
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
						for (Iterator<?> iterInner = element.elementIterator(); iterInner.hasNext();) {
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
			// 获取播放任务id
			if (playlistid == null) {
				return;
			}
			// 获取抓屏的时间
			if (time == null) {
				return;
			}
			// picture和content都不存在的情况
			if (picture == null && hm.size() < 1) {
				return;
			}
			try {
				int hmsize = hm.size();

				if (picture != null && !"".equals(picture)) {

					/************************* picture *******************************/
					byte[] pic = ConvertUtil.base64Decode(picture);

					String path = MainConfig.getInstance().getWebPath() + "/snapshot/" + TimeUtil.getDate() + "/"
							+ terminalId + "/";

					String name = TimeUtil.getTime() + ".jpg";
					path = FileUtil.saveFile(path, name, pic);
					// 对picture重新赋值
					picture = "/snapshot/" + TimeUtil.getDate() + "/" + terminalId + "/" + name;

					log.info("Terminalid:" + terminalId + " new picture value is : " + picture);
					/************************** picture ******************************/
					List<TermPlaying> termPlayings = termPlayingService.getTermPlaying(terminalId);

					if (hmsize > 0) {
						// termPlayingService.del(terminalId, taskid);
						for (Content content : hm) {
							// 根据素材Id判断素材的类型，如果是视频类型的，则调用
							termPlayingService.compositePic(path, content.getContentid(), content.getPosition(),
									content.getDurtime());
							if (termPlayings != null && !termPlayings.isEmpty()) {
								termPlayingService.updatePicture(terminalId, picture, time);
							} else {
								termPlayingService.save(terminalId, taskid, playlistid, content.getContentid(),
										content.getDurtime(), content.getPosition(), picture, time, TimeUtil.getTime());
							}

						}
					} else {
						// termPlayingService.del(terminalId, taskid);
						if (termPlayings != null && !termPlayings.isEmpty()) {
							termPlayingService.updatePicture(terminalId, picture, time);
						} else {
							termPlayingService.save(terminalId, taskid, playlistid, null, null, null, picture, time,
									TimeUtil.getTime());
						}
					}// end if k>0

				} else {
					if (hmsize > 0) {
						termPlayingService.del(terminalId, taskid);
						for (Content content : hm) {
							termPlayingService.save(terminalId, taskid, playlistid, content.getContentid(),
									content.getDurtime(), content.getPosition(), null, time, TimeUtil.getTime());
						}
					} else {
						log.error("Terminalid:" + terminalId
								+ " PlayingReportServlet picture is null and content is null");
						return;
					}

				}// end if picture not null

			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}

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
}
