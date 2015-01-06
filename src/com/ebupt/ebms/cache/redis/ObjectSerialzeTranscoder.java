package com.ebupt.ebms.cache.redis;

public class ObjectSerialzeTranscoder implements Transcoder {

	public Object decode(byte[] b) {
		if (b != null) {
			byte[] bo =SerializingUtil.decompress(b);
			return SerializingUtil.deserialize(bo);
		}
		return null;
	}

	public byte[] encode(Object o) {
		byte[] b = SerializingUtil.serialize(o);
		return SerializingUtil.compress(b);
	}

}
