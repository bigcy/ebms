package com.ebupt.ebms.servlet.main;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import com.ebupt.ebms.servlet.HTTPRespCode;


/**   
 * @author QiChen
 * Create on 2011-3-22
 * @version 1.0  
 */
@WebServlet(urlPatterns = {"/"}, name = "main")
public class EntryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(EntryServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String contentType = "text/html;charset=utf-8";
		resp.setContentType(contentType);
		
		String command = req.getHeader("command");
		log.info("Terminalid:" + req.getHeader("x-device-id") + " command:" + command + " IP:" + req.getRemoteAddr());
		
		if (command == null || command.equals("")) {
			resp.setStatus(HTTPRespCode.ParaError.value());
			log.info("command is invalid");
			return;
		}
		// 解析Command
		EntryType entryType = this.parseCommand(command); 
		
		if (entryType == null) {
			resp.setStatus(HTTPRespCode.ParaError.value());
			return;
		}
		
		RequestDispatcher rd; // 跳转
		
		switch (entryType) {
		case REGISTER:
			rd = req.getRequestDispatcher("/register");
			rd.forward(req, resp);
			break;
		case REGISTER1:
			rd = req.getRequestDispatcher("/register");
			rd.forward(req, resp);
			break;
		case REGISTER2:
			rd = req.getRequestDispatcher("/register");
			rd.forward(req, resp);
			break;
		case GETTASK:
			rd = req.getRequestDispatcher("/getTask");
			rd.forward(req, resp);
			break;
		case REGETTASK:
			rd = req.getRequestDispatcher("/regetTask");
			rd.forward(req, resp);
			break;
		case TASKREPORT:
			rd = req.getRequestDispatcher("/taskReport");
			rd.forward(req, resp);
			break;	
		case PLAYLISTREPORT:
			rd = req.getRequestDispatcher("/playlistReport");
			rd.forward(req, resp);
			break;
		case SOFTUPDATE:
			rd = req.getRequestDispatcher("/softupdate");
			rd.forward(req, resp);
			break;
		case SOFTUPDATEREPORT:
			rd = req.getRequestDispatcher("/softupdateReport");
			rd.forward(req, resp);
			break;
		case CONFIGREPORT:
			rd = req.getRequestDispatcher("/configReport");
			rd.forward(req, resp);
			break;
		case WORKSTATUSREPORT:
			rd = req.getRequestDispatcher("/workstatusReport");
			rd.forward(req, resp);
			break;
		case PLAYINGREPORT:
			rd = req.getRequestDispatcher("/playingReport");
			rd.forward(req, resp);
			break;
		case ALARMREPORT:
			rd = req.getRequestDispatcher("/alarmReport");
			rd.forward(req, resp);
			break;
		case UPLOADLOGREPORT:
			rd = req.getRequestDispatcher("/uploadlogReport");
			rd.forward(req, resp);
			break;
		case TEST:
			rd = req.getRequestDispatcher("/test");
			rd.forward(req, resp);
			break;
		case RESOURCESYNC:
			rd = req.getRequestDispatcher("/resourcesync");
			rd.forward(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	/**
	 * 解析Command
	 * @param command
	 * @return 枚举对象
	 */
	private EntryType parseCommand(String command) {
		for (EntryType entryType : EntryType.values()) {
			if (entryType.getType().equalsIgnoreCase(command))
				return entryType;
		}
		return null;
	}


	public enum EntryType {
		REGISTER("register"),				//认证
		REGISTER1("register1"),				//认证
		REGISTER2("register2"),				//认证
		GETTASK("getTask"),					//任务轮询
		REGETTASK("regetTask"),				//重新获取任务
		TASKREPORT("taskReport"),			//任务获取状态报告
		PLAYLISTREPORT("playlistReport"),	//播放任务下载状态报告		
		SOFTUPDATE("softupdate"),			//软件升级
		SOFTUPDATEREPORT("softupdateReport"),		//软件升级状态报告
		CONFIGREPORT("configReport"),				//终端配置信息上报
		WORKSTATUSREPORT("workstatusReport"),		//工作状态上报
		PLAYINGREPORT("playingReport"), 			//在播内容上报
		ALARMREPORT("alarmReport"),					//终端告警上报
		UPLOADLOGREPORT("uploadlogReport"),			//日志上传完成上报
		RESOURCESYNC("resourcesync"),				//同步服务器资源请求
		TEST("test");								//test
		
		private String type;

		private EntryType(String type) {
			this.type = type;
		}// 私有构造。

		public String getType() {
			return this.type;
		}
	}
}
