package com.ebupt.ebms.dao;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.Template;

/**   
 * @author QiChen
 * Create on 2011-3-17
 * @version 1.0  
 */
public interface TemplateDao extends Dao {

	public Template getTemplateById(String templateid);
	
}