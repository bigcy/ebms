package com.ebupt.ebms.cache;

import com.ebupt.ebms.cache.redis.RedisCacheStart;
import com.ebupt.ebms.cache.redis.RedisClient;
import com.ebupt.ebms.entity.TermOperLog;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**
 * ´ýÖ´ÐÐµÄSQL»º´æ
 * 
 * @author zhangqian 2011-04-25
 */
public class SQLCache {

	private static final RedisClient redisclient = RedisCacheStart.createRedisClient();

	public static void put(String sql) {
		redisclient.rpush("ebms_sql", sql.getBytes());
	}

	public static String get() {
		byte[] sql = redisclient.lpop("ebms_sql");
		if (sql != null)
			return new String(sql);
		else
			return null;
	}

	public static void putTermOperLog(String terminalid, String description) {
		TermOperLog log = new TermOperLog();
		log.setDescription(description);
		log.setSerialno(NumberUtil.generatorId());
		log.setTerminalid(terminalid);
		log.setTime(TimeUtil.getTime());
		redisclient.rpush("ebms_TermOperLog", log);
	}
	
	public static void putTermOperLog(TermOperLog log) {
		
		redisclient.rpush("ebms_TermOperLog", log);
	}

	public static TermOperLog getTermOperLog() {
		TermOperLog log = (TermOperLog) redisclient.lpopObject("ebms_TermOperLog");
		return log;
	}

}
