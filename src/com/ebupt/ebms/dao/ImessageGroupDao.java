package com.ebupt.ebms.dao;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.ImessageGroup;

/**   
 * @author QiChen
 * Create on 2011-3-8
 * @version 1.0  
 */
public interface ImessageGroupDao extends Dao {

	public ImessageGroup findLastMessageByGroupIds(String groupIds);

}