package com.ebupt.ebms.cache;

import com.ebupt.ebms.cache.redis.RedisCacheStart;
import com.ebupt.ebms.cache.redis.RedisClient;
import com.ebupt.ebms.utils.FileUtil;
import com.ebupt.ebms.utils.MD5Util;

/**
 * 任务信息缓存
 * 
 * @author zhangqian 2010-08-10
 */
public class TaskInfoCache {

	private static final RedisClient redisclient = RedisCacheStart.createRedisClient();

	// 缓存的过期时间，单位秒
	private static final int expireTime = 10 * 60;

	/**
	 * 设置任务标记
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public static void setTaskFlag(String terminalId, String flag) {
		redisclient.setObject(CacheIdPrefix.TaskFlag + terminalId, flag);
	}

	/**
	 * 获取任务标记
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
	 * 获取资源文件路径
	 * 
	 * @param key
	 * @param path
	 */
	public static void setResourceXMLPath(String key, String path) {
		redisclient.setObject(CacheIdPrefix.ResourceXMLPath + key, path, expireTime);
	}

	/**
	 * 取得资源文件路径
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
	 * 取得资源文件MD5值
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
	 * 取得资源文件大小
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
	 * 根据key清除缓存
	 * 
	 * @param key
	 * @return
	 */
	public static boolean deleteValueByKey(String key) {
		redisclient.delete(key);
		return true;
	}

}
