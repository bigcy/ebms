package com.ebupt.ebms.crontab;   

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ebupt.ebms.conf.MainConfig;
import com.ebupt.ebms.dao.ContentDownStatusDao;
import com.ebupt.ebms.dao.TermPlayLogDao;
import com.ebupt.ebms.entity.ContentDownStatus;
import com.ebupt.ebms.entity.TermPlayLog;
import com.ebupt.ebms.utils.FileUtil;
import com.ebupt.ebms.utils.NumberUtil;
import com.ebupt.ebms.utils.TimeUtil;

/**   
 * @author lishuhua
 * Create on 2011-3-17
 * @version 1.0  
 */
public class ReadLog {
	
	@Autowired
	@Qualifier("contentDownStatusDaoImpl")
	private ContentDownStatusDao contentDownStatusDao;
	
	@Autowired
	@Qualifier("termPlayLogDaoImpl")
	private TermPlayLogDao termPlayLogDao;

	private static final Logger log = Logger.getLogger(ReadLog.class);
	
	public void readlog() {
		//���ն˵������ļ��ж�ȡ
		String fileName = MainConfig.getInstance().getLogPath();
		String yesterday = TimeUtil.getBeforeDate(1);
		log.info(" ********** ���� " + yesterday + " ��־��������********** ");
		
//		String fileName = "D:\\temp";
//		String userHome = "user.home";
//	    String path = System.getProperty(userHome);
//	    System.out.println("Your Home Path: " + path + ",user dir===" +System.getProperty("user.dir"));
		
		if(fileName != null && !"".equals(fileName)){
			
			File dir = new File(fileName);

			// ȷ����·��ָ��һ��Ŀ¼
			if (dir.exists() && dir.isDirectory()) {

				// �г����н�βΪlog���ļ�
				File[] files = dir.listFiles(new FileFilter() {
					@Override
				  public boolean accept(File file) {
					  return file.isFile();
				  }
				});
				
				String encoding = "GBK"; // �ַ����(��������������� )

				// �����ж��Ƿ��н���ļ�¼����û����Ϊfalse
				boolean downloadExist = false;
				boolean playLogExist = false;
				

				// ѭ��������ļ����µ����е��ļ�
				if (files.length > 0) {
					for (int i = 0; i < files.length; i++) {
						
						if(files[i].getName().toString().split("\\_").length > 0){
							int length = files[i].getName().toString().split("\\_").length - 1;
							if(length == 2){
								String fileHead = files[i].getName().toString().substring(0, files[i].getName().toString().indexOf("_"));
								String fileTime = files[i].getName().toString().substring(files[i].getName().toString().lastIndexOf("_") + 1);
								String terminalid = files[i].getName().toString().substring(files[i].getName().toString().indexOf("_") + 1,
										files[i].getName().toString().lastIndexOf("_"));
								if(terminalid != null && !"".equals(terminalid)){
									terminalid = terminalid.trim();
								}
								
								// ���ʱ���ǲ��Ǹ�������ͬ����ͬ�ٽ��ж�ȡ�ļ��Ĳ���
								if (yesterday.equals(fileTime) && terminalid != null) {
//									System.out.println("�ļ���ͷΪ�� "+ fileHead + ",�ļ�ʱ��Ϊ��" +fileTime + ",�ն�IDΪ��" +terminalid);
									/***** ѭ���������ļ����µ�����Ϊ����������ļ�������---��ʼ *******/
									LineNumberReader reader = null;
									
									try {
										InputStreamReader isr = new InputStreamReader(new FileInputStream(files[i]), encoding);
										reader = new LineNumberReader(isr);

										String tempString = null;
										int line = 1;
										//��map��Ϊ�˷�ֹ�ظ������⣬����������־���
										Map<String, Object> map = new HashMap<String, Object>();
										
										if ("downloadlog".equals(fileHead)) {									
											// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
											while ((tempString = reader.readLine()) != null) {
												String contentid = null;
												String playlistid = null;
												String downtime = null;
												String status = null;
												String title = null;
												
													int fren = tempString.toString().split("\\|").length - 1;
													//�ж�"|"���ֵĴ����ǲ���4�Σ��������˵����ȡ���ļ����ݲ����Ҫ��
													if(fren == 4){
														// ��ֻ�ȡ��ÿ�����
														String[] str = tempString.split("\\|");
														
														contentid = str[0].trim();
														playlistid = str[1].trim();
														downtime = str[2].trim();
														status = str[3].trim();	
														title = str[4].trim();
														
														if(!"".equals(contentid) && !"".equals(playlistid) && !"".equals(status) && !"".equals(downtime)){
															ContentDownStatus con = new ContentDownStatus();
															con.setTerminalid(terminalid);
															con.setPlaylistid(playlistid);
															con.setContentid(contentid);
															con.setStatus(status);
															con.setDowntime(downtime);
															con.setContenttitle(title);
															con.setRecvtime(TimeUtil.getTime());
															con.setSerialno(NumberUtil.generatorId());
															String skey = terminalid + playlistid + contentid;
															map.put(skey, con);	
														}else{
															log.info("��ȡ��������־�ļ���<"+ line +">�и�ʽ��ȷ���п�ֵ���������δ��⣬������һ�е���ݣ�");
														}
													}else{
														log.info("��ȡ��������־�ļ���<"+ line +">�в����Ҫ�󣬴������δ��⣬������һ�е���ݣ�");
													}
													line++;
												}
											line = 1;
											downloadExist = true;
											reader.close();
											//���ļ��Ƶ�������Ϊ�ļ�������ļ�����ȥ
											String filePath = fileName + "/" + files[i].getName();
											String dirname = fileName + "/" + fileTime;
											FileUtil.moveFileToDirectory(filePath, dirname);
											/***************************** ����ݿ�Ĳ��� ��ʼ***********************************/	
											Iterator<?> iter = map.entrySet().iterator(); 
											List<Object> list = new ArrayList<Object>();
											while (iter.hasNext()) { 
											    Entry<?, ?> entry = (Entry<?, ?>) iter.next(); 
											    Object val = entry.getValue(); 
											    list.add(val);
											} 
											contentDownStatusDao.saveObjects(list);
											/******************************** ����ݿ�Ĳ��� ����********************************/
										}
										
										List<Object> playLogList = new ArrayList<Object>();
										if ("playlog".equals(fileHead)) {									
											// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
											while ((tempString = reader.readLine()) != null) {
												String tasktype = null;
												String taskid = null;
												String playtime = null;
												String belongtaskid = null;
												
													int fren = tempString.toString().split("\\|").length - 1;
													//�ж�"|"���ֵĴ����ǲ���3�Σ��������˵����ȡ���ļ����ݲ����Ҫ��
													if(fren == 3){
														// ��ֻ�ȡ��ÿ�����
														String[] str = tempString.split("\\|");
														
														tasktype = str[0].trim();	
														taskid = str[1].trim();
														playtime = str[2].trim();
														belongtaskid = str[3].trim();
 
														if(!"".equals(taskid) && !"".equals(belongtaskid) && !"".equals(tasktype) && !"".equals(playtime)){
															TermPlayLog playLog = new TermPlayLog();
															playLog.setSerialno(NumberUtil.generatorId());
															playLog.setTaskType(tasktype);
															playLog.setTaskid(taskid);
															playLog.setPlaytime(playtime);
															playLog.setBelongtaskid(belongtaskid);
															playLog.setRecvtime(TimeUtil.getTime());
															playLog.setTerminalid(terminalid);
															playLog.setDatetime(yesterday);
															playLogList.add(playLog);	
														}else{
															log.info("��ȡ�Ĳ�����־�ļ������е�<"+ line +">�и�ʽ��ȷ���п�ֵ���������δ��⣬������һ�е���ݣ�");
														}
													}else{
														log.info("��ȡ�Ĳ�����־�ļ������е�<"+ line +">�в����Ҫ�󣬴������δ��⣬������һ�е���ݣ�");
													}
													line++;
												}
											line = 1;
											playLogExist = true;
											reader.close();
											//���ļ��Ƶ�������Ϊ�ļ�������ļ�����ȥ
											String filePath = fileName + "/" + files[i].getName();
											String dirname = fileName + "/" + fileTime;
											FileUtil.moveFileToDirectory(filePath, dirname); 
											//��¼��⣬������������ķ�ʽ���д���
											termPlayLogDao.saveObjects(playLogList);
										}
									} catch (IOException e) {
										e.printStackTrace();
										log.error(e.getMessage(),e);
									} finally {
										if (reader != null) {
											try {
												reader.close();
											} catch (IOException e1) {
											}
										}
									}
									/****** ѭ���������ļ���������Ϊ����������ļ�������---���� *******/
								}
							}
						}else{
							log.info("���ļ����µ��ļ�������û�к���'_'�ģ�");
						}
						
					}
					
					if (!downloadExist) {
						log.info("�����⵽" + fileName + "�ļ���������Ϊ��" + yesterday + " ��������־�����ڻ����ļ��������ʽ����ȷ�������ϴ����ļ���");
					}else {
						log.info("�����⵽" + fileName + "�ļ���������Ϊ��" + yesterday + " ��������־���ڣ����������ɹ���");
					}
					if (!playLogExist) {
						log.info("�����⵽" + fileName + "�ļ���������Ϊ��" + yesterday + " �Ĳ�����־�����ڻ����ļ��������ʽ����ȷ�������ϴ����ļ���");
					}else {
						log.info("�����⵽" + fileName + "�ļ���������Ϊ��" + yesterday + " �Ĳ�����־���ڣ����������ɹ���");
					}
				} else {
					log.info("���ļ����²����ڿɹ��������ļ���");
				}

			} else {
				log.info("�Ҳ���ָ����·�����ļ��У������ն˷�����·�������Ƿ���ȷ����ȡ��fileName =" + fileName);
			}
		}else{
			log.info("��Ч��·�������������ļ���logPath�����Ƿ���ȷ��");
		}
		log.info(" ********** ���� " + yesterday + " ��־�������********** ");
		
	}

}
  