--
--  Copyright(C) 2000 EASTCOM-BUPT Inc.
--
--  Created By          : $Author: cvssjyw $
--  Created At          : $Date: 2004/12/09 10:40:32 $
--  Last Revision       : $Revision: 1.1 $
--  Description         :
--

select max(taskid) from news where (taskid in (select taskid from news where (taskid-1) in (select taskid from news) ));

素材表、节目表、模板表、播放任务表，需要检查usedcount是否设置了默认值为0的属性

厂商密钥：a79352ec7f1946ebaaec2ceb3db64f98
Insert into TERMFACTORY (FACTORYID,CREATETIME,NAME,SECRETKEY) values ('000','20111104163619','北京','a79352ec7f1946ebaaec2ceb3db64f98');

要检查templatedir表，看是否多programid字段

//超级管理员
Insert into OPERATOR (OPERATORID,CREATETIME,EMAIL,LOCATIONID,LOGINNAME,PASSWD,PHONE,ROLEID,SHOWNAME,SUPEROPERATORID,OPERATORLEVEL,LASTLOGINTIME,NEWLOGINTIME,LASTLOGINIP,NEWLOGINIP) values ('-1','20110305','admin@ebupt.com','0','admin','e10adc3949ba59abbe56e057f20f883e','18810606316','0','超级管理员',null,'0','20111122104834','20111122105812','10.1.81.225','10.1.81.197');

//默认全屏模板相关的表，注意检查默认模板是否保存：content/templates/defaulttemplate.jpg
Insert into SUBTEMPLATE (SUBTEMPLATEID,ALPHA,DESCRIPTION,HIDE,MPLNAME,POSITION,TEMPLATEID,TYPE,ZLEVEL) values ('1','0',null,'0',null,'0,0,1280,720','1','video','0');
Insert into TEMPLATE (TEMPLATEID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,HEIGHT,NAME,PATH,STATUS,TPLDESCPATH,USEDCOUNT,WIDTH,ISOPEN) values ('1',null,null,'20111126152633',null,'模板描述信息','720','全屏模板','content/templates/defaulttemplate.jpg','P','模板文件对应描述文件路径',0,'1280','F');
Insert into TEMPLATEDIR (SERIALNO,DIRECTORYID,TEMPLATEID) values ('6fd11169cc7189c317f58956ef71776f','-1','1');
Insert into TEMPLATEOPERATOR (SERIALNO,OPERATORID,TEMPLATEID,TYPE,SHARERS) values ('aee7d2bf07795e2540e0408a6d2d42c6','-1','1','0',null);


//告警信息表
REM INSERTING into ALARMCODEVALUE
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('0001','成功');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('0002','失败');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1001','终端开机');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1002','终端认证');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1003','任务轮询');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1004','网络断开');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1005','网络连接');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1006','终端休眠');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1007','终端唤醒');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1008','终端重启');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1009','播放任务开始下载');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1010','播放任务结束下载');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1011','音量参数被设置');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1012','开机时间参数被设置');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1013','关机时间参数被设置');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1014','下载时间段参数被设置 ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1015','下载速度参数被设置');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1016','服务器地址参数被设置 ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1017','服务器端口参数被设置 ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1018','播放日志上传时间参数被设置');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1019','运行日志上传时间参数被设置');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1020','本机别名参数被设置');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1021','本机物理区域参数被设置');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1022','本机IP地址参数被设置 ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1023','终端参数上报');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1024','终端向服务端索要播放任务');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1025','终端抓屏');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1026','终端升级');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1027','终端执行即时消息');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1028','终端执行插播任务');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1029','终端开始执行播放任务 ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1030','终端开始执行垫片');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1031','终端硬盘自动清理');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1032','终端硬盘状态上报');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1033','资源清单获取');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1034','播放日志上报');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1035','终端下载时间段开始响应');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1036','终端下载时间段结束响应 ');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1037','终端本地时间同步');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1038','终端发出告警信息');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('1039','关机');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2001','磁盘空间不足');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2002','配置信息设置失败');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2003','任务下载失败');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2004','硬盘mount不上');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2005','解码异常');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2006','LED屏连接异常');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2007','存储有非法内容（例如MD5校验不成功）');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('3001','ftp连接失败');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('3002','文件不存在');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2008','xml不完整');
Insert into ALARMCODEVALUE (ALARMCODE,ALARMVALUE) values ('2009','日志上传失败');

