package com.ebupt.ebms.dao;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.Content;

/**   
 * @author QiChen
 * Create on 2011-3-18
 * @version 1.0  
 */
public interface ContentDao extends Dao {
	
	public Content getContentType(String contentid);

}