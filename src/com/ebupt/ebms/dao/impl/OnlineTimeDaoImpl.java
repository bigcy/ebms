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

		// 当前时间在开始统计和结束统计时间之间则开始创建或更新数据库，否则清除缓存中的数据
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
							// 终端编号为12位
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
									// 如果存在数据则更新，否则插入一条新数据
									String createorupdatesql = "";
									if ((objstr).equals(checkres)) {
										// 判断目前存储的时间是否和刚获取到的时间相同，相同的话不需要再次更新，获取的时间大于数据库存取的时间则更新
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

										// 获取的时间大于数据库里存取的时间则更新
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

									// 将sql语句加入缓存队列
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
							// 清除缓存数据,终端编号为12位
							if (objstr.length() == 12) {
								String onlinetime = TermInfoCache
										.getTermOnlineTime(objstr);
								// 只处理onlinetime不为空的，为空的不做处理
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