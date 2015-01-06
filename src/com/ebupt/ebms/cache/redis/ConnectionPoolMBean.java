package com.ebupt.ebms.cache.redis;

public interface ConnectionPoolMBean {

	public int getPoolTotalSize();

	public int getPoolActiveSize();

	public int getPoolBusySize();

}
