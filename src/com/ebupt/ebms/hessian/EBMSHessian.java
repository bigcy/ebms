package com.ebupt.ebms.hessian;

/**
 * @author QiChen Create on 2011-3-16
 * @version 1.0
 */
public interface EBMSHessian {

	/**
	 * ����taskflag��ֵ
	 * 
	 * @param terminalId
	 * @param flag
	 */
	public void setTaskFlag(String terminalId, String flag);

	/**
	 * ��ȡtaskflag��ֵ
	 * 
	 * @param terminalId
	 * @return
	 */
	public String getTaskFlag(String terminalId);

	/**
	 * ���ݼ�ɾ����Ӧ��ֵ
	 * 
	 * @param key
	 * @return
	 */
	public boolean deleteValueByKey(String key);

	/**
	 * �����ն����һ�����ӵ�ʱ���
	 * 
	 * @param terminalId
	 */
	public String getTermSelectTime(String terminalId);

	/**
	 * ����Ⱥ��ID��������
	 * 
	 * @param groupids
	 *            ����ö��ŷָ���
	 * @param taskType
	 *            01 ~ 12 ������������ 01 ����������id��һ��ֻ��һ�� ����������� 02 ������ 03 ��ʱ��Ϣ���� 04
	 *            ���������� 05 ����Ԥ������ 06 �����������
	 *            07����ͣ���ָ�����������Ҫ�Ƚ�playlistgroup��status�ĳ���Ӧ״̬�ٵ��ýӿ�
	 *            ,taskIds=serialno �ն���Ϣ�ϱ� 08 �ն˹���״̬�ϱ� 09 �ڲ������ϱ����� 10 ʵʱ�������� 11
	 *            �ϱ���־���� 12
	 * @param taskIds
	 *            :����id����Ӧÿ�����������id ���taskType = 03 �� ��ôtaskidsΪ��0:���� 1:���� 2:����
	 *            3:������գ�������ö��ŷָ��� taskIds������ǰ̨����ʱ����
	 *            operatorid:����Աid��Ϊ�˱�����ڵ�Ⱥ��id�ظ�����������Ӳ���Աid��ɸѡ�ն�
	 * @return
	 */
	public String setTasksByGroupids(String groupids, String taskType, String taskIds, String operatorid);

	/**
	 * �����ն�ID��������
	 * 
	 * @param terminalId
	 * @param taskType
	 *            01 ~ 12
	 * @param taskIds
	 *            ���taskType = 03����ôtaskids������ֵ������Ϊ��0��1��2��3��4��5��
	 * @param resourceXMLPath
	 *            ǰ̨����ʱ���Դ�null
	 * @param status
	 *            ǰ̨����ʱ���Դ�null taskIds������ǰ̨����ʱ���� ����ö��ŷָ���
	 * @return
	 */
	public String setTasksByTerminalIds(String terminalId, String taskType, String taskIds, String resourceXMLPath,
			String status);

	/**
	 * ��֤�����Ƿ��ͻ������ȷ��Ⱥ�飩
	 * 
	 * @param groupIds
	 *            (����ö��ŷָ�),playlistId(�����б�ID) startDate|endDate : ��ʼ����|��������
	 * @return String flag|reason(true or false | ���Ϊfalse ����ԭ�������߷ָ�)
	 */
	public String validation(String groupIds, String playlistid, String startDate, String endDate, String priority);

	/**
	 * 
	 * @param name
	 *            :��Ƶ�ļ����� size:ͼƬ��С 200x100
	 * @return ��Ƶ����ʱ��
	 */
	public String getVideoThumb(String name, String size);

	/**
	 * @param path
	 *            :����������·��
	 * @return
	 */
	public String softupdate(String factoryid, String version, String path);

	/**
	 * @param srcPathԭͼƬ·��
	 * @param midPathҪ���ӵ�ͼƬ·��
	 * @param destPathĿ��ͼƬ·��
	 * @param position����λ������
	 * @return destPath
	 */
	public String composite(String srcPath, String midPath, String destPath, String position);

	/**
	 * ��������
	 * 
	 * @param terminalId
	 * @param flag
	 *            0��ֹͣ���� 1����ʼ����
	 * @return
	 */
	public String continueGetSnapshot(String terminalId, String flag);

	/**
	 * �ʼ�����
	 * @param to �����������ַ
	 * @param subject �ʼ�����
	 * @param content �ʼ�����
	 * @return
	 */
	public String sendMail(String to, String subject, String content);
}