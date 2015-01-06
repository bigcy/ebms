package com.ebupt.ebms.conf;

/**
 * @author QiChen Create on 2011-3-7
 * @version 1.0
 */
public class MainConfig {

	private boolean useCache = true;// �Ƿ񻺴�

	private boolean videoConvert = false;// �Ƿ������Ƶ��ʽת��
	
	private boolean useMail = true;//�Ƿ�����ʼ�֪ͨ

	private Integer sessionTimeOut = 3 * 60 * 1000;

	private String logPath = ""; // �ϴ��ĸ�����־�ı���·��

	private String webPath = ""; // �������������ļ���·���������б����Դ�嵥���ڸû�·����

	private String urlPath = ""; // �ն˷��ʻ�·������Ϊhttp��ftp�Ļ�·��

	private String tomcatPath = ""; // Tomcat ����������·��

	private String phoneNumber = "";// ά����Ա�ֻ����룬���֮���ö��ŷָ�
	
	private String email = "";// ά����Ա�����ַ�����֮���ö��ŷָ�

	private String mailServer = "";// �����������ַ

	private String mailAccount = "";// �����˺�

	private String mailPasswd = "";// ��������
	
	private String ltpasso = "";// �����ƶ�����LTPA��ʽ��SSO
	
	private String sitesso = "";// �����ƶ�����SiteMinder��ʽ��SSO
	
	private String ssourl = "";// SSO�ķ���·��
	
	private String statisticsstarttime = "";//������ͳ�ƿ�ʼʱ��
	
	private String statisticsendtime = "";//������ͳ�ƽ���ʱ��
	
	private static MainConfig instance = new MainConfig();

	public static MainConfig getInstance() {
		if (instance == null)
			instance = new MainConfig();
		return instance;
	}

	public String string() {

		StringBuffer sb = new StringBuffer();
		sb.append("logPath : ").append(logPath).append("\n");
		sb.append("webPath : ").append(webPath).append("\n");
		sb.append("urlPath : ").append(urlPath).append("\n");
		sb.append("tomcatPath : ").append(tomcatPath).append("\n");
		sb.append("useCache : ").append(useCache).append("\n");
		sb.append("useMail : ").append(useMail).append("\n");
		sb.append("videoConvert : ").append(videoConvert).append("\n");
		sb.append("sessionTimeOut : ").append(sessionTimeOut).append("\n");
		sb.append("phoneNumber : ").append(phoneNumber).append("\n");
		sb.append("email : ").append(email).append("\n");
		sb.append("mailServer : ").append(mailServer).append("\n");
		sb.append("mailAccount : ").append(mailAccount).append("\n");
		sb.append("mailPasswd : ").append(mailPasswd).append("\n");
		sb.append("ltpasso : ").append(ltpasso).append("\n");
		sb.append("sitesso : ").append(sitesso).append("\n");
		sb.append("ssourl : ").append(ssourl).append("\n");
		sb.append("statisticsstarttime : ").append(statisticsstarttime).append("\n");
		sb.append("statisticsendtime : ").append(statisticsendtime).append("\n");
		return sb.toString();
	}

	public boolean isUseCache() {
		return useCache;
	}

	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}

	public boolean isUseMail() {
		return useMail;
	}

	public void setUseMail(boolean useMail) {
		this.useMail = useMail;
	}

	public boolean isVideoConvert() {
		return videoConvert;
	}

	public void setVideoConvert(boolean videoConvert) {
		this.videoConvert = videoConvert;
	}

	public Integer getSessionTimeOut() {
		return sessionTimeOut;
	}

	public void setSessionTimeOut(Integer sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getTomcatPath() {
		return tomcatPath;
	}

	public void setTomcatPath(String tomcatPath) {
		this.tomcatPath = tomcatPath;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public String getMailAccount() {
		return mailAccount;
	}

	public void setMailAccount(String mailAccount) {
		this.mailAccount = mailAccount;
	}

	public String getMailPasswd() {
		return mailPasswd;
	}

	public void setMailPasswd(String mailPasswd) {
		this.mailPasswd = mailPasswd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLtpasso() {
		return ltpasso;
	}

	public void setLtpasso(String ltpasso) {
		this.ltpasso = ltpasso;
	}

	public String getSitesso() {
		return sitesso;
	}

	public void setSitesso(String sitesso) {
		this.sitesso = sitesso;
	}

	public String getSsourl() {
		return ssourl;
	}

	public void setSsourl(String ssourl) {
		this.ssourl = ssourl;
	}

	public String getStatisticsstarttime() {
		return statisticsstarttime;
	}

	public void setStatisticsstarttime(String statisticsstarttime) {
		this.statisticsstarttime = statisticsstarttime;
	}

	public String getStatisticsendtime() {
		return statisticsendtime;
	}

	public void setStatisticsendtime(String statisticsendtime) {
		this.statisticsendtime = statisticsendtime;
	}	
	
}
