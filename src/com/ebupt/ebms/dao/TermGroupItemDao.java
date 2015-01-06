package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.TermGroupItem;

/**   
 * @author QiChen
 * Create on 2011-3-8
 * @version 1.0  
 */
public interface TermGroupItemDao extends Dao {

	public List<TermGroupItem> getItemsByTermId(String terminalId);
	
	public List<TermGroupItem> getItemsByGroupId(String groupId);
	
	public List<String> getItemsByGroupAndOperatorId(String groupId,String operatorid);
	
	public List<String> getTermianlIdsByGroupId(String groupId);

}