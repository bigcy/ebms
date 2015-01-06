package com.ebupt.ebms.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.cache.RegisterInfoCache.RegisterState;
import com.ebupt.ebms.service.task.RegisterService;
import com.ebupt.ebms.servlet.RegisterData;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ConvertUtil;
import com.ebupt.ebms.utils.ServletUtil;
import com.ebupt.ebms.utils.MD5Util;

/**
 * @author QiChen Create on 2011-3-7
 * @version 1.0
 */
public abstract class AuthServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AuthServlet.class);

	@Override
	public void doGetService(HttpServletRequest req, HttpServletResponse resp, String terminalId) {

		RegisterService registerService = (RegisterService) BeanUtil.getBean(this.getServletContext(),
				"registerService");

		RegisterState registerState = registerService.getSession(req, terminalId);

		String _username = null, _realm = null, _nonce = null, _uri = null, _qop = null, _nc = null, _cnonce = null, _response = null, _passwd = null;

		/** ******************鉴权******************** */
		if (registerState == RegisterState.DoesNotRegister) {
			// 如果用户没有注册 ， 则返回鉴权失败错误信息
			String errorInfo = "terminalId: " + terminalId + " has not registered...";
			log.debug(errorInfo);
			ServletUtil.SetAuthFailed(terminalId, HTTPRespCode.AuthFailed.value(), errorInfo, resp);
			return;
		}
		if (registerState == RegisterState.SessionTimeOut) {
			// 如果用户没有注册，则返回鉴权失败错误信息
			String errorInfo = "terminalId: " + terminalId + " registered session is timeout...";
			log.debug(errorInfo);
			ServletUtil.SetAuthFailed(terminalId, HTTPRespCode.SessionOut.value(), errorInfo, resp);
			return;
		}

		RegisterData _data = registerService.getRegisterDataByTerminalId(req, terminalId);

		// 鉴权结果判断
		boolean authResult = false;
		// 获取用户请求的authorization
		String authorization = req.getHeader("Authorization");
		if (authorization != null) {
			int index = authorization.indexOf(" ");
			if (index != -1) {
				String authType = authorization.substring(0, index);
				if (authType.equalsIgnoreCase("Digest")) {

					StringTokenizer st = new StringTokenizer(authorization.substring(index + 1), ",");

					HashMap<String, String> values = new HashMap<String, String>();

					while (st.hasMoreTokens()) {
						String token = st.nextToken();
						int subIndex = token.indexOf("=");
						if (subIndex != -1) {
							String key = token.substring(0, subIndex).trim();
							String value = token.substring(subIndex + 1).trim();
							values.put(key, value);
						}
					}

					_username = ConvertUtil.delDoubleQuotationMarks(values.get("username"));
					_realm = ConvertUtil.delDoubleQuotationMarks(values.get("realm"));
					_nonce = ConvertUtil.delDoubleQuotationMarks(values.get("nonce"));
					_uri = ConvertUtil.delDoubleQuotationMarks(values.get("uri"));
					_qop = ConvertUtil.delDoubleQuotationMarks(values.get("qop"));
					_nc = values.get("nc");
					_cnonce = ConvertUtil.delDoubleQuotationMarks(values.get("cnonce"));
					_response = ConvertUtil.delDoubleQuotationMarks(values.get("response"));

					// 取得厂商密码
					_passwd = registerService.getPasswdByTerminalId(terminalId);
					_passwd = MD5Util.MD5(terminalId + ":" + _passwd);
					log.debug("AuthServlet _passwd is :" + _passwd);

					if (_data != null && _data.getNonce().equalsIgnoreCase(_nonce)) {
						String A1 = _username + ":" + _realm + ":" + _passwd;
						String A2 = "post" + ":" + _uri;
						// String A2 = req.getMethod() + ":" + _uri;
						String H_A1 = MD5Util.MD5(A1);
						String H_A2 = MD5Util.MD5(A2);

						String rspauth = H_A1 + ":" + _data.getNonce() + ":" + _nc + ":" + _cnonce + ":" + _qop + ":"
								+ H_A2;
						rspauth = MD5Util.MD5(rspauth);

						log.debug("ReqMethod=" + req.getMethod());
						log.debug("A1=" + A1 + " H_A1=" + H_A1);
						log.debug("A2=" + A2 + " H_A2=" + H_A2);
						log.debug("rspauth" + rspauth);
						log.debug("_response=" + _response);

						if (rspauth.equalsIgnoreCase(_response)) {
							// 鉴权成功
							authResult = true;
						}
					}
					if (_data != null) {
						String nonce = MD5Util.randomUUID();
						String opaque = MD5Util.randomUUID();
						_data = new RegisterData();
						_data.setNonce(nonce);
						_data.setOpaque(opaque);

						registerService.setTerminalIdToRegisterData(req, terminalId, _data);

					}
				}
			}
		}
		if (!authResult) {
			ServletUtil.SetAuthFailed(terminalId, resp, "auth");
			return;
		}

		/** ************************鉴权结束*************************** */

		/** *******************计算返回摘要********************* */
		String responseqop = "auth-int";

		if (responseqop != null) {
			String nextnonce = MD5Util.randomUUID();
			String rspauth = null;

			String A1 = _username + ":" + _realm + ":" + _passwd;
			String A2 = null;
			if ("auth".equals(responseqop)) {
				A2 = ":" + _uri;
			} else {
				A2 = ":" + _uri + ":" + MD5Util.MD5(null);
			}
			String B1 = MD5Util.MD5(A1);
			String B2 = MD5Util.MD5(A2);
			String udata = nextnonce + ":" + _nc + ":" + _cnonce + ":" + _qop + ":" + B2;
			String C = B1 + ":" + udata;
			rspauth = MD5Util.MD5(C);

			StringBuffer authentication = new StringBuffer();
			authentication.append("Digest ");
			authentication.append("qop=" + ConvertUtil.addDoubleQuotationMarks(responseqop));
			authentication.append(",nextnonce=" + ConvertUtil.addDoubleQuotationMarks(nextnonce));
			authentication.append(",rspauth=" + ConvertUtil.addDoubleQuotationMarks(rspauth));
			authentication.append(",nc=" + _nc);
			authentication.append(",cnonce=" + ConvertUtil.addDoubleQuotationMarks(_cnonce));
			resp.addHeader("Authentication-Info", authentication.toString());
			log.debug("Authentication-Info:" + resp.getHeader("Authentication-Info"));
			registerService.setSession(req, terminalId);
			if (_data != null) {
				_data.setNonce(nextnonce);
				registerService.setTerminalIdToRegisterData(req, terminalId, _data);
			}
		}

		// 返回消息体
		String resxml = doAuthGetService(req, resp, terminalId); // 调用抽象，具体类去实现。
		if (resxml != null && !"".equals(resxml)) {
			try {
				resp.getWriter().write(resxml);
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}
		}
		/** *******************计算返回摘要结束********************* */

	}

	public abstract String doAuthGetService(HttpServletRequest req, HttpServletResponse resp, String terminalId);

}
