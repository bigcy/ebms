package com.ebupt.ebms.servlet.sso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.utils.ConvertUtil;

/**
 * @author ZhangQian Create on 2012-1-11
 * @version 1.0
 */
@WebServlet("/sso")
public class SsoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SsoServlet.class);

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		String ltpaToken = null;
		String smsession = null;
		String kmUserId = null;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				log.info(cookies[i].getName() + " = " + cookies[i].getValue());
				// ��Cookie�л�ȡLtpaToken
				if ("LtpaToken".equals(cookies[i].getName())) {
					ltpaToken = cookies[i].getValue();
				} else if ("SMSESSION".equals(cookies[i].getName())) {
					smsession = cookies[i].getValue();
				}
			}
		} else {
			log.error("Cookie is null!");
			return;
		}

		//����LTPA��SSO
		if (ltpaToken != null && ltpaToken.length() > 0) {
			if (!ltpaToken.endsWith("==")) {
				ltpaToken += "==";
			}
			try {
				// ���ӳ�ʱʱ��
				System.setProperty("sun.net.client.defaultConnectTimeout", "20000");
				// ��ȡ��ʱʱ��
				System.setProperty("sun.net.client.defaultReadTimeout", "20000");

				// ȡSSO Ӧ�ñ�����userToken��ҳ���ַ
				URL url = new URL(MainConfig.getInstance().getLtpasso());

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDefaultUseCaches(false);
				conn.setUseCaches(false);
				conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
				// ʹ��LtpaTokenΪcookie
				conn.setRequestProperty("Cookie", "LtpaToken=" + ltpaToken + "; path=/; domain=.bmcc.com.cn");
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				String line = reader.readLine();
				reader.close();
				conn.disconnect();
				// �����û�id
				if (line != null && line.length() > 0 && line.indexOf("SSO_KM_USERID") != -1) {
					log.info("SsoResult:" + line);
					kmUserId = line.substring("SSO_KM_USERID".length() + 1);
					if (kmUserId.toLowerCase().equals("null")) {
						log.error("userId return null!");
						return;
					}
				} else {
					log.error("wrong token format file!");
					return;
				}

			} catch (MalformedURLException e) {
				log.error("LtpaToken url error!");
			} catch (IOException e) {
				log.error("Read LTPA Token error!");

			}

			if (kmUserId != null && kmUserId.length() > 0) {
				// ��õ�SSO�û�ID������ҵ����������
				log.info("kmUserId:" + kmUserId);
				
				//�籾webӦ�õ�ҳ����ת��ʽ
				kmUserId = ConvertUtil.base64Encode(kmUserId.getBytes());
				resp.sendRedirect(MainConfig.getInstance().getSsourl() + "?userid=" + kmUserId);
				
				return;
			}
		}

		//����SiteMinder��SSO
		if (smsession != null && smsession.length() > 0) {
			if (!smsession.endsWith("==")) {
				smsession += "==";
			}
			try {
				// ���ӳ�ʱʱ��
				System.setProperty("sun.net.client.defaultConnectTimeout", "20000");
				// ��ȡ��ʱʱ��
				System.setProperty("sun.net.client.defaultReadTimeout", "20000");
				// ȡsiteminder������userToken��ҳ���ַ
				URL url = new URL(MainConfig.getInstance().getSitesso());

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDefaultUseCaches(false);
				conn.setUseCaches(false);
				conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
				// ʹ��SMSESSIONΪcookie
				conn.setRequestProperty("Cookie", "SMSESSION=" + smsession + "; path=/; domain=.bmcc.com.cn");
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				String line = reader.readLine();
				reader.close();
				conn.disconnect();
				// �����û�id
				if (line != null && line.length() > 0 && line.indexOf("SSO_KM_USERID") != -1) {
					log.info("SsoResult:" + line);
					kmUserId = line.substring("SSO_KM_USERID".length() + 1);
					if (kmUserId.toLowerCase().equals("null")) {
						log.error("userId return null!");
						return;
					}
				} else {
					log.error("wrong token format file!");
					return;
				}

			} catch (MalformedURLException e) {
				log.error("SMSESSION Token url error!");
			} catch (IOException e) {
				log.error("Read SMSESSION Token error!");
			}

			if (kmUserId != null && kmUserId.length() > 0) {
				// ��õ�SSO�û�ID������ҵ����������
				log.info("kmUserId:" + kmUserId);
				
				//�籾webӦ�õ�ҳ����ת��ʽ
				kmUserId = ConvertUtil.base64Encode(kmUserId.getBytes());
				resp.sendRedirect(MainConfig.getInstance().getSsourl() + "?userid=" + kmUserId);
				
				return;
			}
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
