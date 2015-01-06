package com.ebupt.ebms.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;

public class NumberUtil {

	public static String getmsisdn11(String msisdn) {
		if (msisdn == null)
			return null;
		if (msisdn.length() > 11) {
			return msisdn.substring(msisdn.length() - 11);
		}
		return msisdn;
	}

	/**
	 * ���ɴ�ҵ����Ϣ������Id��
	 * 
	 * @param servicecode
	 * @return
	 */
	public static String generatorTaskId(String serviceId) {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replace("-", "");
		if (serviceId == null) {
			return id;
		}
		if (serviceId.length() > 3) {
			serviceId = serviceId.substring(0, 3);
		}
		id = serviceId + id.substring(3);
		return id;
	}

	/**
	 * ����32λId��
	 * 
	 * @param servicecode
	 * @return
	 */
	public static String generatorId() {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replace("-", "");
		return id;
	}

	/**
	 * ͨ������ŷ��ض�Ӧ��ҵ��
	 * 
	 * @param taskId
	 * @return
	 */
	public static String getServiceId(String taskId) {
		return taskId.substring(0, 3);
	}

	/**
	 * ɾ�������д���86��+86��Ϣ
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static String del86(String phoneNum) {
		if (phoneNum == null)
			return null;
		if (phoneNum.length() > 11) {
			return phoneNum.substring(phoneNum.length() - 11);
		} else {
			return phoneNum;
		}
	}

	/**
	 * ��ȡ�̶����ȵ������
	 * @param num
	 * @return
	 */
	public static String randomNum(int num) {
		Random random = new Random();
		String id = String.valueOf(Math.abs(random.nextLong()));
		while (id.length() < 32) {
			Random r = new Random();
			long l = Math.abs(r.nextLong());
			id += String.valueOf(l);
		}
		return id.substring(0, num);
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ����
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * �����ż�����ַ������ؽ����
	 * @param number
	 * @return
	 */
	
	public static Set<String> splitNumber(String number) {
		Set<String> result = new HashSet<String>();
		if (number != null && !"".equals(number)) {
			StringTokenizer st = new StringTokenizer(number, "��,");
			while (st.hasMoreTokens()) {
				result.add(st.nextToken());
			}
		}
		return result;
	}
	
	public static void main(String args[]) {
		System.out.println(NumberUtil.generatorId());
	}
}
