package com.ebupt.ebms.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ebupt.ebms.cache.RegisterInfoCache;
import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.servlet.HTTPRespCode;
import com.ebupt.ebms.servlet.RegisterData;

/**
 * Servlet工具类
 * 
 * @author zhangqian 2010-1-16
 */
public class ServletUtil {

	private static final Logger log = Logger.getLogger(ServletUtil.class);

	/**
	 * 获取请求的消息体
	 * 
	 * @param request
	 * @return
	 */
	public static String parseRequestContent(HttpServletRequest request) {

		String content = null;
		String encode = request.getCharacterEncoding();

		if (request.getContentLength() > 0) {
			InputStream input = null;
			try {
				input = request.getInputStream();
				ByteBuffer bb = ByteBuffer.allocate(request.getContentLength());
				byte[] buf = new byte[1024];
				int size = 0;
				int totalSize = 0;
				while ((size = input.read(buf)) > 0) {
					totalSize += size;
					bb.put(buf, 0, size);
				}
				if (encode != null) {
					content = new String(bb.array(), encode);
				} else {
					content = new String(bb.array());
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return content;
	}

	public static void SetAuthFailed(String terminalId, int status, String content, HttpServletResponse response) {
		String nonce = MD5Util.randomUUID();
		String opaque = MD5Util.randomUUID();
		RegisterData data = new RegisterData();
		data.setNonce(nonce);
		data.setOpaque(opaque);

		RegisterInfoCache.setTerminalIdToRegisterData(terminalId, data);
		StringBuffer authenticate = new StringBuffer();
		authenticate.append("Digest ");
		authenticate.append("realm=" + ConvertUtil.addDoubleQuotationMarks("ebrealm@service.cmcc.cn"));
		authenticate.append(",qop=" + ConvertUtil.addDoubleQuotationMarks("auth"));
		authenticate.append(",nonce=" + ConvertUtil.addDoubleQuotationMarks(nonce));
		authenticate.append(",opaque=" + ConvertUtil.addDoubleQuotationMarks(opaque));
		response.addHeader("WWW-Authenticate", authenticate.toString());
		log.debug("WWW-Authenticate " + authenticate.toString());
		response.addHeader("servertime", TimeUtil.getTime());
		response.setStatus(status);

		try {
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void SetAuthFailed(String terminalId, HttpServletResponse resp, String type) {
		String errorInfo = null;
		Integer time = RegisterInfoCache.getAuthFailedTimes(terminalId);
		if (time >= 4) {
			errorInfo = "terminalId:" + terminalId + " auth " + time + " times ,refused!";
			SetAuthFailed(terminalId, HTTPRespCode.Refused.value(), errorInfo, resp);
			SQLCache.putTermOperLog(terminalId, "连续[" + time + "]次鉴权失败，平台返回403错误！");
		} else {
			RegisterInfoCache.setAuthFailedTimes(terminalId, time + 1);
			if ("register".equalsIgnoreCase(type)) {
				errorInfo = "terminalId:" + terminalId + " register failed ,times:" + time;
				SQLCache.putTermOperLog(terminalId, "注册失败，次数[" + time + "]");
			} else {
				errorInfo = "terminalId:" + terminalId + " auth failed ,times:" + time;
				SQLCache.putTermOperLog(terminalId, "鉴权失败，次数[" + time + "]");
			}
			SetAuthFailed(terminalId, HTTPRespCode.AuthFailed.value(), errorInfo, resp);
		}
		log.debug("terminalId: " + terminalId + " auth failed , times:" + time);
	}

	/**
	 * 获取BODY的XML
	 * 
	 * @param req
	 * @return String
	 */
	public static String parseRequsetXML(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		String encode = req.getCharacterEncoding();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
			String temp = br.readLine();
			while (temp != null) {
				sb.append(temp);
				temp = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			log.error("parseRequsetXML error : " + e.getMessage());
		}
		String result = sb.toString().trim();
		try {
			if (encode != null) {
				result = new String(result.getBytes(), encode);
			} else {
				result = new String(result.getBytes(), "utf-8");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 返回失败信息 0：成功|1：失败
	 * 
	 * @return String
	 */
	public static String returnXMLMessage(String result, String errorinfo) {
		Document document = DocumentHelper.createDocument();
		Element command = document.addElement("command");
		command.addElement("result").setText(result);
		if (errorinfo != null)
			command.addElement("errorinfo").setText(errorinfo);
		return XMLUtil.formatXML(document);
	}

}