package com.ebupt.ebms.cache;

import com.ebupt.ebms.cache.redis.RedisCacheStart;
import com.ebupt.ebms.cache.redis.RedisClient;
import com.ebupt.ebms.utils.FileUtil;
import com.ebupt.ebms.utils.MD5Util;

/**
 * ������Ϣ����
 * 
 * @author zhangqian 2010-08-10
 */
public class TaskInfoCache {

	private static final RedisClient redisclient = RedisCacheStart.createRedisClient();

	// ����Ĺ���ʱ�䣬��λ��
	private static final int expireTime = 10 * 60;

	/**
	 * ����������
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public static void setTaskFlag(String terminalId, String flag) {
		redisclient.setObject(CacheIdPrefix.TaskFlag + terminalId, flag);
	}

	/**
	 * ��ȡ������
	 * 
	 * @param terminalId
	 * @return
	 */
	public static String getTaskFlag(String terminalId) {
		Object obj = redisclient.getObject(CacheIdPrefix.TaskFlag + terminalId);
		if (obj != null) {
			return (String) obj;
		} else {
			return "0";
		}
	}

	/**
	 * ��ȡ��Դ�ļ�·��
	 * 
	 * @param key
	 * @param path
	 */
	public static void setResourceXMLPath(String key, String path) {
		redisclient.setObject(CacheIdPrefix.ResourceXMLPath + key, path, expireTime);
	}

	/**
	 * ȡ����Դ�ļ�·��
	 * 
	 * @param key
	 * @return
	 */
	public static String getResourceXMLPath(String key) {
		Object obj = redisclient.getObject(CacheIdPrefix.ResourceXMLPath + key);
		if (obj != null) {
			return (String) obj;
		} else {
			return null;
		}
	}

	/**
	 * ȡ����Դ�ļ�MD5ֵ
	 * 
	 * @param key
	 * @return
	 */
	public static String getResourceXMLPathMD5(String path) {
		Object obj = redisclient.getObject(CacheIdPrefix.ResourceXMLPathMD5 + path);
		if (obj != null) {
			return (String) obj;
		} else {
			String md5 = MD5Util.FileMD5(path);
			redisclient.setObject(CacheIdPrefix.ResourceXMLPathMD5 + path, md5, expireTime);
			return md5;
		}
	}

	/**
	 * ȡ����Դ�ļ���С
	 * 
	 * @param key
	 * @return
	 */
	public static Long getResourceXMLPathSize(String path) {
		Object obj = redisclient.getObject(CacheIdPrefix.ResourceXMLPathSize + path);
		if (obj != null) {
			return (Long) obj;
		} else {
			Long size = FileUtil.getFileSize(path);
			redisclient.setObject(CacheIdPrefix.ResourceXMLPathSize + path, size, expireTime);
			return size;
		}
	}

	/**
	 * ����key�������
	 * 
	 * @param key
	 * @return
	 */
	public static boolean deleteValueByKey(String key) {
		redisclient.delete(key);
		return true;
	}

}
