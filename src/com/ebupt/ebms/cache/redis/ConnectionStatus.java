package com.ebupt.ebms.cache.redis;

public enum ConnectionStatus {
	/**
	 * socket的状态，可使用
	 */
	ACTIVE,
	/**
	 * socket的状态，被使用，出于忙
	 */
	BUSY,
	;
	
	public int status() {
		switch(this) {
		case ACTIVE:
			return 0;
		case BUSY:
			return 1;
		}
		return -1;
	}
	
}
