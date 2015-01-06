package com.ebupt.ebms.hessian;   

import com.caucho.hessian.client.HessianProxyFactory;
import com.ebupt.ebms.cache.RegisterInfoCache;
/**   
 * @author QiChen
 * Create on 2011-1-20
 * @version 1.0  
 */
public class TestHessian {
	public static void main(String[] args) {
//		String url = "http://127.0.0.1:8080/ebms/hessian";
		String url = "http://10.1.60.108:8086/ebms/hessian";
//		String url = "http://10.1.1.151:8096/ebmstest/hessian";
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			
			EBMSHessian service = (EBMSHessian) factory.create(EBMSHessian.class, url);
//			service.softupdate("1.00", "/home/g3media/wwwroot/ebms/public/softinfo/updatepackage.tar");
			//��������ʹ��
//			System.out.println(service.getTaskFlag("000000000001"));
//			service.continueGetSnapshot("000000000003", "0");
//			service.setTaskFlag("000100102001", "0");
//			service.setTaskFlag("000900000001", "1");
//			service.composite("/home/g3media/pic/untitled.png", "/home/g3media/pic/mid.jpg", "/home/g3media/pic/dest.jpg", "20,20,182,293");
//			System.out.println(service.getTaskFlag("000100100001"));
//			service.deleteValueByKey("ResourceXMLPathe2b7e35f5a254cdb543dd33c75a9ee1f69276d6b78a505b30a0a5c33c5b3450c8");
//			service.deleteValueByKey("ResourceXMLPathe55bb07bd12d4f6e80783553ea0da15891e64b146c5f4aa7bb7b10dfb4479466");
//			service.deleteValueByKey("TerminalIdToGroupId000100100001");
//			service.deleteValueByKey("TerminalsByGroupIde55bb07bd12d4f6e80783553ea0da157");
//			service.deleteValueByKey("TerminalsByGroupIde55bb07bd12d4f6e80783553ea0da158");
//			//����������groupid\ type \ playlistid
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "01", "91e64b146c5f4aa7bb7b10dfb4479466"); 
			
			//�������������
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "02", "8ff4637a0b9bd12c022e2af1f2cf5f0c");
			
			// ����������			
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "03", "0,1,2,3"); 
			
			//��ʱ��Ϣ������
//			service.setTasksByGroupids("e5r5bb07bd12d4f6e80783553ea0da157", "04", "f7963f78deb345d09057777b464af08c");
			
			//����������
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "05", "40a68533e8ce4cf29dc6dc744a8567ae");
			
			//����Ԥ������
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "06", "0b9bd12c022e25f0c8ff4637aaf1f2cf");
			
			//����������� status in ('1','2','3')
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "07", "9c48b01bd3ee4c5788b6c3087633d916");
			
			//�ն������ϱ�
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "08", "");
			
			//�ն˹���״̬�ϱ�
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "09", "c81176c4b507841533d953e040be9ca4");
			
			//�ն��ڲ������ϱ�
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "10", "1533d953e040be9ca4c81176c4b50784");
			
			//ʵʱ����������
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "11", "");
			
			//�ն���־�ϱ�
//			service.setTasksByGroupids("e55bb07bd12d4f6e80783553ea0da157", "12", "playlog");
			
			service.sendMail("lishuhua@ebupt.com", "mail test", "hello"); 
			
			//��������ʹ��
//			System.out.println("getTaskFlag:" + service.getTaskFlag("000100100010"));
			
			//����key�������
//			System.out.println(service.deleteValueByKey("ResourceXMLPath"));
			
//			RegisterInfoCache.getRegisterDataByTerminalId("");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
  