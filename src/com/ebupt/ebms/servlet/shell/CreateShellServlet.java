package com.ebupt.ebms.servlet.shell;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.dao.TerminalDao;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.FileUtil;

/**
 * @author ZhangQian Create on 2012-12-18
 * @version 1.0
 */
@WebServlet("/createshell")
public class CreateShellServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(CreateShellServlet.class);

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		TerminalDao terminalDao = (TerminalDao) BeanUtil.getBean(getServletContext(), "terminalDaoImpl");

		ArrayList<String> terminalids = terminalDao.getAllTerminals();
		if (terminalids != null) {
			for (String terminalid : terminalids) {
				FileUtil.writeStringToFile(MainConfig.getInstance().getWebPath() + "/shell/" + terminalid + ".sh",
						"killall stbclient");
				log.info(terminalid + ".sh is created!");
			}
		}
		terminalids.clear();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
