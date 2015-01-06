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

	// ����Ĺ���ʱ�䣬��λ��
	private static final int expireTime = 10 * 60;

	/**
	 * ������ѯʱ��
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public static void setTermSelectTime(String terminalId, String time) {
		//redisclient.setObject(CacheIdPrefix.TerminalSelectTime + terminalId, time);
		redisclient.setString(CacheIdPrefix.TerminalSelectTime + terminalId, time);
	}

	/**
	 * ��ȡ��ѯʱ��
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
	 * ����Shell��ѯʱ��
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public static void setTermShellTime(String terminalId, String time) {
		redisclient.setString(CacheIdPrefix.TerminalShellTime + terminalId, time);
	}

	/**
	 * ��ȡShell��ѯʱ��
	 * 
	 * @param terminalId
	 * @return
	 */
	public static String getShellSelectTime(String terminalId) {
		return redisclient.getString(CacheIdPrefix.TerminalShellTime + terminalId);
	}

	/**
	 * �����ն�����Ⱥ��
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
	//��ԭ�еĻ��������Ӷ�Ʒ�Ƶ�֧��2012-09-27
	public static String getGroupsByTerminalId(ServletContext sc, String terminalId) {
		String groupIds = "";

		if (MainConfig.getInstance().isUseCache()) {
			groupIds = (String) redisclient.getObject(CacheIdPrefix.TerminalIdToGroupId + terminalId);
		}

		if (groupIds == null || groupIds.equals("")) {
			TermGroupItemDao termGroupItemDao = (TermGroupItemDao) BeanUtil.getBean(sc, "termGroupItemDaoImpl");
			List<TermGroupItem> items = termGroupItemDao.getItemsByTermId(terminalId);

			//����ط�Ҫ������Ʒ�Ƶ�֧��,����0��������ǰ�ķ���������ȥ����Ʒ��2012-09-27
			if(items.size() > 0){
				//���ﻹ���õ���ǰ�Ĵ���ʽ2012-09-27
				for (TermGroupItem item : items) {
					if (groupIds == null || groupIds.equals("")) {
						groupIds = item.getGroupid();
					} else {
						groupIds = groupIds + "," + item.getGroupid();
					}
				}
			}else{
				//�����Ƕ�Ʒ�ƵĴ���2012-09-27
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
	 * ͨ��Ⱥ��ŷ��������ն�
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
//			// ��ѯ���ݿ�
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
	//��ԭ�еĻ��������Ӷ�Ʒ�Ƶ�֧��2012-09-27
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
			// ��ѯ���ݿ�
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
	 * ͨ��Ⱥ��ŷ��������ն�
	 * �����ƶ��汾������Ҫ���ݲ���Աidȥ��
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
//			// ��ѯ���ݿ�
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
	//��ԭ�еĻ��������Ӷ�Ʒ�Ƶ�֧��2012-09-27
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
			// ��ѯ���ݿ�
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
	 * ͨ���ն˷��������ֻ�����
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
			// ��ѯ���ݿ�
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
	 * ���ý���״̬λ
	 * @param terminalId
	 * @param flag
	 *            0����������1������
	 */
	public static void setSnapshotFlag(String terminalId, String flag) {
		redisclient.setString(terminalId + ":snapshotcmd", flag);
		
		//��ս�������
		redisclient.ltrim(terminalId + ":snapshotlist", 1, 0);
	}

	/**
	 * ��ȡ����״̬λ
	 * @param terminalId
	 * @return
	 */
	public static String getSnapshotFlag(String terminalId) {
		return redisclient.getString(terminalId + ":snapshotcmd");
	}
	

	/**
	 * ��ȡ�����еĽ�������
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
	 * �����ն�����ʱ��
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public static void setTermOnlineTime(String terminalId, String time) {
		redisclient.setString(CacheIdPrefix.TerminalOnlineTime + terminalId, time);
	}

	/**
	 * ��ȡ�ն�����ʱ��
	 * 
	 * @param terminalId
	 * @return
	 */
	public static String getTermOnlineTime(String terminalId) {
		return redisclient.getString(CacheIdPrefix.TerminalOnlineTime + terminalId);
	}
	
}
