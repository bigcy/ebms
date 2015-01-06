package com.ebupt.ebms.servlet;

import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebupt.ebms.utils.TimeUtil;
import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.service.task.RegisterService;
import com.ebupt.ebms.servlet.BaseServlet;
import com.ebupt.ebms.servlet.HTTPRespCode;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ConvertUtil;
import com.ebupt.ebms.utils.ServletUtil;
import com.ebupt.ebms.utils.MD5Util;

/**
 * @author QiChen Create on 2011-3-7
 * @version 1.0
 */
@WebServlet("/register")
public class RegisterServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(RegisterServlet.class);

	@Override
	public void doGetService(HttpServletRequest req, HttpServletResponse resp, String terminalId) {
		log.debug("Terminalid:" + terminalId + " enter into RegisterServlet");

		RegisterService registerService = (RegisterService) BeanUtil.getBean(this.getServletContext(), "registerService");

		// 获取用户请求的authorization
		String authorization = req.getHeader("Authorization");
		// 鉴权结果判断
		boolean authResult = false;
		if (authorization != null){
			log.debug("Authorization " + authorization);
			int index = authorization.indexOf(" ");
			if (index != -1){
				String authType = authorization.substring(0, index);
				if (authType.equalsIgnoreCase("Digest")){

					StringTokenizer st = new StringTokenizer(authorization.substring(index + 1), ",");

					HashMap<String, String> values = new HashMap<String, String>();

					while (st.hasMoreTokens()){
						String token = st.nextToken();
						int subIndex = token.indexOf("=");
						if (subIndex != -1){
							String key = token.substring(0, subIndex).trim();
							String value = token.substring(subIndex + 1).trim();
							values.put(key, value);
						}
					}

					String _username = ConvertUtil.delDoubleQuotationMarks(values.get("username"));
					String _realm = ConvertUtil.delDoubleQuotationMarks(values.get("realm"));
					String _nonce = ConvertUtil.delDoubleQuotationMarks(values.get("nonce"));
					String _uri = ConvertUtil.delDoubleQuotationMarks(values.get("uri"));
					String _qop = ConvertUtil.delDoubleQuotationMarks(values.get("qop"));
					String _nc = values.get("nc");
					String _cnonce = ConvertUtil.delDoubleQuotationMarks(values.get("cnonce"));
					String _response = ConvertUtil.delDoubleQuotationMarks(values.get("response"));
					String _opaque = ConvertUtil.delDoubleQuotationMarks(values.get("opaque"));

					// 取得厂商密码
					String _passwd = registerService.getPasswdByTerminalId(terminalId);
					log.debug("comm_passwd=" + _passwd);
					_passwd = MD5Util.MD5(terminalId + ":" + _passwd);

					// 获取平台-->终端的摘要信息
					RegisterData _data = registerService.getRegisterDataByTerminalId(req, terminalId);

					if (_data != null && _data.getNonce().equalsIgnoreCase(_nonce) && _data.getOpaque().equalsIgnoreCase(_opaque)){

						String A1 = _username + ":" + _realm + ":" + _passwd;
						// String A2 = req.getMethod() + ":" + _uri;
						String A2 = "post" + ":" + _uri;
						String H_A1 = MD5Util.MD5(A1);
						String H_A2 = MD5Util.MD5(A2);

						String rspauth = H_A1 + ":" + _data.getNonce() + ":" + _nc + ":" + _cnonce + ":" + _qop + ":" + H_A2;
						rspauth = MD5Util.MD5(rspauth);

						log.debug("ReqMethod=" + req.getMethod());
						log.debug("_passwd=" + _passwd);
						log.debug("A1=" + A1 + " H_A1=" + H_A1);
						log.debug("A2=" + A2 + " H_A2=" + H_A2);
						log.debug("rspauth=" + rspauth);
						log.debug("_response=" + _response);

						if (rspauth.equalsIgnoreCase(_response)){
							// 鉴权成功
							authResult = true;
							String nextnonce = MD5Util.randomUUID();
							rspauth = null;

							A1 = _username + ":" + _realm + ":" + _passwd;
							A2 = ":" + _uri;
							H_A1 = MD5Util.MD5(A1);
							H_A2 = MD5Util.MD5(A2);
							rspauth = H_A1 + ":" + nextnonce + ":" + _nc + ":" + _cnonce + ":" + _qop + ":" + H_A2;
							rspauth = MD5Util.MD5(rspauth);

							StringBuffer authentication = new StringBuffer();

							authentication.append("Digest ");
							authentication.append("qop=" + ConvertUtil.addDoubleQuotationMarks("auth"));
							authentication.append(",nextnonce=" + ConvertUtil.addDoubleQuotationMarks(nextnonce));
							authentication.append(",rspauth=" + ConvertUtil.addDoubleQuotationMarks(rspauth));
							authentication.append(",nc=" + _nc);
							authentication.append(",cnonce=" + ConvertUtil.addDoubleQuotationMarks(_cnonce));

							resp.addHeader("Authentication-Info", authentication.toString());
							resp.addHeader("servertime", TimeUtil.getTime());
							log.debug("Authentication-Info " + authentication.toString());
							resp.setStatus(HTTPRespCode.OK.value()); // 200

							registerService.setSession(req, terminalId);

							_data.setNonce(nextnonce);
							registerService.setTerminalIdToRegisterData(req, terminalId, _data);

							log.debug("terminalId: " + terminalId + " register success!");
							SQLCache.putTermOperLog(terminalId, "注册成功！");
							return;
						}
					}
				}
			}
		}
		if (!authResult){
			ServletUtil.SetAuthFailed(terminalId, resp, "register");
		}
		log.debug("Terminalid:" + terminalId + " exit RegisterServlet");

		return;
	}
}
