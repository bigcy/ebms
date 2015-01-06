package com.ebupt.ebms.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import com.ebupt.ebms.cache.redis.RedisCacheStart;
import com.ebupt.ebms.cache.redis.RedisClient;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.dao.BrandItemDao;
import com.ebupt.ebms.dao.TermGroupItemDao;
import com.ebupt.ebms.dao.TerminalDao;
import com.ebupt.ebms.entity.BrandItem;
import com.ebupt.ebms.entity.TermGroupItem;
import com.ebupt.ebms.entity.Terminal;
import com.ebupt.ebms.utils.BeanUtil;

/**
 * @author QiChen Create on 2011-3-8
 * @version 1.0
 */
public class TermInfoCache {

	private static final RedisClient redisclient = RedisCacheStart.createRedisClient();

	// 缓存的过期时间，单位秒
	private static final int expireTime = 10 * 60;

	/**
	 * 设置轮询时间
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public static void setTermSelectTime(String terminalId, String time) {
		//redisclient.setObject(CacheIdPrefix.TerminalSelectTime + terminalId, time);
		redisclient.setString(CacheIdPrefix.TerminalSelectTime + terminalId, time);
	}

	/**
	 * 获取轮询时间
	 * 
	 * @param terminalId
	 * @return
	 */
	public static String getTermSelectTime(String terminalId) {
//		Object obj = null;
//		obj = redisclient.getObject(CacheIdPrefix.TerminalSelectTime + terminalId);
//		if (obj != null)
//			return (String) obj;
//		return null;
		return redisclient.getString(CacheIdPrefix.TerminalSelectTime + terminalId);
	}
	
	/**
	 * 设置Shell轮询时间
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public static void setTermShellTime(String terminalId, String time) {
		redisclient.setString(CacheIdPrefix.TerminalShellTime + terminalId, time);
	}

	/**
	 * 获取Shell轮询时间
	 * 
	 * @param terminalId
	 * @return
	 */
	public static String getShellSelectTime(String terminalId) {
		return redisclient.getString(CacheIdPrefix.TerminalShellTime + terminalId);
	}

	/**
	 * 查找终端所在群组
	 * 
	 * @param sc
	 * @param terminalId
	 * @return
	 */
//	public static String getGroupsByTerminalId(ServletContext sc, String terminalId) {
//		String groupIds = "";
//
//		if (MainConfig.getInstance().isUseCache()) {
//			groupIds = (String) redisclient.getObject(CacheIdPrefix.TerminalIdToGroupId + terminalId);
//		}
//
//		if (groupIds == null || groupIds.equals("")) {
//			TermGroupItemDao termGroupItemDao = (TermGroupItemDao) BeanUtil.getBean(sc, "termGroupItemDaoImpl");
//			List<TermGroupItem> items = termGroupItemDao.getItemsByTermId(terminalId);
//			
//			for (TermGroupItem item : items) {
//				if (groupIds == null || groupIds.equals("")) {
//					groupIds = item.getGroupid();
//				} else {
//					groupIds = groupIds + "," + item.getGroupid();
//				}
//			}
//
//			redisclient.setObject(CacheIdPrefix.TerminalIdToGroupId + terminalId, groupIds, expireTime);
//		}
//
//		return groupIds;
//	}
	//在原有的基础上增加对品牌的支持2012-09-27
	public static String getGroupsByTerminalId(ServletContext sc, String terminalId) {
		String groupIds = "";

		if (MainConfig.getInstance().isUseCache()) {
			groupIds = (String) redisclient.getObject(CacheIdPrefix.TerminalIdToGroupId + terminalId);
		}

		if (groupIds == null || groupIds.equals("")) {
			TermGroupItemDao termGroupItemDao = (TermGroupItemDao) BeanUtil.getBean(sc, "termGroupItemDaoImpl");
			List<TermGroupItem> items = termGroupItemDao.getItemsByTermId(terminalId);

			//这个地方要新增对品牌的支持,大于0继续走以前的方法，否则去查找品牌2012-09-27
			if(items.size() > 0){
				//这里还是用的以前的处理方式2012-09-27
				for (TermGroupItem item : items) {
					if (groupIds == null || groupIds.equals("")) {
						groupIds = item.getGroupid();
					} else {
						groupIds = groupIds + "," + item.getGroupid();
					}
				}
			}else{
				//这里是对品牌的处理2012-09-27
				BrandItemDao brandItemDao = (BrandItemDao) BeanUtil.getBean(sc, "brandItemDaoImpl");
				List<BrandItem> bitems = brandItemDao.getBrandItemsByTermId(terminalId);
				for (BrandItem bitem : bitems) {
					if (groupIds == null || groupIds.equals("")) {
						groupIds = bitem.getBrandid();
					} else {
						groupIds = groupIds + "," +bitem.getBrandid();
					}
				}
			}

			redisclient.setObject(CacheIdPrefix.TerminalIdToGroupId + terminalId, groupIds, expireTime);
		}

		return groupIds;
	}

