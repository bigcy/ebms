package com.ebupt.ebms.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.cache.SQLCache;
import com.ebupt.ebms.cache.TermInfoCache;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.dao.OnlineTimeDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.OnlineTime;
import com.ebupt.ebms.utils.MD5Util;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * @author Create on 2014-2-27
 * @version 1.0
 */
@Repository
public class OnlineTimeDaoImpl extends DaoSupport implements OnlineTimeDao {

	private static Logger log = Logger.getLogger(OnlineTimeDaoImpl.class);

	@Override
	public void createOrUpdateOnlineTime(String nowdate) {

		String startstr = MainConfig.getInstance().getStatisticsstarttime();
		if ("".equals(startstr) || startstr == null) {
			startstr = "0600";
		}

		String endstr = MainConfig.getInstance().getStatisticsendtime();
		if ("".equals(endstr) || endstr == null) {
			endstr = "2000";
		}

		int starttime = Integer.parseInt(startstr);
		int endtime = Integer.parseInt(endstr);

		String nowhmstr = TimeUtil.getHHmm();
		int nowhm = Integer.parseInt(nowhmstr);

		// ��ǰʱ���ڿ�ʼͳ�ƺͽ���ͳ��ʱ��֮����ʼ������������ݿ⣬������������е�����
		if (starttime <= nowhm && nowhm <= endtime) {
			String termsql = "select terminalid from Terminal";
			List<Object> terminals = find(termsql);

			if (terminals != null && !terminals.isEmpty()) {
				String nowtime = TimeUtil.getTime();
				try {
					String objstr;
					OnlineTime online = new OnlineTime();
					for (Object obj : terminals) {
						if (obj instanceof String) {
							objstr = (String) obj;
							// �ն˱��Ϊ12λ
							if (objstr.length() == 12) {
								String onlinetime = TermInfoCache
										.getTermOnlineTime(objstr);

								if (!"".equals(onlinetime)
										&& onlinetime != null
										&& !"0".equals(onlinetime)) {
									String checksql = "select terminalid from OnlineTime where terminalid = '"
											+ objstr
											+ "' and statisticsdate = '"
											+ nowdate + "'";
									String checkres = (String) findSingleObject(checksql);
									// ���������������£��������һ��������
									String createorupdatesql = "";
									if ((objstr).equals(checkres)) {
										// �ж�Ŀǰ�洢��ʱ���Ƿ�͸ջ�ȡ����ʱ����ͬ����ͬ�Ļ�����Ҫ�ٴθ��£���ȡ��ʱ��������ݿ��ȡ��ʱ�������
										String getonlinetimestr = "select onlinetime from OnlineTime where terminalid = '"
												+ objstr
												+ "' and statisticsdate = '"
												+ nowdate + "'";
										String getonlinetime = (String) findSingleObject(getonlinetimestr);

										int dataonlinetime = Integer
												.parseInt(getonlinetime);
										int nowonlintime = Integer
												.parseInt(onlinetime);
//										log.info("dataonlinetime is:"
//												+ dataonlinetime
//												+ ", nowonlintime is:"
//												+ nowonlintime);

										// ��ȡ��ʱ��������ݿ����ȡ��ʱ�������
										if (nowonlintime > dataonlinetime) {
											createorupdatesql = "update OnlineTime set onlinetime = '"
													+ onlinetime
													+ "', updatetime = '"
													+ nowtime
													+ "' where terminalid = '"
													+ objstr
													+ "' and statisticsdate = '"
													+ nowdate + "'";
										}
									} else {
										online.setSerialno(MD5Util.randomUUID());
										online.setTerminalid(objstr);
										online.setStatisticsdate(TimeUtil
												.getDate());
										online.setOnlinetime(onlinetime);
										online.setUpdatetime(TimeUtil.getTime());
										add(online);
									}

									// ��sql�����뻺�����
									if (!"".equals(createorupdatesql)) {
										SQLCache.put(createorupdatesql);
									}
								}

							}
							objstr = "";
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				}
			}
		} else {
			String termsql = "select terminalid from Terminal";
			List<Object> terminals = find(termsql);
			if (terminals != null && !terminals.isEmpty()) {
				try {
					String objstr;
					for (Object obj : terminals) {
						if (obj instanceof String) {
							objstr = (String) obj;
							// �����������,�ն˱��Ϊ12λ
							if (objstr.length() == 12) {
								String onlinetime = TermInfoCache
										.getTermOnlineTime(objstr);
								// ֻ����onlinetime��Ϊ�յģ�Ϊ�յĲ�������
								if (!"".equals(onlinetime)
										&& onlinetime != null) {
									TermInfoCache
											.setTermOnlineTime(objstr, "0");
								}
							}
							objstr = "";
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage(), e);
				}
			}
		}

	}

}