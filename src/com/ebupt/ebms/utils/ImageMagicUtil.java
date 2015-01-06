package com.ebupt.ebms.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageMagicUtil {

	private static final Logger log = Logger.getLogger(ImageMagicUtil.class);

	/**
	 * @param srcPath原图片路径->背景图
	 * @param midPath要叠加的图片路径->前景图
	 * @param destPath目标图片路径
	 * @param position叠加位置坐标
	 * @return destPath -geometry 使用这样的语法： widthxheight{+-}x{+-}y -geometry
	 *         参数后面加的那个 `>' 表示设置 -geometry 的同时并不同时放大原来的图像
	 */
	public static String composite(String srcPath, String midPath, String destPath, String position) {

		if (position == null || "".equals(position))
			return srcPath;
		String[] pos = position.split(",");
		String geometry = pos[2] + "x" + pos[3] + "+" + pos[0] + "+" + pos[1] + ">";
		String cmd1 = "composite -geometry \"" + geometry + "\" " + midPath + " " + srcPath + " " + destPath;
		log.info("composite:" + cmd1);
		String cmd[] = { "/bin/sh", "-c", cmd1 };

		try {
			Process proc = Runtime.getRuntime().exec(cmd, null, null);
			proc.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			log.debug(e.getMessage());
		}

		// 判断截图是否存在，不存在给一张默认值
		File file = new File(destPath);
		if (!file.exists()) {
			return srcPath;
		}
		return destPath;
	}
	
	/**
	 * @param srcPath原图片路径->背景图
	 * @param midPath要叠加的图片路径->前景图
	 * @param destPath目标图片路径
	 * @param position叠加位置坐标
	 * @return destPath 
	 */
	public static String compositeJDK(String srcPath, String midPath, String destPath, String position) {
		if (position == null || "".equals(position))
			return srcPath;
		String[] pos = position.split(",");
		int x = Integer.parseInt(pos[0]);
		int y = Integer.parseInt(pos[1]);
		int w = Integer.parseInt(pos[2]);
		int h = Integer.parseInt(pos[3]);
		try {
			// 源文件
			File _srcfile = new File(srcPath);
			Image src = ImageIO.read(_srcfile);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			
			// 水印文件
			File _midfile = new File(midPath);
			Image mid = ImageIO.read(_midfile);
			//int wideth_biao = src_biao.getWidth(null);
			//int height_biao = src_biao.getHeight(null);
			g.drawImage(mid, x, y, w, h, null);
			g.dispose();
			
			// 目标文件
			FileOutputStream out = new FileOutputStream(destPath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destPath;
	}
	
	/**
	 * 按比例缩放图片
	 * 
	 * @param srcPath
	 * @param width
	 * @param height
	 * @return
	 */
	public static String convert(String srcPath, int width, int height) {

		String newPath = srcPath.substring(0, srcPath.lastIndexOf('/') + 1) + "resize/";
		File path = new File(newPath);
		if (!path.exists())
			path.mkdirs();
		newPath = newPath + srcPath.substring(srcPath.lastIndexOf('/') + 1);

		String cmd1 = "/usr/bin/convert -resize \"" + width + "x" + height + ">" + "\" " + srcPath + " " + newPath;
		String cmd[] = { "/bin/sh", "-c", cmd1 };

		log.info("imagemagic cmd:" + cmd1);

		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return newPath;
	}
	
	public static void main(String[] args) {
		compositeJDK("E:/untitled.png","E:/mid.jpg","E:/1.jpg","50,50,182,293");
	}

}