REM INSERTING into PROGRAM

Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('1',null,null,'20111025181842',null,null,'1','YYYY年MM月DD日 星期WK hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('2',null,null,'20111025182117',null,null,'1','YYYY年MM月DD日 hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('3',null,null,'20111025182125',null,null,'1','MM月DD日 星期WK hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('4',null,null,'20111025182134',null,null,'1','MM月DD日 星期WK','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('5',null,null,'20111025182144',null,null,'1','MM月DD日 hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('6',null,null,'20111025182152',null,null,'1','hh:mm:ss','P',10,'time',0,'F');
Insert into PROGRAM (PROGRAMID,APPROVER,APPROVETIME,CREATETIME,DENYREASON,DESCRIPTION,ISSYSTEM,NAME,STATUS,TIMELENGTH,TYPE,USEDCOUNT,ISOPEN) values ('7',null,null,'20111025182152',null,null,'1','hh:mm','P',10,'time',0,'F');

Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('3e942ee9ae6f9656b1fa15d36e69e565',null,'123','FFFFFF','simhei','36',null,0,'1',null,null,'YYYY年MM月DD日 星期WK hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('79ac23eaaf70f372690d26875a976fd7',null,'123','FFFFFF','simhei','36',null,0,'2',null,null,'YYYY年MM月DD日 hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('78eb3378ecc4ebf431ecb8b0d7626578',null,'123','FFFFFF','simhei','36',null,0,'3',null,null,'MM月DD日 星期WK hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('c60159b730dd6815b2f3c35b96be99ae',null,'123','FFFFFF','simhei','36',null,0,'4',null,null,'MM月DD日 星期WK','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('b1cf33c3e7439904ab40685b2a5b08f3',null,'123','FFFFFF','simhei','36',null,0,'5',null,null,'MM月DD日 hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('f351754d8ba40a32e6dc348caa524fd9',null,'123','FFFFFF','simhei','36',null,0,'6',null,null,'hh:mm:ss','0','0');
Insert into PROGRAMITEM (PROGRAMITEMID,BGCOLOR,CONTENTID,FGCOLOR,FONTNAME,FONTSIZE,LOOP,POSITION,PROGRAMID,SHOWMODE,SHOWSPEED,TIMEFORMAT,TIMELENGTH,TYPE) values ('6da524fd9f351754d8c348caba40a32e',null,'123','FFFFFF','simhei','36',null,0,'7',null,null,'hh:mm','0','0');

REM INSERTING into AREA
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('1','紫竹桥营业厅','39.947751','afc111f00366aff8f5f36366309a6e84','116.319434','海淀区紫竹桥东北角人济山庄Ｄ裙１０１席位');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('2','清华动感品牌店','40.004207','afc111f00366aff8f5f36366309a6e84','116.330704','海淀区清华园街道清华大学16区');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('3','知春路营业厅','39.981531','afc111f00366aff8f5f36366309a6e84','116.355289','海淀区知春路6号锦秋国际大厦一层A102');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('4','昌平回龙观营业厅','40.089547','408078263f8a721b0ea5e6e81a8aae25','116.339675','昌平区回龙观镇北店嘉园商业街33号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('5','昌平东关营业厅','40.227429','408078263f8a721b0ea5e6e81a8aae25','116.263122','昌平区府学路8号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('6','昌平霍营营业厅','40.086946','408078263f8a721b0ea5e6e81a8aae25','116.372344','昌平区东小口镇霍营旺龙花园商业17号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('7','蒋宅口营业厅','39.966536','723a25c587cdf1b5fd2f82f8b402ff59','116.41389','东城区安定门外大街3号楼一层');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('8','东四营业厅','39.928494','723a25c587cdf1b5fd2f82f8b402ff59','116.424173','东城区东四南大街62号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('9','宽街营业厅','39.939493','723a25c587cdf1b5fd2f82f8b402ff59','116.417949','东城区张自忠路10号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('10','西直门营业厅','39.945072','3b80e6795dfda83f0f5ed4232a450bcc','116.365499','西城区西直门内大街后半壁街56号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('11','月坛南街营业厅','39.919501','3b80e6795dfda83f0f5ed4232a450bcc','116.353453','西城区月坛南街甲12号一层南侧 ');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('12','德胜门营业厅','39.961126','3b80e6795dfda83f0f5ed4232a450bcc','116.38627','西城区德外教场口9号院6号楼1层');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('13','黄寺营业厅','39.968914','3b80e6795dfda83f0f5ed4232a450bcc','116.38937','西城区黄寺大街26号1号楼1层108号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('14','天坛东里营业厅','39.881796','69b60131c2db2f805c1e604fb4f04242','116.429467','崇文区天坛东里5号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('15','陶然桥营业厅','39.871749','69b60131c2db2f805c1e604fb4f04242','116.394217','崇文区马家堡56号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('16','崇文门营业厅','39.903124','69b60131c2db2f805c1e604fb4f04242','116.425977','崇文区崇文门外大街40号1幢一层北侧');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('17','延庆绿韵广场营业厅','40.471514','2945faa245079f97e9af1d217eb62aa2','115.992867','延庆县延庆镇绿韵商业广场G4号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('18','延庆北街营业厅','40.465635','2945faa245079f97e9af1d217eb62aa2','115.97666','延庆县延庆镇北街1号楼1门');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('19','门头沟营业厅','39.94245','52455988531ddd7dc43dedd209f4ac44','116.117234','门头沟区滨河路111号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('20','石景山路营业厅','39.913881','43ec565de97133e88e5b59bdefe2fe12','116.2202','石景山区石景山路23号中础大厦一层');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('21','实兴东街营业厅','39.938939','43ec565de97133e88e5b59bdefe2fe12','116.197428','石景山区实兴大街1号海特饭店一层');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('22','通州通胡大街营业厅','39.919171','fe70309af71cae1df55c9df9f36db097','116.691856','通州区通胡大街23-8-9号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('23','通州翠景北里营业厅','39.894278','fe70309af71cae1df55c9df9f36db097','116.661369','通州区九棵树街118号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('24','顺义中山南街营业厅','40.142303','4ae06fc815e6a9e4cdbb6cc3d65432d6','116.657524','顺义中山南街5号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('25','顺义天竺营业厅','40.066854','4ae06fc815e6a9e4cdbb6cc3d65432d6','116.59049','顺义区天竺镇府前一街3号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('26','平谷世纪广场营业厅','40.15207','15a0bbcfb59505cb0e5d9be79f829fe1','117.112356','平谷新平北路57号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('27','平谷马昌营营业厅','40.144166','15a0bbcfb59505cb0e5d9be79f829fe1','117.029433','平谷马昌营镇东侧商业楼');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('28','蒲芳路营业厅','39.872041','e356500bb3e2e3644ccd556a9f857c3d','116.441917','丰台区方庄蒲芳路1号一层底商');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('29','芳古园营业厅','39.872018','e356500bb3e2e3644ccd556a9f857c3d','116.430894','丰台区方庄芳古园一区29号楼1-1-1');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('30','密云县鼓楼营业厅','40.382239','195a80ae883ea6ca3f72ea0dae0641b1','116.853547','密云县鼓楼东大街电信大楼');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('31','密云县十里堡营业厅','40.372662','195a80ae883ea6ca3f72ea0dae0641b1','116.82687','密云县兴云小区14－9－102');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('32','怀柔凤翔营业厅','40.296605','0370c7eb7ad7c069a5af0709211f5c52','116.688766','怀柔区邮政局杨宋庄邮政所东厅一层二层');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('33','怀柔九渡河营业厅','40.365852','0370c7eb7ad7c069a5af0709211f5c52','116.473434','怀柔区九渡河镇黄坎村6号院');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('34','房山良乡营业厅','39.729875','9e9178cfe318836e791b0d6dd4bca3c3','116.156718','房山区良乡长虹东路16号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('35','房山窦店镇营业厅','39.653753','9e9178cfe318836e791b0d6dd4bca3c3','116.075495','房山区窦店镇金鑫苑小区2号楼9号底商');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('36','大兴经济技术开发区营业厅','39.807666','ef718e2b0e0209a11fd6c6d44dae4c31','116.51454','北京经济技术开发区宏达北路10号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('37','大兴西红门营业厅','39.798824','ef718e2b0e0209a11fd6c6d44dae4c31','116.345508','大兴区西红门镇欣旺北大街111号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('38','光华路营业厅','39.919137','9344d6acbea98b1c37d9f1f3730a8e27','116.480851','朝阳区光华路2号阳光100写字楼G座');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('39','望京营业厅','40.004438','9344d6acbea98b1c37d9f1f3730a8e27','116.474217','朝阳区广顺北大街29号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('40','团结湖营业厅','39.934174','9344d6acbea98b1c37d9f1f3730a8e27','116.470751','朝阳区白家庄东里一号绵湖园天客隆超市');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('23c49c23000be5cef89450279fbbe9b1','上地一街','40.040613  ','afc111f00366aff8f5f36366309a6e84','116.314092','上地一街xx号');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('e5cef89450279fbbe9b123c49c23000b','上地五街','40.045807  ','afc111f00366aff8f5f36366309a6e84','116.315312','北京市海淀区上地五街');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('3000be5cef89450279fbbe9b123c49c2','上地七街','40.048777  ','afc111f00366aff8f5f36366309a6e84','116.304101','北京市海淀区上地七街');
Insert into AREA (AREAID,AREA,LATITUDE,LOCATIONID,LONGITUDE,ADDRESS) values ('b123c49c23000be5cef89450279fbbe9','上地六街','40.048213 ','408078263f8a721b0ea5e6e81a8aae25','116.309947','北京市海淀区上地六街');

REM INSERTING into LOCATION
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('43ec565de97133e88e5b59bdefe2fe12','石景山区','019');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('afc111f00366aff8f5f36366309a6e84','海淀区','011');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('ed2674b37eee02d6457740093f338723','北京','010');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('408078263f8a721b0ea5e6e81a8aae25','昌平区','012');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('69b60131c2db2f805c1e604fb4f04242','崇文区','016');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('723a25c587cdf1b5fd2f82f8b402ff59','东城区','013');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('3b80e6795dfda83f0f5ed4232a450bcc','西城区','015');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('2945faa245079f97e9af1d217eb62aa2','延庆县','017');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('52455988531ddd7dc43dedd209f4ac44','门头沟区','018');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('fe70309af71cae1df55c9df9f36db097','通州区','020');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('4ae06fc815e6a9e4cdbb6cc3d65432d6','顺义区','021');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('15a0bbcfb59505cb0e5d9be79f829fe1','平谷区','022');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('e356500bb3e2e3644ccd556a9f857c3d','丰台区','023');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('195a80ae883ea6ca3f72ea0dae0641b1','密云县','024');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('0370c7eb7ad7c069a5af0709211f5c52','怀柔区','025');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('9e9178cfe318836e791b0d6dd4bca3c3','房山区','026');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('ef718e2b0e0209a11fd6c6d44dae4c31','大兴区','027');
Insert into LOCATION (LOCATIONID,CITY,CODE) values ('9344d6acbea98b1c37d9f1f3730a8e27','朝阳区','028');

//增加对我的工作中已阅功能的支持，增加readstatus字段，informix测试通过
alter table imessagegroup add readstatus varchar(1) default 'N';
alter table playlistgroup add readstatus varchar(1) default 'N';
alter table resourcedeliver add readstatus varchar(1) default 'N';

//清除表数据
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


//清除表
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