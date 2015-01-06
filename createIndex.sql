--
--  Copyright(C) 2000 EASTCOM-BUPT Inc.
--
--  Created By          : $Author: cvssjyw $
--  Created At          : $Date: 2004/12/09 10:40:32 $
--  Last Revision       : $Revision: 1.1 $
--  Description         :
--

create index TP_t on TermPlaying(terminalid);

create index TCTI_t on TermConfigTaskItem(terminalid);

create index TA_t on TermAlarm(terminalid);

create index SITI_t on SoftInfoTaskItem(terminalid);

create index PLTI_t on PlayListTaskItem(terminalid);

create index PLTI_p on PlayListTaskItem(playlistid);

create index PLG_p on PlayListGroup(playlistid);

create index PLG_g on PlayListGroup(groupid);

create index PLG_s on PlayListGroup(startdate);

create index PLG_e on PlayListGroup(enddate);

create index IG_g on ImessageGroup(groupid);

create index CDS_tcp on ContentDownStatus(terminalid,contentid,playlistid);

create index WTI_t on WeatherTaskItem(terminalid);

create index WTI_w on WeatherTaskItem(weatherid);

create index ULR_taskid on UploadLogReport(taskid);

create index ULR_terminalid on UploadLogReport(terminalid);

/**
 * É¾³ýË÷ÒýÊ¾Àý
 */
drop index CDS_tcp on ContentDownStatus;
