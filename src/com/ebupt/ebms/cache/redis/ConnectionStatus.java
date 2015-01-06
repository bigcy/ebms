package com.ebupt.ebms.cache.redis;

public enum ConnectionStatus {
	/**
	 * socket��״̬����ʹ��
	 */
	ACTIVE,
	/**
	 * socket��״̬����ʹ�ã�����æ
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
