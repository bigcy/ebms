package com.ebupt.ebms.servlet;

import java.io.Serializable;

public class RegisterData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * �û�ͨ������Ϣ����
	 */
	private String nonce = null;
	
	/**
	 * �û��ش�����Ϣ������֤��Ϣ
	 */
	private String opaque = null;

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getOpaque() {
		return opaque;
	}

	public void setOpaque(String opaque) {
		this.opaque = opaque;
	}
	
}