	/**
	 * 通过群组号返回所有终端
	 * 
	 * @param groupid
	 * @return
	 */
//	public static Set<String> getTerminalIdsByGroupId(ServletContext sc, String groupid, String operatorid) {
//		if (groupid == null)
//			return null;
//		Set<String> sterminalIds = new HashSet<String>();
//		if (MainConfig.getInstance().isUseCache()) {
//			String ids = (String) redisclient.getObject(CacheIdPrefix.TerminalsByGroupId + groupid + operatorid);
//			if (ids != null) {
//				for (String id : ids.split(",")) {
//					if (id != null && !"".equals(id))
//						sterminalIds.add(id);
//				}
//			}
//		}
//		if (sterminalIds.size() == 0) {
//			// 查询数据库
//			TermGroupItemDao termGroupItemDao = (TermGroupItemDao) BeanUtil.getBean(sc, "termGroupItemDaoImpl");
//			String terminalIds = "";
//			for (String terminal : termGroupItemDao.getItemsByGroupAndOperatorId(groupid,operatorid)) {
//				sterminalIds.add(terminal);
//				if ("".equals(terminalIds)) {
//					terminalIds = terminal;
//				} else {
//					terminalIds = terminalIds + "," + terminal;
//				}
//			}
//
//			if (sterminalIds.size() > 0) {
//				redisclient.setObject(CacheIdPrefix.TerminalsByGroupId + groupid + operatorid, terminalIds, expireTime);
//			}
//		}
//		return sterminalIds;
//	}
	//在原有的基础上增加对品牌的支持2012-09-27
	public static Set<String> getTerminalIdsByGroupId(ServletContext sc, String groupid, String operatorid) {
		if (groupid == null)
			return null;
		Set<String> sterminalIds = new HashSet<String>();
		if (MainConfig.getInstance().isUseCache()) {
			String ids = (String) redisclient.getObject(CacheIdPrefix.TerminalsByGroupId + groupid + operatorid);
			if (ids != null) {
				for (String id : ids.split(",")) {
					if (id != null && !"".equals(id))
						sterminalIds.add(id);
				}
			}
		}
		if (sterminalIds.size() == 0) {
			// 查询数据库
			TermGroupItemDao termGroupItemDao = (TermGroupItemDao) BeanUtil.getBean(sc, "termGroupItemDaoImpl");
			String terminalIds = "";
			
			List<String> terminals = termGroupItemDao.getItemsByGroupAndOperatorId(groupid,operatorid);
			if(terminals.size() == 0){
				BrandItemDao brandItemDao = (BrandItemDao) BeanUtil.getBean(sc, "brandItemDaoImpl");
				terminals = brandItemDao.getBrandItemsByBrandAndOperatorId(groupid, operatorid);
			}
			
			for (String terminal : terminals) {
				sterminalIds.add(terminal);
				if ("".equals(terminalIds)) {
					terminalIds = terminal;
				} else {
					terminalIds = terminalIds + "," + terminal;
				}
			}

			if (sterminalIds.size() > 0) {
				redisclient.setObject(CacheIdPrefix.TerminalsByGroupId + groupid + operatorid, terminalIds, expireTime);
			}
		}
		return sterminalIds;
	}
	
	/**
	 * 通过群组号返回所有终端
	 * 北京移动版本，不需要根据操作员id去找
	 * @param groupid
	 * @return
	 */
//	public static Set<String> getTerminalIdsByGroupId(ServletContext sc, String groupid) {
//		if (groupid == null)
//			return null;
//		Set<String> sterminalIds = new HashSet<String>();
//		if (MainConfig.getInstance().isUseCache()) {
//			String ids = (String) redisclient.getObject(CacheIdPrefix.TerminalsByGroupId + groupid);
//			if (ids != null) {
//				for (String id : ids.split(",")) {
//					if (id != null && !"".equals(id))
//						sterminalIds.add(id);
//				}
//			}
//		}
//		if (sterminalIds.size() == 0) {
//			// 查询数据库
//			TermGroupItemDao termGroupItemDao = (TermGroupItemDao) BeanUtil.getBean(sc, "termGroupItemDaoImpl");
//			String terminalIds = "";
//			for (String terminal : termGroupItemDao.getTermianlIdsByGroupId(groupid)) {
//				sterminalIds.add(terminal);
//				if ("".equals(terminalIds)) {
//					terminalIds = terminal;
//				} else {
//					terminalIds = terminalIds + "," + terminal;
//				}
//			}
//
//			if (sterminalIds.size() > 0) {
//				redisclient.setObject(CacheIdPrefix.TerminalsByGroupId + groupid, terminalIds, expireTime);
//			}
//		}
//		return sterminalIds;
//	}
	//在原有的基础上增加对品牌的支持2012-09-27
	public static Set<String> getTerminalIdsByGroupId(ServletContext sc, String groupid) {
		if (groupid == null)
			return null;
		Set<String> sterminalIds = new HashSet<String>();
		if (MainConfig.getInstance().isUseCache()) {
			String ids = (String) redisclient.getObject(CacheIdPrefix.TerminalsByGroupId + groupid);
			if (ids != null) {
				for (String id : ids.split(",")) {
					if (id != null && !"".equals(id))
						sterminalIds.add(id);
				}
			}
		}
		if (sterminalIds.size() == 0) {
			// 查询数据库
			TermGroupItemDao termGroupItemDao = (TermGroupItemDao) BeanUtil.getBean(sc, "termGroupItemDaoImpl");
			String terminalIds = "";
			
			List<String> terminals = termGroupItemDao.getTermianlIdsByGroupId(groupid);
			if(terminals.size() == 0){
				BrandItemDao brandItemDao = (BrandItemDao) BeanUtil.getBean(sc, "brandItemDaoImpl");
				terminals = brandItemDao.getTermianlIdsByBrandId(groupid);
			}
			
			for (String terminal : terminals) {
				sterminalIds.add(terminal);
				if ("".equals(terminalIds)) {
					terminalIds = terminal;
				} else {
					terminalIds = terminalIds + "," + terminal;
				}
			}

			if (sterminalIds.size() > 0) {
				redisclient.setObject(CacheIdPrefix.TerminalsByGroupId + groupid, terminalIds, expireTime);
			}
		}
		return sterminalIds;
	}

