--
--  Copyright(C) 2000 EASTCOM-BUPT Inc.
--
--  Created By          : $Author: cvssjyw $
--  Created At          : $Date: 2004/12/09 10:40:32 $
--  Last Revision       : $Revision: 1.1 $
--  Description         :
--

select max(taskid) from news where (taskid in (select taskid from news where (taskid-1) in (select taskid from news) ));

�زı���Ŀ��ģ��������������Ҫ���usedcount�Ƿ�������Ĭ��ֵΪ0������

������Կ��a79352ec7f1946ebaaec2ceb3db64f98
Insert into TERMFACTORY (FACTORYID,CREATETIME,NAME,SECRETKEY) values ('000','20111104163619','����','a79352ec7f1946ebaaec2ceb3db64f98');

Ҫ���templatedir�����Ƿ��programid�ֶ�

//��������Ա
Insert into OPERATOR (OPERATORID,CREATETIME,EMAIL,LOCATIONID,LOGINNAME,PASSWD,PHONE,ROLEID,SHOWNAME,SUPEROPERATORID,OPERATORLEVEL,LASTLOGINTIME,NEWLOGINTIME,LASTLOGINIP,NEWLOGINIP) values ('-1','20110305','admin@ebupt.com','0','admin','e10adc3949ba59abbe56e057f20f883e','18810606316','0','��������Ա',null,'0','20111122104834','20111122105812','10.1.81.225','10.1.81.197');

//Ĭ��ȫ��ģ����صı�ע����Ĭ��ģ���Ƿ񱣴棺content/templates/defaulttemplate.jpg
Insert into SUBTEMPLATE (SUBTEMPLATEID,ALPHA,DESCRIPTION,HIDE,MPLNAME,POSITION,TEMPLATEID,TYPE,ZLEVEL) values ('1','0',null,'0',null,'0,0,1280,720','1','video','0');
Insert into TEMPLATE (TEMPLATEID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,HEIGHT,NAME,PATH,STATUS,TPLDESCPATH,USEDCOUNT,WIDTH,ISOPEN) values ('1',null,null,'20111126152633',null,'ģ��������Ϣ','720','ȫ��ģ��','content/templates/defaulttemplate.jpg','P','ģ���ļ���Ӧ�����ļ�·��',0,'1280','F');
Insert into TEMPLATEDIR (SERIALNO,DIRECTORYID,TEMPLATEID) values ('6fd11169cc7189c317f58956ef71776f','-1','1');
Insert into TEMPLATEOPERATOR (SERIALNO,OPERATORID,TEMPLATEID,TYPE,SHARERS) values ('aee7d2bf07795e2540e0408a6d2d42c6','-1','1','0',null);


//�澯��Ϣ��
REM INSERTING into ALARMCODEVALUE
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('0001','�ɹ�');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('0002','ʧ��');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1001','�ն˿���');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1002','�ն���֤');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1003','������ѯ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1004','����Ͽ�');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1005','��������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1006','�ն�����');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1007','�ն˻���');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1008','�ն�����');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1009','��������ʼ����');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1010','���������������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1011','��������������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1012','����ʱ�����������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1013','�ػ�ʱ�����������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1014','����ʱ��β��������� ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1015','�����ٶȲ���������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1016','��������ַ���������� ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1017','�������˿ڲ��������� ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1018','������־�ϴ�ʱ�����������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1019','������־�ϴ�ʱ�����������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1020','������������������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1021','���������������������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1022','����IP��ַ���������� ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1023','�ն˲����ϱ�');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1024','�ն���������Ҫ��������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1025','�ն�ץ��');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1026','�ն�����');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1027','�ն�ִ�м�ʱ��Ϣ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1028','�ն�ִ�в岥����');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1029','�ն˿�ʼִ�в������� ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1030','�ն˿�ʼִ�е�Ƭ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1031','�ն�Ӳ���Զ�����');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1032','�ն�Ӳ��״̬�ϱ�');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1033','��Դ�嵥��ȡ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1034','������־�ϱ�');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1035','�ն�����ʱ��ο�ʼ��Ӧ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1036','�ն�����ʱ��ν�����Ӧ ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1037','�ն˱���ʱ��ͬ��');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1038','�ն˷����澯��Ϣ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1039','�ػ�');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2001','���̿ռ䲻��');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2002','������Ϣ����ʧ��');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2003','��������ʧ��');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2004','Ӳ��mount����');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2005','�����쳣');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2006','LED�������쳣');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2007','�洢�зǷ����ݣ�����MD5У�鲻�ɹ���');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('3001','ftp����ʧ��');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('3002','�ļ�������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2008','xml������');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2009','��־�ϴ�ʧ��');

