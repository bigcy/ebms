package com.ebupt.ebms.servlet.task;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ebupt.ebms.cache.TaskInfoCache;
import com.ebupt.ebms.service.task.TaskStatusService;
import com.ebupt.ebms.servlet.AuthServlet;
import com.ebupt.ebms.utils.BeanUtil;
import com.ebupt.ebms.utils.ServletUtil;

/**
 * @author zhangqian Create on 2011-9-30
 * @version 1.0
 */
@WebServlet("/regetTask")
public class RegetTaskServlet extends AuthServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(RegetTaskServlet.class);

	@Override
	public String doAuthGetService(HttpServletRequest req, HttpServletResponse resp, String terminalId) {

		log.debug("Terminalid:" + terminalId + " enter into TaskSelectServlet");

		TaskStatusService service = (TaskStatusService) BeanUtil.getBean(getServletContext(), "taskStatusService");
		String result = service.updatePlayListTaskItem(terminalId, "0");
		String errorinfo = null;
		if ("0".equals(result)) {
			TaskInfoCache.setTaskFlag(terminalId, "1");
			log.info("Terminalid:" + terminalId + " regetTask success!");
		} else {
			errorinfo = "Terminalid:" + terminalId + " regetTask failed!";
			log.error(errorinfo);
		}
		return ServletUtil.returnXMLMessage(result, errorinfo);
	}

}
