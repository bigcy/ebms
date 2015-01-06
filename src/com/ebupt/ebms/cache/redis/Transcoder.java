package com.ebupt.ebms.cache.redis;

public interface Transcoder {
	
	public byte[] encode(Object o);
	
	public Object decode(byte[] b);
}
