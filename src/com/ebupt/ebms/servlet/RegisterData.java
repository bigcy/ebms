package com.ebupt.ebms.servlet;

import java.io.Serializable;

public class RegisterData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户通过此信息加密
	 */
	private String nonce = null;
	
	/**
	 * 用户回传此信息用于验证消息
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
