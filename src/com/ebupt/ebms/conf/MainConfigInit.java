package com.ebupt.ebms.conf;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


/**   
 * @author QiChen
 * Create on 2011-3-21
 * @version 1.0  
 * 访问/resetMainConfig,读取配置文件，可以不停止Tomcat的情况下，修改MainConfig
 */
@WebServlet(urlPatterns = {"/resetMainConfig"}, loadOnStartup = 3)
public class MainConfigInit extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MainConfigInit.class);
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.doInit();
	}
	
	private void doInit(){
		log.info("--------------------MAINCONFIG INIT--------------------");
		Properties props = new Properties();
		try {
			props.load(getServletContext().getResourceAsStream("/WEB-INF/MainConfig.properties"));
			MainConfig.getInstance().setLogPath(props.getProperty("logPath"));
			MainConfig.getInstance().setSessionTimeOut(Integer.valueOf(props.getProperty("sessionTimeOut")));
			MainConfig.getInstance().setUrlPath(props.getProperty("urlPath"));
			MainConfig.getInstance().setUseCache(Boolean.valueOf(props.getProperty("useCache")));
			MainConfig.getInstance().setUseMail(Boolean.valueOf(props.getProperty("useMail")));
			MainConfig.getInstance().setVideoConvert(Boolean.valueOf(props.getProperty("videoConvert")));
			MainConfig.getInstance().setWebPath(props.getProperty("webPath"));
			MainConfig.getInstance().setTomcatPath(props.getProperty("tomcatPath"));
			MainConfig.getInstance().setPhoneNumber(props.getProperty("phoneNumber"));
			MainConfig.getInstance().setEmail(props.getProperty("email"));
			MainConfig.getInstance().setMailServer(props.getProperty("mailServer"));
			MainConfig.getInstance().setMailAccount(props.getProperty("mailAccount"));
			MainConfig.getInstance().setMailPasswd(props.getProperty("mailPasswd"));
			MainConfig.getInstance().setLtpasso(props.getProperty("ltpasso"));
			MainConfig.getInstance().setSitesso(props.getProperty("sitesso"));
			MainConfig.getInstance().setSsourl(props.getProperty("ssourl"));
			MainConfig.getInstance().setStatisticsstarttime(props.getProperty("statisticsstarttime"));
			MainConfig.getInstance().setStatisticsendtime(props.getProperty("statisticsendtime"));
			log.info(MainConfig.getInstance().string());
		log.info("--------------------MAINCONFIG END INIT--------------------");
		} catch (IOException e) {
			log.error("没有找到MainConfig.properties!");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doInit();
	}
}