	public static boolean isTerminalExist(ServletContext sc, String terminalid) {
		String result = null;
		if (MainConfig.getInstance().isUseCache()) {
			result = (String) redisclient.getObject(CacheIdPrefix.TermianlIdExist + terminalid);
		}

		if (result == null) {
			TerminalDao termianlDao = (TerminalDao) BeanUtil.getBean(sc, "terminalDaoImpl");
			Terminal terminal = (Terminal) termianlDao.find(Terminal.class, terminalid);
			if (terminal != null) {
				redisclient.setObject(CacheIdPrefix.TermianlIdExist + terminalid, "true", expireTime);
				result = "true";
			} else {
				redisclient.setObject(CacheIdPrefix.TermianlIdExist + terminalid, "false", expireTime);
				result = "false";
			}
		}
		if ("true".equals(result)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 通过终端返回所有手机号码
	 * 
	 * @param terminalid
	 * @return
	 */
	public static Set<String> getPhonesByTerminalid(ServletContext sc, String terminalid) {
		if (terminalid == null)
			return null;
		Set<String> results = new HashSet<String>();
		if (MainConfig.getInstance().isUseCache()) {
			String ids = (String) redisclient.getObject(CacheIdPrefix.PhonesByTermianlId + terminalid);
			if (ids != null) {
				for (String id : ids.split(",")) {
					if (id != null && !"".equals(id))
						results.add(id);
				}
			}
		}
		if (results.size() == 0) {
			// 查询数据库
			TerminalDao termDao = (TerminalDao) BeanUtil.getBean(sc, "terminalDaoImpl");
			String phones = "";
			for (String phone : termDao.getPhonesByTerminalid(terminalid)) {
				results.add(phone);
				if ("".equals(phones)) {
					phones = phone;
				} else {
					phones = phones + "," + phone;
				}
			}

			if (results.size() > 0) {
				redisclient.setObject(CacheIdPrefix.PhonesByTermianlId + terminalid, phones, expireTime);
			}
		}
		return results;
	}
	
	
	/**
	 * 设置截屏状态位
	 * @param terminalId
	 * @param flag
	 *            0：不截屏，1：截屏
	 */
	public static void setSnapshotFlag(String terminalId, String flag) {
		redisclient.setString(terminalId + ":snapshotcmd", flag);
		
		//清空截屏队列
		redisclient.ltrim(terminalId + ":snapshotlist", 1, 0);
	}

	/**
	 * 获取截屏状态位
	 * @param terminalId
	 * @return
	 */
	public static String getSnapshotFlag(String terminalId) {
		return redisclient.getString(terminalId + ":snapshotcmd");
	}
	

	/**
	 * 获取队列中的截屏数据
	 * @param terminalId
	 * @return
	 */
	public static String getSnapshot(String terminalId) {
		byte[] snapshot = redisclient.lpop(terminalId + ":snapshotlist");
		if (snapshot != null)
			return new String(snapshot);
		else
			return null;
	}
	
	/**
	 * 设置终端在线时长
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public static void setTermOnlineTime(String terminalId, String time) {
		redisclient.setString(CacheIdPrefix.TerminalOnlineTime + terminalId, time);
	}

	/**
	 * 获取终端在线时长
	 * 
	 * @param terminalId
	 * @return
	 */
	public static String getTermOnlineTime(String terminalId) {
		return redisclient.getString(CacheIdPrefix.TerminalOnlineTime + terminalId);
	}
	
}
