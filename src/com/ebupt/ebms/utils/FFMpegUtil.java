package com.ebupt.ebms.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.ebupt.ebms.conf.MainConfig;

public class FFMpegUtil {

	private static final Logger log = Logger.getLogger(FFMpegUtil.class);

	public static String getVideoThumb(String name, String size) {
		if (size == null)
			size = "530x400";
		String pername = name.substring(0, name.indexOf('.'));
		String videoBasePath = MainConfig.getInstance().getWebPath() + "/content/videos/";
		String videoPath = videoBasePath + name;
		String picPath = videoBasePath + "thumb/" + pername + ".jpg";
		String cmd = "ffmpeg -i " + videoPath + " -ss 7 -y -f image2 -t 0.001 -s " + size + " " + picPath;
		log.info("getVideoThumb_cmd=" + cmd);

		try {
			Process proc = Runtime.getRuntime().exec(cmd, null, null);
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}

		// 判断截图是否存在，不存在截取flv
		File file = new File(picPath);
		if (!file.exists()) {
			videoPath = videoBasePath + pername + ".flv";
			File videoFile = new File(videoPath);
			if (!videoFile.exists()) {
				FileUtil.copyFile(videoBasePath + "thumb/video.png", picPath);
			} else {
				cmd = "ffmpeg -i " + videoPath + " -ss 7 -y -f image2 -t 0.001 -s " + size + " " + picPath;
				log.info("getVideoThumb_cmd=" + cmd);

				try {
					Process proc = Runtime.getRuntime().exec(cmd, null, null);
					proc.waitFor();
				} catch (IOException e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				} catch (InterruptedException e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				}
				file = new File(picPath);
				if (!file.exists()) {
					FileUtil.copyFile(videoBasePath + "thumb/video.png", picPath);
				}
			}

		}
		return picPath;
	}

	/**
	 * @param name
	 *            要获取的图片视频路径，如content/videos/fede355d5d4b25f88c76469d522810ea.
	 *            wmv
	 * @param size
	 *            缩略图大小 300x200
	 * @param time
	 *            获取时间点(秒)
	 * @return
	 */
	public static String getVideoThumb(String name, String size, String time) {
		if (size == null)
			size = "530x400";
		if (time == null)
			return null;
		String pername = name.substring(name.lastIndexOf("/") + 1, name.indexOf('.'));
		String videoBasePath = MainConfig.getInstance().getWebPath();
		String videoPath = videoBasePath + File.separator + name;
		String picPath = videoBasePath + "/content/videos/thumb/" + pername + ".jpg";
		if ("0".equals(time)) {
			time = "2";
		}
		File file = new File(picPath);
		if (file.exists()) {
			FileUtil.deleteFile(picPath);
		}
		
		String cmd = "ffmpeg -i " + videoPath + " -ss " + time + " -y -f image2 -t 0.001 -s " + size + " " + picPath;
		log.info("getVideoThumb_cmd=" + cmd);

		try {
			Process proc = Runtime.getRuntime().exec(cmd, null, null);
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}

		// 判断截图是否存在，不存在截取flv
		file = new File(picPath);
		if (!file.exists()) {
			videoPath = videoBasePath + "/content/videos/" + pername + ".flv";
			File videoFile = new File(videoPath);
			if (!videoFile.exists()) {
				log.info(videoPath + "not exist");
				return null;
			} else {
				cmd = "ffmpeg -i " + videoPath + " -ss " + time + " -y -f image2 -t 0.001 -s " + size + " " + picPath;
				log.info("getVideoThumb_cmd=" + cmd);

				try {
					Process proc = Runtime.getRuntime().exec(cmd, null, null);
					proc.waitFor();
				} catch (IOException e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				} catch (InterruptedException e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				}
				file = new File(picPath);
				if (!file.exists()) {
					return null;
				}
			}

		}
		return picPath;

	}

	public static String convertVideoFormat(String name, String size) {
		if (size == null)
			size = "530x400";
		String pername = name.substring(0, name.indexOf('.'));
		String videoBasePath = MainConfig.getInstance().getWebPath() + "/content/videos/";
		String videoPath = videoBasePath + name;
		String changedPath = videoBasePath + pername + ".flv";
		String cmd = "ffmpeg -i " + videoPath + " -ab 56 -ar 22050 -b 500 -r 15 -s " + size + " " + changedPath;
		log.info("convertVideoFormat_begin: " + cmd);
		long starttime = System.currentTimeMillis();
		try {
			Process proc = Runtime.getRuntime().exec(cmd, null, null);
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		long endtime = System.currentTimeMillis();
		log.debug("convertVideoFormat_end,interval=" + (endtime - starttime) / 1000 + "s");
		return changedPath;
	}

	public static String convertVideoToTS(String name) {
		String pername = name.substring(0, name.indexOf('.'));
		String videoBasePath = MainConfig.getInstance().getWebPath() + "/content/videos/";
		String videoPath = videoBasePath + name;
		String changedPath = videoBasePath + pername + ".ts";
		String cmd = "ffmpeg -i " + videoPath + " -f mpegts -acodec libmp3lame -sameq " + changedPath;
		log.info("convertVideoToTS_begin: " + cmd);
		long starttime = System.currentTimeMillis();
		try {
			Process proc = Runtime.getRuntime().exec(cmd, null, null);
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		long endtime = System.currentTimeMillis();
		log.info("convertVideoToTS_end,interval=" + (endtime - starttime) / 1000 + "s");
		return changedPath;
	}

	public static String getVideoLength(String name) {
		String videoBasePath = MainConfig.getInstance().getWebPath() + "/content/videos/";
		String videoPath = videoBasePath + name;
		String cmd = "ffmpeg -i " + videoPath;
		log.info("getVideoLength_cmd: " + cmd);
		int second = 5;
		try {
			Process proc = Runtime.getRuntime().exec(cmd, null, null);
			// BufferedReader stdout = new BufferedReader(new
			// InputStreamReader(proc.getInputStream()));
			BufferedReader stdout = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String line = null;
			String time = "";
			while ((line = stdout.readLine()) != null) {
				System.out.println(line);
				if (line.contains("Duration")) {
					time = line.substring(line.indexOf(":") + 1, line.indexOf(","));
					if (time == null || "".equals(time))
						return second + "";
					time = time.trim();
					String[] t = time.split(":");
					if (t != null && t.length == 3) {
						second = (int) (Integer.parseInt(t[0]) * 3600 + Integer.parseInt(t[1]) * 60 + Float
								.parseFloat(t[2]));
					}

				}
			}
			proc.waitFor();
			stdout.close();
			log.info("name=" + name + "	timeLength=" + second);

		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			return second + "";
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			return second + "";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			return second + "";
		}
		return second + "";
	}

}
