package com.ebupt.ebms.servlet.shell;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.servlet.HTTPRespCode;
import com.ebupt.ebms.utils.FileUtil;

/**
 * @author ZhangQian Create on 2012-8-13
 * @version 1.0
 */
@WebServlet("/rmshell")
public class RmShellServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(RmShellServlet.class);

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String deviceid = req.getParameter("deviceid");
		if (deviceid == null || "".equals(deviceid)) {
			String errorInfo = "TerminalId is null...";
			log.error(errorInfo);
			resp.setStatus(HTTPRespCode.ParaError.value());
			return;
		}
		String shellPath = MainConfig.getInstance().getWebPath() + "/shell/" + deviceid + ".sh";
		File file = new File(shellPath);
		if (file.exists()) {
			FileUtil.deleteFile(shellPath);
			log.info("Shell:" + deviceid + ".sh was deleted!");
		} else {
			log.warn("No such shell:" + deviceid + ".sh");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
