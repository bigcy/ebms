package com.ebupt.ebms.service.report;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.dao.ContentDao;
import com.ebupt.ebms.dao.TermPlayingDao;
import com.ebupt.ebms.entity.Content;
import com.ebupt.ebms.entity.TermPlaying;
import com.ebupt.ebms.utils.FFMpegUtil;
import com.ebupt.ebms.utils.ImageMagicUtil;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author lishuhua Create time 2011-3-7
 * @version 1.0
 */
@Service
public class TermPlayingService {

	@Autowired
	@Qualifier("termPlayingDaoImpl")
	private TermPlayingDao termPlayingDao;

	@Autowired
	@Qualifier("contentDaoImpl")
	private ContentDao contentDao;

	private static final Logger log = Logger.getLogger(TermPlayingService.class);

	public void del(String terminalId, String taskid) {
		log.debug("------Enter into TermPlayingService del method------");
		termPlayingDao.delTermPlaying(terminalId, taskid);
	}

	public List<TermPlaying> getTermPlaying(String terminalId) {
		return termPlayingDao.findTermPlaying(terminalId);
	}

	public String save(String terminalId, String taskid, String playlistid, String contentid, String durtime,
			String position, String picture, String time, String createtime) {
		log.debug("------Enter into TermPlayingService save method------");
		try {
			TermPlaying playing = new TermPlaying();
			playing.setSerialno(NumberUtil.generatorId());
			playing.setTerminalid(terminalId);
			playing.setTaskid(taskid);
			playing.setPlaylistid(playlistid);
			if (contentid != null && !"".equals(contentid)) {
				playing.setContentid(contentid);
			}
			if (durtime != null && !"".equals(durtime)) {
				playing.setDurtime(durtime);
			}
			if (position != null && !"".equals(position)) {
				playing.setPosition(position);
			}
			if (picture != null && !"".equals(picture)) {
				playing.setPicture(picture);
			}
			playing.setTime(time);
			playing.setRecvtime(TimeUtil.getTime());
			playing.setCreatetime(createtime);
			playing.setStatus("1");
			termPlayingDao.add(playing);

			log.debug("TermPlayingService save success!");
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return "1";
	}

	public void updatePicture(String terminalId, String picture, String time) {
		if (terminalId != null && picture != null && time != null)
			termPlayingDao.updatePicture(terminalId, picture, time);
	}

	public TermPlaying findCreateTime(String terminalId, String taskid) {
		log.debug("------Enter into TermPlayingService findCreateTime method------");
		return termPlayingDao.findTermPlaying(terminalId, taskid);
	}

	public String compositePic(String path, String contentid, String position, String time) {
		Content content = contentDao.getContentType(contentid);
		if (content == null)
			return null;
		// 如果是video类型的，则调用叠加图的方法
		if ("video".equals(content.getType())) {
			if (position == null) {
				return null;
			} else {
				String[] pos = position.split(",");
				//将传递的尺寸参数装换成偶数20120829，
				//ffmpeg里面的size必须是偶数乘以偶数，否则会引起frame size for ffmpeg must be a multiple of 2这个错误！！！！
				int xsize = ((Integer.parseInt(pos[2]))/2)*2;
				int ysize = ((Integer.parseInt(pos[3]))/2)*2;
				String size = xsize + "x" + ysize;
				//这个是之前的
//				String size = pos[2] + "x" + pos[3];
				String thumbPath = FFMpegUtil.getVideoThumb(content.getPath(), size, time);
				if (thumbPath == null)
					return null;
				// video绝对路径
				return ImageMagicUtil.composite(path, thumbPath, path, position);
			}

		}
		return null;
	}

}
