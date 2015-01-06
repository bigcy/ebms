package com.ebupt.ebms.service.task;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.dao.SoftInfoDao;
import com.ebupt.ebms.entity.SoftInfo;
import com.ebupt.ebms.utils.FileUtil;
import com.ebupt.ebms.utils.MD5Util;
import com.ebupt.ebms.utils.XMLUtil;

/**
 * @author lishuhua Create time 2011-3-7
 * @version 1.0
 */
@Service
public class SoftInfoService {

	@Autowired
	@Qualifier("softInfoDaoImpl")
	private SoftInfoDao softInfoDao;

	private static final Logger log = Logger.getLogger(SoftInfoService.class);

	public SoftInfo findSoft(String version,String factoryid) {
		log.debug("------Enter into SoftInfoService findSoft method------");
		return softInfoDao.findSoft(version,factoryid);
	}

	public String softupdate(String factoryid, String version, String path) {
		String filename = path.substring(path.lastIndexOf("/") + 1);
		String url = MainConfig.getInstance().getUrlPath() + "/softinfo/" + factoryid + "_" + version + "/" + filename;
		String filesize = new File(path).length() + "";
		String md5 = MD5Util.FileMD5(path);
		Document document = DocumentHelper.createDocument();
		Element updateconfig = document.addElement("updateconfig");
		updateconfig.addElement("version").addText(version);
		updateconfig.addElement("filename").addText(filename);
		updateconfig.addElement("filesize").addText(filesize);
		updateconfig.addElement("url").addText(url);
		updateconfig.addElement("md5").addText(md5);

		String xml = XMLUtil.formatXML(document);
		log.info("softupdatexml=" + xml);
		String fileName = filename + "_" + factoryid + "_" + version + "_upgrade.cfg";
		String subPath = File.separator + "softinfo" + File.separator;
		FileUtil.saveFile(MainConfig.getInstance().getWebPath() + subPath, fileName, xml);
		
		return MainConfig.getInstance().getUrlPath() + subPath + fileName;

	}
}
