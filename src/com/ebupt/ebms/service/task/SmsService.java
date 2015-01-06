package com.ebupt.ebms.service.task;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ebupt.ebms.cache.redis.RedisCacheStart;
import com.ebupt.ebms.cache.redis.RedisClient;

/**
 * @author zhangqian Create on 2011-10-20
 * @version 1.0
 */
@Service
public class SmsService {

	private static final Logger log = Logger.getLogger(SmsService.class);

	private static final RedisClient redisclient = RedisCacheStart.createRedisClient();

	public void sendSMS(String phone, String content) {
		log.info("phone=" + phone + " content=" + content);
		content = phone + "|0||" + content;
		redisclient.rpush("SEND:SMS", content.getBytes());
	}

}
