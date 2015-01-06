package com.ebupt.ebms.cache.redis;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.ebupt.ebms.entity.TermOperLog;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.TimeUtil;

import redis.clients.jedis.Jedis;

public class TestJedis {

	private static Logger log = Logger.getLogger(TestJedis.class);

	private String ip = "10.1.1.151";

	private int port = 6379;

	private Jedis jedis = null;

	public TestJedis(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public Jedis open() {
		do {
			try {
				jedis = new Jedis(ip, port);
			} catch (Exception e) {
				log.info(e.getMessage());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				continue;
			}
		} while ((jedis == null));

		return jedis;
	}

	public void close() {
		if (jedis != null) {
			try {
				jedis.quit();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
			jedis.disconnect();
			jedis = null;
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		TestJedis test = new TestJedis("10.1.1.151", 6379);
		final Jedis jedis = test.open();
		// String content = "ÄãºÃ";
		// // jedis.rpush("testcode", content.getBytes());
		// jedis.set(content, "123");
		// System.out.println(new String(jedis.get(content)));
		// jedis.publish("test", "hello,everyone");
		// System.out.println(jedis.del("hello"));
		// System.out.println(jedis.set("hello", "hahhaha"));
		// System.out.println(jedis.get("hello"));
		// System.out.println(jedis.expire("hello", 1));
		// System.out.println(jedis.get("hello"));

//		TermOperLog log = new TermOperLog();
//		log.setDescription("description");
//		log.setSerialno(NumberUtil.generatorId());
//		log.setTerminalid("000000000001");
//		log.setTime(TimeUtil.getTime());
//		Transcoder trans = new ObjectSerialzeTranscoder();
//		byte[] value = trans.encode(log);
//		jedis.rpush("ebms_TermOperLog".getBytes(), value);
//		
//		byte[] result = jedis.lpop("ebms_TermOperLog".getBytes());
//		if (result != null) {
//			Object obj = trans.decode(result);
//			System.out.println(obj);
//			TermOperLog log1 = (TermOperLog)obj;
//			System.out.println(log1.getDescription());
//			System.out.println(log1.getTime());
//		}
		
		final PubSubListener listener = new PubSubListener();
		 new Thread(new Runnable() {  
            @Override  
            public void run() {  
                jedis.psubscribe(listener, new String[] { "test*","hello*" });  
            }  
        }).start();  

//		test.close();
	}
}
