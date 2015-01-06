package com.ebupt.ebms.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.TemplateDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.Template;

/**   
 * @author QiChen
 * Create on 2011-3-17
 * @version 1.0  
 */
@Repository
public class TemplateDaoImpl extends DaoSupport implements TemplateDao {

	private static Logger log = Logger.getLogger(TemplateDaoImpl.class);
	@Override
	public Template getTemplateById(String templateid) {
		try{
			String sql = "from Template where templateid = '" + templateid + "'";
			return (Template)this.findSingleObject(sql);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

}