REM INSERTING into PROGRAM

Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('1',null,null,'20111025181842',null,null,'1','YYYY��MM��DD�� ����WK hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('2',null,null,'20111025182117',null,null,'1','YYYY��MM��DD�� hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('3',null,null,'20111025182125',null,null,'1','MM��DD�� ����WK hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('4',null,null,'20111025182134',null,null,'1','MM��DD�� ����WK','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('5',null,null,'20111025182144',null,null,'1','MM��DD�� hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('6',null,null,'20111025182152',null,null,'1','hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('7',null,null,'20111025182152',null,null,'1','hh:mm','P',10,'time',0,'F');

Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('3e942ee9ae6f9656b1fa15d36e69e565',null,'123','FFFFFF','simhei','36',null,0,'1',null,null,'YYYY��MM��DD�� ����WK hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('79ac23eaaf70f372690d26875a976fd7',null,'123','FFFFFF','simhei','36',null,0,'2',null,null,'YYYY��MM��DD�� hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('78eb3378ecc4ebf431ecb8b0d7626578',null,'123','FFFFFF','simhei','36',null,0,'3',null,null,'MM��DD�� ����WK hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('c60159b730dd6815b2f3c35b96be99ae',null,'123','FFFFFF','simhei','36',null,0,'4',null,null,'MM��DD�� ����WK','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('b1cf33c3e7439904ab40685b2a5b08f3',null,'123','FFFFFF','simhei','36',null,0,'5',null,null,'MM��DD�� hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('f351754d8ba40a32e6dc348caa524fd9',null,'123','FFFFFF','simhei','36',null,0,'6',null,null,'hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('6da524fd9f351754d8c348caba40a32e',null,'123','FFFFFF','simhei','36',null,0,'7',null,null,'hh:mm','0','0');

REM INSERTING into AREA
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('1','������Ӫҵ��','39.947751','afc111f00366aff8f5f36366309a6e84','116.319434','�����������Ŷ������˼�ɽׯ��ȹ������ϯλ');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('2','�廪����Ʒ�Ƶ�','40.004207','afc111f00366aff8f5f36366309a6e84','116.330704','�������廪԰�ֵ��廪��ѧ16��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('3','֪��·Ӫҵ��','39.981531','afc111f00366aff8f5f36366309a6e84','116.355289','������֪��·6�Ž�����ʴ���һ��A102');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('4','��ƽ������Ӫҵ��','40.089547','408078263f8a721b0ea5e6e81a8aae25','116.339675','��ƽ���������򱱵��԰��ҵ��33��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('5','��ƽ����Ӫҵ��','40.227429','408078263f8a721b0ea5e6e81a8aae25','116.263122','��ƽ����ѧ·8��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('6','��ƽ��ӪӪҵ��','40.086946','408078263f8a721b0ea5e6e81a8aae25','116.372344','��ƽ����С�����Ӫ������԰��ҵ17��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('7','��լ��Ӫҵ��','39.966536','723a25c587cdf1b5fd2f82f8b402ff59','116.41389','����������������3��¥һ��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('8','����Ӫҵ��','39.928494','723a25c587cdf1b5fd2f82f8b402ff59','116.424173','�����������ϴ��62��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('9','���Ӫҵ��','39.939493','723a25c587cdf1b5fd2f82f8b402ff59','116.417949','������������·10��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('10','��ֱ��Ӫҵ��','39.945072','3b80e6795dfda83f0f5ed4232a450bcc','116.365499','��������ֱ���ڴ�ֺ��ڽ�56��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('11','��̳�Ͻ�Ӫҵ��','39.919501','3b80e6795dfda83f0f5ed4232a450bcc','116.353453','��������̳�Ͻּ�12��һ���ϲ� ');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('12','��ʤ��Ӫҵ��','39.961126','3b80e6795dfda83f0f5ed4232a450bcc','116.38627','����������̳���9��Ժ6��¥1��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('13','����Ӫҵ��','39.968914','3b80e6795dfda83f0f5ed4232a450bcc','116.38937','���������´��26��1��¥1��108��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('14','��̳����Ӫҵ��','39.881796','69b60131c2db2f805c1e604fb4f04242','116.429467','��������̳����5��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('15','��Ȼ��Ӫҵ��','39.871749','69b60131c2db2f805c1e604fb4f04242','116.394217','��������ұ�56��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('16','������Ӫҵ��','39.903124','69b60131c2db2f805c1e604fb4f04242','116.425977','����������������40��1��һ�㱱��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('17','�������Ϲ㳡Ӫҵ��','40.471514','2945faa245079f97e9af1d217eb62aa2','115.992867','������������������ҵ�㳡G4��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('18','���챱��Ӫҵ��','40.465635','2945faa245079f97e9af1d217eb62aa2','115.97666','�����������򱱽�1��¥1��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('19','��ͷ��Ӫҵ��','39.94245','52455988531ddd7dc43dedd209f4ac44','116.117234','��ͷ��������·111��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('20','ʯ��ɽ·Ӫҵ��','39.913881','43ec565de97133e88e5b59bdefe2fe12','116.2202','ʯ��ɽ��ʯ��ɽ·23���д�����һ��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('21','ʵ�˶���Ӫҵ��','39.938939','43ec565de97133e88e5b59bdefe2fe12','116.197428','ʯ��ɽ��ʵ�˴��1�ź��ط���һ��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('22','ͨ��ͨ�����Ӫҵ��','39.919171','fe70309af71cae1df55c9df9f36db097','116.691856','ͨ����ͨ�����23-8-9��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('23','ͨ�ݴ侰����Ӫҵ��','39.894278','fe70309af71cae1df55c9df9f36db097','116.661369','ͨ�����ſ�����118��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('24','˳����ɽ�Ͻ�Ӫҵ��','40.142303','4ae06fc815e6a9e4cdbb6cc3d65432d6','116.657524','˳����ɽ�Ͻ�5��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('25','˳������Ӫҵ��','40.066854','4ae06fc815e6a9e4cdbb6cc3d65432d6','116.59049','˳����������ǰһ��3��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('26','ƽ�����͹㳡Ӫҵ��','40.15207','15a0bbcfb59505cb0e5d9be79f829fe1','117.112356','ƽ����ƽ��·57��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('27','ƽ�����ӪӪҵ��','40.144166','15a0bbcfb59505cb0e5d9be79f829fe1','117.029433','ƽ�����Ӫ�򶫲���ҵ¥');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('28','�ѷ�·Ӫҵ��','39.872041','e356500bb3e2e3644ccd556a9f857c3d','116.441917','��̨����ׯ�ѷ�·1��һ�����');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('29','����԰Ӫҵ��','39.872018','e356500bb3e2e3644ccd556a9f857c3d','116.430894','��̨����ׯ����԰һ��29��¥1-1-1');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('30','�����ع�¥Ӫҵ��','40.382239','195a80ae883ea6ca3f72ea0dae0641b1','116.853547','�����ع�¥����ֵ��Ŵ�¥');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('31','������ʮ�ﱤӪҵ��','40.372662','195a80ae883ea6ca3f72ea0dae0641b1','116.82687','����������С��14��9��102');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('32','�������Ӫҵ��','40.296605','0370c7eb7ad7c069a5af0709211f5c52','116.688766','����������������ׯ����������һ�����');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('33','����Ŷɺ�Ӫҵ��','40.365852','0370c7eb7ad7c069a5af0709211f5c52','116.473434','�������Ŷɺ���ƿ���6��Ժ');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('34','��ɽ����Ӫҵ��','39.729875','9e9178cfe318836e791b0d6dd4bca3c3','116.156718','��ɽ�����糤�綫·16��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('35','��ɽ����Ӫҵ��','39.653753','9e9178cfe318836e791b0d6dd4bca3c3','116.075495','��ɽ���������ԷС��2��¥9�ŵ���');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('36','���˾��ü���������Ӫҵ��','39.807666','ef718e2b0e0209a11fd6c6d44dae4c31','116.51454','�������ü�����������ﱱ·10��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('37','����������Ӫҵ��','39.798824','ef718e2b0e0209a11fd6c6d44dae4c31','116.345508','�����������������������111��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('38','�⻪·Ӫҵ��','39.919137','9344d6acbea98b1c37d9f1f3730a8e27','116.480851','�������⻪·2������100д��¥G��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('39','����Ӫҵ��','40.004438','9344d6acbea98b1c37d9f1f3730a8e27','116.474217','��������˳�����29��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('40','�Ž��Ӫҵ��','39.934174','9344d6acbea98b1c37d9f1f3730a8e27','116.470751','�������׼�ׯ����һ�����԰���¡����');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('23c49c23000be5cef89450279fbbe9b1','�ϵ�һ��','40.040613  ','afc111f00366aff8f5f36366309a6e84','116.314092','�ϵ�һ��xx��');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('e5cef89450279fbbe9b123c49c23000b','�ϵ����','40.045807  ','afc111f00366aff8f5f36366309a6e84','116.315312','�����к������ϵ����');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('3000be5cef89450279fbbe9b123c49c2','�ϵ��߽�','40.048777  ','afc111f00366aff8f5f36366309a6e84','116.304101','�����к������ϵ��߽�');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('b123c49c23000be5cef89450279fbbe9','�ϵ�����','40.048213 ','408078263f8a721b0ea5e6e81a8aae25','116.309947','�����к������ϵ�����');

REM INSERTING into LOCATION
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('43ec565de97133e88e5b59bdefe2fe12','ʯ��ɽ��','019');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('afc111f00366aff8f5f36366309a6e84','������','011');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('ed2674b37eee02d6457740093f338723','����','010');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('408078263f8a721b0ea5e6e81a8aae25','��ƽ��','012');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('69b60131c2db2f805c1e604fb4f04242','������','016');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('723a25c587cdf1b5fd2f82f8b402ff59','������','013');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('3b80e6795dfda83f0f5ed4232a450bcc','������','015');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('2945faa245079f97e9af1d217eb62aa2','������','017');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('52455988531ddd7dc43dedd209f4ac44','��ͷ����','018');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('fe70309af71cae1df55c9df9f36db097','ͨ����','020');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('4ae06fc815e6a9e4cdbb6cc3d65432d6','˳����','021');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('15a0bbcfb59505cb0e5d9be79f829fe1','ƽ����','022');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('e356500bb3e2e3644ccd556a9f857c3d','��̨��','023');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('195a80ae883ea6ca3f72ea0dae0641b1','������','024');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('0370c7eb7ad7c069a5af0709211f5c52','������','025');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('9e9178cfe318836e791b0d6dd4bca3c3','��ɽ��','026');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('ef718e2b0e0209a11fd6c6d44dae4c31','������','027');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('9344d6acbea98b1c37d9f1f3730a8e27','������','028');

//���Ӷ��ҵĹ��������Ĺ��ܵ�֧�֣�����readstatus�ֶΣ�informix����ͨ��
alter table imessagegroup add readstatus varchar(1) default 'N';
alter table playlistgroup add readstatus varchar(1) default 'N';
alter table resourcedeliver add readstatus varchar(1) default 'N';

//���������
truncate table CONTENT            ;
truncate table CONTENTDIR         ;
truncate table CONTENTDOWNSTATUS  ;
truncate table CONTENTOPERATOR    ;
truncate table DEMO               ;
truncate table DIRECTORY          ;
truncate table IMESSAGE           ;
truncate table IMESSAGEGROUP      ;
truncate table LOCATION           ;
truncate table OPERATELOG         ;
truncate table OPERATOR           ;
truncate table OPERATORGROUP      ;
truncate table OPERATORPOWER      ;
truncate table PLAYLIST           ;
truncate table PLAYLISTDIR        ;
truncate table PLAYLISTGROUP      ;
truncate table PLAYLISTITEM       ;
truncate table PLAYLISTOPERATOR   ;
truncate table PLAYLISTTASKITEM   ;
truncate table PLAYLISTTASKITEM_GX;
truncate table POWERINFO          ;
truncate table PROGRAM            ;
truncate table PROGRAMDIR         ;
truncate table PROGRAMITEM        ;
truncate table PROGRAMOPERATOR    ;
truncate table ROLE               ;
truncate table ROLEPOWER          ;
truncate table SOFTINFO           ;
truncate table SOFTINFOGROUP      ;
truncate table SOFTINFOTASKITEM   ;
truncate table SUBTEMPLATE        ;
truncate table TEMPLATE           ;
truncate table TEMPLATEDIR        ;
truncate table TEMPLATEOPERATOR   ;
truncate table TERMALARM          ;
truncate table TERMCONFIG         ;
truncate table TERMCONFIGGROUP    ;
truncate table TERMCONFIGREPORT   ;
truncate table TERMCONFIGTASKITEM ;
truncate table TERMFACTORY        ;
truncate table TERMGROUP          ;
truncate table TERMGROUPITEM      ;
truncate table TERMINAL           ;
truncate table TERMOPERATOR       ;
truncate table TERMOPRELATION     ;
truncate table TERMPLAYING        ;
truncate table TERMPLAYLOG        ;
truncate table TERMSTATUSRECORD   ;
truncate table TERMSTATUSTEMP     ;
truncate table TERMWORKING        ;
truncate table UPLOADLOGREPORT    ;
truncate table USERGROUP          ;
truncate table WEATHER            ;
truncate table WEATHERTASKITEM    ;


//�����
truncate table CONTENT            ;
truncate table CONTENTDIR         ;
truncate table CONTENTDOWNSTATUS  ;
truncate table CONTENTOPERATOR    ;
truncate table IMESSAGE           ;
truncate table IMESSAGEGROUP      ;
truncate table OPERATELOG         ;
truncate table PLAYLIST           ;
truncate table PLAYLISTDIR        ;
truncate table PLAYLISTGROUP      ;
truncate table PLAYLISTITEM       ;
truncate table PLAYLISTOPERATOR   ;
truncate table PLAYLISTTASKITEM   ;
truncate table PROGRAM            ;
truncate table PROGRAMDIR         ;
truncate table PROGRAMITEM        ;
truncate table PROGRAMOPERATOR    ;
truncate table SOFTINFO           ;
truncate table SOFTINFOGROUP      ;
truncate table SOFTINFOTASKITEM   ;
truncate table SUBTEMPLATE        ;
truncate table TEMPLATE           ;
truncate table TEMPLATEDIR        ;
truncate table TEMPLATEOPERATOR   ;
truncate table TERMALARM          ;
truncate table TERMPLAYING        ;
truncate table TERMPLAYLOG        ;
truncate table TERMSTATUSRECORD   ;
truncate table TERMSTATUSTEMP     ;
truncate table TERMWORKING        ;
truncate table UPLOADLOGREPORT    ;
truncate table WEATHER            ;
truncate table WEATHERTASKITEM    ;