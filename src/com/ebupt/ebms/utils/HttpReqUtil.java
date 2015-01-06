package com.ebupt.ebms.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.log4j.Logger;

/**
 * @author lishuhua 
 * Create time 2011-3-11
 * @version 1.0
 */
public class HttpReqUtil {

	private static final Logger log = Logger.getLogger(HttpReqUtil.class);

	public static String sendPost(String requestUrl,
			Map<String, String> requestParamsMap) {

		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(requestUrl);
		String sResponseBody = null;

		try {
			//对map中的header进行遍历
			for (String key : requestParamsMap.keySet()) {    
			       System.out.println("key=" + key + "  and  value=" + requestParamsMap.get(key));    
			       postMethod.addRequestHeader(key,requestParamsMap.get(key));
			    } 
				
			// 消息体用post方法实现封装
			//RequestBody和RequestEntity两个不能同时存在，同时存在接口不能同时得到Header和body体
			
			//软件升级的xml
//			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//					"<command><version>2.0</version></command>";
			//软件升级状态报告
//			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//			"<command><taskid>5f0c8ff4637a0b9bd12c022e2af1f2cf</taskid>" +
//			"<version>1.0</version><status>0007</status></command>";

			
			//工作状态的xml
//			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><command>"
//					+ "<playerstatus>2</playerstatus>"
//					+ "<exdevice><type>0001</type><status>5</status></exdevice>"
//					+ "<exdevice><type>0002</type><status>6</status></exdevice>"
//					+ "<exdevice><type>0003</type><status>7</status></exdevice>"
//					+ "<exdevice><type>0004</type><status>8</status></exdevice>"
//					+ "</command>";
			
			//终端配置上报的xml
//			String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><command><ip>10.1.1.111</ip><mac>a3:ef:bd:10:00:00</mac>" +
//					"<gateway>10.1.1.1</gateway><dns>10.1.1.1</dns><appversion>1.0</appversion><startuptime>10:00:00</startuptime>" +
//					"<shutdowntime>18:00:00</shutdowntime><diskspacealarm>8888</diskspacealarm><serverip>10.1.1.151</serverip>" +
//					"<serverport>38080</serverport><volume>50</volume><downloadrate>6666</downloadrate>  " +
//					"<downloadtime>00:00:00-08:00:00,18:00:00-24:00:00</downloadtime><selectinterval>10</selectinterval>" +
//					"<logserver> ftp://sw:sw@172.16.5.33:3100 </logserver><uploadlogtime> 18:00:00</uploadlogtime>" +
//					"<keeplogtime> 7 </keeplogtime><loglevel> info </loglevel><placementmodel>1</placementmodel>" +
//					"<exdevicetype> 0001 </exdevicetype><city>010</city></command>";
			
			//在播内容上报的xml
//			String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//					"<command>" +
//					"<playlistid>f6c1dfc763bf1344d00679511bf0cbbb</playlistid>" +
//					"<time>20110101120000</time>" +
//					"<content><contentid>f6c1dfc763bf1344d00679511bf0cbbc</contentid><durtime>1111</durtime></content>" +
//					"<content><contentid>dddddfc763bf1344d00679511bfddddd</contentid><durtime>2222</durtime></content>" +
////					"<content><contentid>dfc763bf1344d00679511bf0cbbcf6c1</contentid><durtime>3333</durtime></content>" +
//					"<content><contentid>bf1344d00679511bdddddfc763fddddd</contentid><durtime>55140</durtime></content>" +
//					"<picture>sfasdfjioajsdfaskldfjlsdfj</picture>" +
//					"</command>";
			
			//终端告警上报
//			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//			"<command><alarmcode>0001</alarmcode>" +
//			"<time>2011-3-17 12:26:45</time></command>";
			
			//日志上传完成报告
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<command><taskid>9393df8ba6764bc4ae784bdde1086a40</taskid><logtype>runlog</logtype>" +
			"<logname>temp_runlog_0771+000100100010_20110323</logname></command>";
			
			RequestEntity re = new StringRequestEntity(xml,"application/xml; charset=utf-8", null);

			postMethod.setRequestEntity(re);
			
			// 执行postMethod方法
			client.executeMethod(postMethod);
			int responseCode = postMethod.getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				sResponseBody = postMethod.getResponseBodyAsString();
				postMethod.getRequestEntity();
				log.info("Post Success!" + "sResponseBody:" + sResponseBody);
				log.info("Post Success!" + "sResponseentity:"
						+ postMethod.getRequestEntity());
			} else {
				log.info(" Error=" + responseCode);
			}
		} catch (HttpException he) {
			log.info("HTTP Problem: " + he.getMessage());
		} catch (IOException ioe) {
			System.out.println("IO Exeception: " + ioe.getMessage());
		} finally {
			postMethod.releaseConnection();
		}
		return sResponseBody;
	}

	public static String sendGet(String requestUrl,
			Map<String, String> requestParamsMap) {

		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(requestUrl);
		String sResponseBody = null;
		NameValuePair[] nameValuePairPairs = null;
		try {
			//对map中的header进行遍历
			for (String key : requestParamsMap.keySet()) {    
		         System.out.println("key=" + key + "  and  value=" + requestParamsMap.get(key));    
		         getMethod.addRequestHeader(key,requestParamsMap.get(key));
		    } 
			getMethod.addRequestHeader("Content-Type","text/html;charset=gb2312");

			String queryString = EncodingUtil.formUrlEncode(nameValuePairPairs,"gb2312");
			
			log.info("queryString:" + queryString);
			getMethod.setQueryString(queryString);
			
			// 执行getMethod方法
			client.executeMethod(getMethod);
			int responseCode = getMethod.getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				sResponseBody = getMethod.getResponseBodyAsString();
				log.info("Get Success!" + "sResponseBody:" + sResponseBody);
			} else {
				log.info(" Error=" + responseCode);
			}
		} catch (HttpException he) {
			log.info("HTTP Problem: " + he.getMessage());
		} catch (IOException ioe) {
			System.out.println("IO Exeception: " + ioe.getMessage());
		} finally {
			getMethod.releaseConnection();
		}

		return sResponseBody;
	}

	public static void main(String[] args) {
		String url = "http://10.1.1.151:8086/ebms/";
		Map<String, String> map = new HashMap<String, String>();
		map.put("x-device-id", "000100100010");
		map.put("command", "test");
//		map.put("alarmcode", "0002");
//		map.put("time", "20101207123030");
//		map.put("version", "1.0");
//		map.put("playlistid", "79511bf0cbbbf6c1dfc763bf1344d006");
//		map.put("contentid", "57cd953423fc0c1704c447fbc075d9ae");
//		map.put("status", "0003");
//		map.put("taskid", "5f0c8ff4637a0b9bd12c022e2af1f2cf");
//		map.put("version", "2.0");
//		map.put("status", "0001");
		sendPost(url, map);
	}
}
