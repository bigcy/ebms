package com.ebupt.ebms.dao;

import com.ebupt.ebms.dao.support.Dao;

/**
 * @author 
 * Create on 2014-2-27
 * @version 1.0
 */
public interface OnlineTimeDao extends Dao {

	//创建或更新终端在线时长
	public void createOrUpdateOnlineTime(String nowdate);
	
}