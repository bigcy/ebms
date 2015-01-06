package com.ebupt.ebms.dao;


import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.SubTemplate;

/**   
 * @author QiChen
 * Create on 2011-3-17
 * @version 1.0  
 */
public interface SubTemplateDao extends Dao {
	
	public SubTemplate findSubTemplate(String subTemplateId);
	
	public List<SubTemplate> findSubtemplates(String templateid);

}