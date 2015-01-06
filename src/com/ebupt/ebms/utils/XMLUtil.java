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
		// ���������ʽ
		OutputFormat format = OutputFormat.createPrettyPrint();

		// ָ�����xml�ı�������
		format.setEncoding("UTF-8");

		StringWriter writer = new StringWriter();
		// ����һ���ļ������
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
