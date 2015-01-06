package com.ebupt.ebms.servlet.shell;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebupt.ebms.cache.TermInfoCache;
import com.ebupt.ebms.servlet.HTTPRespCode;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author ZhangQian Create on 2012-8-13
 * @version 1.0
 */
@WebServlet("/shellalive")
public class ShellAliveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ShellAliveServlet.class);

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String deviceid = req.getParameter("deviceid");
		if (deviceid == null || "".equals(deviceid)) {
			String errorInfo = "TerminalId is null...";
			log.error(errorInfo);
			resp.setStatus(HTTPRespCode.ParaError.value());
			return;
		}

		// 记录轮询时间到缓存中
		String selecttime = TimeUtil.getFormatTime();
		TermInfoCache.setTermShellTime(deviceid, selecttime);
		
		log.info("Terminalid:" + deviceid +" Selecttime:" + selecttime);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
