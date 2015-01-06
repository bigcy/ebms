package com.ebupt.ebms.cache;

import com.ebupt.ebms.cache.redis.RedisCacheStart;
import com.ebupt.ebms.cache.redis.RedisClient;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.servlet.RegisterData;

/**
 * 终端注册缓存
 * 
 * @author zhangqian 2010-01-16
 */
public class RegisterInfoCache {

	private static final RedisClient redisclient = RedisCacheStart
			.createRedisClient();

	/**
	 * 密码与厂商ID对应关系
	 * 
	 * @param factoryId
	 * @return
	 */
	public static String getPasswdByFactoryId(String factoryId) {

		return (String) redisclient.getObject(CacheIdPrefix.PasswdtoFactoryId
				+ factoryId);
	}

	/**
	 * 密码与厂商ID对应关系
	 * 
	 * @param passwd
	 * @param factoryId
	 */
	public static void setPasswdtoFactoryId(String passwd, String factoryId) {

		redisclient.setObject(CacheIdPrefix.PasswdtoFactoryId + factoryId,
				passwd);
	}

	/**
	 * 认证失败次数
	 * 
	 * @param terminalId
	 * @return
	 */
	public static Integer getAuthFailedTimes(String terminalId) {

		Integer times = (Integer) redisclient
				.getObject(CacheIdPrefix.AuthFailedTimes + terminalId);
		if (times == null)
			times = 1;
		return times;
	}

	/**
	 * 认证失败次数
	 * 
	 * @param terminalId
	 * @param num
	 *            300:单位秒
	 */
	public static void setAuthFailedTimes(String terminalId, Integer num) {

		redisclient.setObject(CacheIdPrefix.AuthFailedTimes + terminalId, num,
				30);
	}

	/**
	 * 终端帐号与注册信息的对应关系
	 * 
	 * @param terminalId
	 * @param data
	 */
	public static void setTerminalIdToRegisterData(String terminalId,
			RegisterData data) {
		redisclient.setObject(CacheIdPrefix.TerminalIdToRegisterData
				+ terminalId, data);
	}

	/**
	 * 通过终端帐号获取终端注册信息
	 * 
	 * @param terminalId
	 * @return
	 */
	public static RegisterData getRegisterDataByTerminalId(String terminalId) {
		RegisterData data = (RegisterData) redisclient
				.getObject(CacheIdPrefix.TerminalIdToRegisterData + terminalId);
		return data;
	}

	/**
	 * @description 缓存用户登录的时间信息
	 * @param terminalId
	 *            终端账号
	 */
	public static void setSession(String terminalId) {
		redisclient.setObject(CacheIdPrefix.TermSession + terminalId,
				System.currentTimeMillis());
	}

	public enum RegisterState {
		DoesNotRegister, SessionTimeOut, OK, ;
	}

	/**
	 * @description 用户是否Session超时
	 * @param terminalId
	 *            终端账号
	 * @return RegisterState
	 */
	public static RegisterState getSession(String terminalId) {
		Long session = (Long) redisclient.getObject(CacheIdPrefix.TermSession
				+ terminalId);
		if (session == null) {
			return RegisterState.DoesNotRegister;
		}

		long currentTime = System.currentTimeMillis();
		if ((currentTime - session) > (MainConfig.getInstance().getSessionTimeOut())) {
			return RegisterState.SessionTimeOut;
		}
		redisclient.setObject(CacheIdPrefix.TermSession + terminalId,
				System.currentTimeMillis());
		return RegisterState.OK;
	}
}
