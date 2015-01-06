package com.ebupt.ebms.service.report;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.dao.ConfigReportDao;
import com.ebupt.ebms.entity.TermConfigReport;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author lishuhua Create time 2011-3-7
 * @version 1.0
 */
@Service
public class ConfigReportService {

	@Autowired
	@Qualifier("configReportDaoImpl")
	private ConfigReportDao configReportDao;

	private static final Logger log = Logger.getLogger(ConfigReportService.class);

	public String updateoradd(TermConfigReport termConfig) {
		log.debug("------Enter into ConfigReportService updateoradd method------");
		try {
			TermConfigReport tp = (TermConfigReport) configReportDao.findreport(termConfig.getTerminalid());

			if (tp == null) {
				TermConfigReport report = new TermConfigReport();
				report.setTerminalid(termConfig.getTerminalid());
				report.setReporttime(TimeUtil.getTime());
				if (termConfig.getIp() != null && !"".equals(termConfig.getIp())) {
					report.setIp(termConfig.getIp());
				}
				if (termConfig.getMac() != null && !"".equals(termConfig.getMac())) {
					report.setMac(termConfig.getMac());
				}
				if (termConfig.getGateway() != null && !"".equals(termConfig.getGateway())) {
					report.setGateway(termConfig.getGateway());
				}
				if (termConfig.getDns() != null && !"".equals(termConfig.getDns())) {
					report.setDns(termConfig.getDns());
				}
				if (termConfig.getAppversion() != null && !"".equals(termConfig.getAppversion())) {
					report.setAppversion(termConfig.getAppversion());
				}
				if (termConfig.getStartuptime() != null && !"".equals(termConfig.getStartuptime())) {
					report.setStartuptime(termConfig.getStartuptime());
				}
				if (termConfig.getShutdowntime() != null && !"".equals(termConfig.getShutdowntime())) {
					report.setShutdowntime(termConfig.getShutdowntime());
				}
				if (termConfig.getDiskspacealarm() != null && !"".equals(termConfig.getDiskspacealarm())) {
					report.setDiskspacealarm(termConfig.getDiskspacealarm());
				}
				if (termConfig.getServerip() != null && !"".equals(termConfig.getServerip())) {
					report.setServerip(termConfig.getServerip());
				}
				if (termConfig.getServerport() != null && !"".equals(termConfig.getServerport())) {
					report.setServerport(termConfig.getServerport());
				}
				if (termConfig.getRedisip() != null && !"".equals(termConfig.getRedisip())) {
					report.setRedisip(termConfig.getRedisip());
				}
				if (termConfig.getRedisport() != null && !"".equals(termConfig.getRedisport())) {
					report.setRedisport(termConfig.getRedisport());
				}
				if (termConfig.getMaxmonitortime() != null && !"".equals(termConfig.getMaxmonitortime())) {
					report.setMaxmonitortime(termConfig.getMaxmonitortime());
				}
				if (termConfig.getVolume() != null && !"".equals(termConfig.getVolume())) {
					report.setVolume(termConfig.getVolume());
				}
				if (termConfig.getDownloadrate() != null && !"".equals(termConfig.getDownloadrate())) {
					report.setDownloadrate(termConfig.getDownloadrate());
				}
				if (termConfig.getDownloadtime() != null && !"".equals(termConfig.getDownloadtime())) {
					report.setDownloadtime(termConfig.getDownloadtime());
				}
				if (termConfig.getSelectinterval() != null && !"".equals(termConfig.getSelectinterval())) {
					report.setSelectinterval(termConfig.getSelectinterval());
				}
				if (termConfig.getLogserver() != null && !"".equals(termConfig.getLogserver())) {
					report.setLogserver(termConfig.getLogserver());
				}
				if (termConfig.getUploadlogtime() != null && !"".equals(termConfig.getUploadlogtime())) {
					report.setUploadlogtime(termConfig.getUploadlogtime());
				}
				if (termConfig.getKeeplogtime() != null && !"".equals(termConfig.getKeeplogtime())) {
					report.setKeeplogtime(termConfig.getKeeplogtime());
				}
				if (termConfig.getLoglevel() != null && !"".equals(termConfig.getLoglevel())) {
					report.setLoglevel(termConfig.getLoglevel());
				}
				if (termConfig.getPlacementmodel() != null && !"".equals(termConfig.getPlacementmodel())) {
					report.setPlacementmodel(termConfig.getPlacementmodel());
				}
				if (termConfig.getExdevicetype() != null && !"".equals(termConfig.getExdevicetype())) {
					report.setExdevicetype(termConfig.getExdevicetype());
				}
				if (termConfig.getResolution() != null && !"".equals(termConfig.getResolution())) {
					report.setResolution(termConfig.getResolution());
				}
				configReportDao.add(report);

				log.debug("config report add success!");
				return "0";
			} else {
				tp.setTerminalid(termConfig.getTerminalid());
				tp.setReporttime(TimeUtil.getTime());
				if (termConfig.getIp() != null && !"".equals(termConfig.getIp())) {
					tp.setIp(termConfig.getIp());
				}
				if (termConfig.getMac() != null && !"".equals(termConfig.getMac())) {
					tp.setMac(termConfig.getMac());
				}
				if (termConfig.getGateway() != null && !"".equals(termConfig.getGateway())) {
					tp.setGateway(termConfig.getGateway());
				}
				if (termConfig.getDns() != null && !"".equals(termConfig.getDns())) {
					tp.setDns(termConfig.getDns());
				}
				if (termConfig.getAppversion() != null && !"".equals(termConfig.getAppversion())) {
					tp.setAppversion(termConfig.getAppversion());
				}
				if (termConfig.getStartuptime() != null && !"".equals(termConfig.getStartuptime())) {
					tp.setStartuptime(termConfig.getStartuptime());
				}
				if (termConfig.getShutdowntime() != null && !"".equals(termConfig.getShutdowntime())) {
					tp.setShutdowntime(termConfig.getShutdowntime());
				}
				if (termConfig.getDiskspacealarm() != null && !"".equals(termConfig.getDiskspacealarm())) {
					tp.setDiskspacealarm(termConfig.getDiskspacealarm());
				}
				if (termConfig.getServerip() != null && !"".equals(termConfig.getServerip())) {
					tp.setServerip(termConfig.getServerip());
				}
				if (termConfig.getServerport() != null && !"".equals(termConfig.getServerport())) {
					tp.setServerport(termConfig.getServerport());
				}
				if (termConfig.getRedisip() != null && !"".equals(termConfig.getRedisip())) {
					tp.setRedisip(termConfig.getRedisip());
				}
				if (termConfig.getRedisport() != null && !"".equals(termConfig.getRedisport())) {
					tp.setRedisport(termConfig.getRedisport());
				}
				if (termConfig.getMaxmonitortime() != null && !"".equals(termConfig.getMaxmonitortime())) {
					tp.setMaxmonitortime(termConfig.getMaxmonitortime());
				}
				if (termConfig.getVolume() != null && !"".equals(termConfig.getVolume())) {
					tp.setVolume(termConfig.getVolume());
				}
				if (termConfig.getDownloadrate() != null && !"".equals(termConfig.getDownloadrate())) {
					tp.setDownloadrate(termConfig.getDownloadrate());
				}
				if (termConfig.getDownloadtime() != null && !"".equals(termConfig.getDownloadtime())) {
					tp.setDownloadtime(termConfig.getDownloadtime());
				}
				if (termConfig.getSelectinterval() != null && !"".equals(termConfig.getSelectinterval())) {
					tp.setSelectinterval(termConfig.getSelectinterval());
				}
				if (termConfig.getLogserver() != null && !"".equals(termConfig.getLogserver())) {
					tp.setLogserver(termConfig.getLogserver());
				}
				if (termConfig.getUploadlogtime() != null && !"".equals(termConfig.getUploadlogtime())) {
					tp.setUploadlogtime(termConfig.getUploadlogtime());
				}
				if (termConfig.getKeeplogtime() != null && !"".equals(termConfig.getKeeplogtime())) {
					tp.setKeeplogtime(termConfig.getKeeplogtime());
				}
				if (termConfig.getLoglevel() != null && !"".equals(termConfig.getLoglevel())) {
					tp.setLoglevel(termConfig.getLoglevel());
				}
				if (termConfig.getPlacementmodel() != null && !"".equals(termConfig.getPlacementmodel())) {
					tp.setPlacementmodel(termConfig.getPlacementmodel());
				}
				if (termConfig.getExdevicetype() != null && !"".equals(termConfig.getExdevicetype())) {
					tp.setExdevicetype(termConfig.getExdevicetype());
				}
				if (termConfig.getResolution() != null && !"".equals(termConfig.getResolution())) {
					tp.setResolution(termConfig.getResolution());
				}
				configReportDao.modify(tp);

				log.debug(" config report modify success!");
				return "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		// 数据库操作不成功返回失败
		return "1";
	}

}
