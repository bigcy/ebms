package com.ebupt.ebms.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * @author QiChen Create on 2011-3-18
 * @version 1.0
 */
public class XMLUtil {
	public static String formatXML(Document document) {
		// 创建输出格式
		OutputFormat format = OutputFormat.createPrettyPrint();

		// 指定输出xml的编码类型
		format.setEncoding("UTF-8");

		StringWriter writer = new StringWriter();
		// 创建一个文件输出流
		XMLWriter xmlwriter = new XMLWriter(writer, format);

		try {
			xmlwriter.write(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String returnString = writer.toString();
		try {
			writer.close();
			xmlwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnString;
	}
}
