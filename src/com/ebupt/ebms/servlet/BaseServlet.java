package com.ebupt.ebms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebupt.ebms.cache.TermInfoCache;
import com.ebupt.ebms.utils.ServletUtil;

/**
 * @author QiChen Create on 2011-3-7
 * @version 1.0
 */
public abstract class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(BaseServlet.class);

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		String contentType = "text/html;charset=utf-8";
//		resp.setContentType(contentType);

		// ��ȡ�ն��豸��
		String deviceId = req.getHeader("x-device-id");
		if (deviceId == null || "".equals(deviceId))
			deviceId = req.getParameter("x-device-id");
		if (deviceId == null || "".equals(deviceId)) {
			String errorInfo = "TerminalId is null...";
			log.error(errorInfo);
			resp.setStatus(HTTPRespCode.ParaError.value());
			return;
		}

		// �жϸ��ն��Ƿ�����ƽ̨����
		if (TermInfoCache.isTerminalExist(this.getServletContext(), deviceId)) {
			doGetService(req, resp, deviceId); // ���ó��󣨼�����ȥʵ�֣�
		} else {
			log.info("TerminalId=" + deviceId + " is invalid");
			ServletUtil.SetAuthFailed(deviceId, resp, "auth");
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	};

	public abstract void doGetService(HttpServletRequest req,
			HttpServletResponse resp, String terminalId) throws IOException;
}
