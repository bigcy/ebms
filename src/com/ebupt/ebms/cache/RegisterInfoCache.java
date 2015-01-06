package com.ebupt.ebms.cache;

import com.ebupt.ebms.cache.redis.RedisCacheStart;
import com.ebupt.ebms.cache.redis.RedisClient;
import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.servlet.RegisterData;

/**
 * �ն�ע�Ỻ��
 * 
 * @author zhangqian 2010-01-16
 */
public class RegisterInfoCache {

	private static final RedisClient redisclient = RedisCacheStart
			.createRedisClient();

	/**
	 * �����볧��ID��Ӧ��ϵ
	 * 
	 * @param factoryId
	 * @return
	 */
	public static String getPasswdByFactoryId(String factoryId) {

		return (String) redisclient.getObject(CacheIdPrefix.PasswdtoFactoryId
				+ factoryId);
	}

	/**
	 * �����볧��ID��Ӧ��ϵ
	 * 
	 * @param passwd
	 * @param factoryId
	 */
	public static void setPasswdtoFactoryId(String passwd, String factoryId) {

		redisclient.setObject(CacheIdPrefix.PasswdtoFactoryId + factoryId,
				passwd);
	}

	/**
	 * ��֤ʧ�ܴ���
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
	 * ��֤ʧ�ܴ���
	 * 
	 * @param terminalId
	 * @param num
	 *            300:��λ��
	 */
	public static void setAuthFailedTimes(String terminalId, Integer num) {

		redisclient.setObject(CacheIdPrefix.AuthFailedTimes + terminalId, num,
				30);
	}

	/**
	 * �ն��ʺ���ע����Ϣ�Ķ�Ӧ��ϵ
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
	 * ͨ���ն��ʺŻ�ȡ�ն�ע����Ϣ
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
	 * @description �����û���¼��ʱ����Ϣ
	 * @param terminalId
	 *            �ն��˺�
	 */
	public static void setSession(String terminalId) {
		redisclient.setObject(CacheIdPrefix.TermSession + terminalId,
				System.currentTimeMillis());
	}

	public enum RegisterState {
		DoesNotRegister, SessionTimeOut, OK, ;
	}

	/**
	 * @description �û��Ƿ�Session��ʱ
	 * @param terminalId
	 *            �ն��˺�
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
