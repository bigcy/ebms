package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.WeatherTaskItem;

/**
 * @author QiChen Create on 2011-3-8
 * @version 1.0
 */
public interface WeatherTaskItemDao extends Dao {

	public List<WeatherTaskItem> findNewItemsByTerminalId(String terminalId);

	public void updateItem(String taskid, String status, String terminalId);

	public void deleteItem(String terminalId);

}