package com.ebupt.ebms.servlet;

public enum HTTPRespCode {

	OK(200),				//����
	SessionOut(408),		//Session��ʱ
	AuthFailed(401),		//��Ȩʧ��
	Refused(403),			//�ܾ�����
	ParaError(409),			//��������
	ServiceError(410),		//ҵ�����
	SysError(411),			//ϵͳ����
	;
	
	private int value;

	private HTTPRespCode(int value) {
		this.value = value;
	}// ˽�й��졣

	public int value() {
		return value;
	}
}
