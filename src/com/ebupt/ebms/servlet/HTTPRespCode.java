package com.ebupt.ebms.servlet;

public enum HTTPRespCode {

	OK(200),				//正常
	SessionOut(408),		//Session超时
	AuthFailed(401),		//鉴权失败
	Refused(403),			//拒绝访问
	ParaError(409),			//参数错误
	ServiceError(410),		//业务错误
	SysError(411),			//系统错误
	;
	
	private int value;

	private HTTPRespCode(int value) {
		this.value = value;
	}// 私有构造。

	public int value() {
		return value;
	}
}
