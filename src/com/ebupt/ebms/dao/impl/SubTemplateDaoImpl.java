package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.SubTemplateDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.SubTemplate;

/**
 * @author QiChen Create on 2011-3-17
 * @version 1.0
 */
@Repository
public class SubTemplateDaoImpl extends DaoSupport implements SubTemplateDao {

	private static Logger log = Logger.getLogger(SubTemplateDaoImpl.class);
	
	@Override
	public SubTemplate findSubTemplate(String subTemplateId) {
		try {
			String sql = "from SubTemplate where subtemplateid = '" + subTemplateId + "'";
			return (SubTemplate) this.findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public List<SubTemplate> findSubtemplates(String templateid) {
		try {
			String sql = "from SubTemplate where templateid = '" + templateid + "'";
			List<SubTemplate> result = new ArrayList<SubTemplate>();
			for (Object obj : find(sql)) {
				if (obj instanceof SubTemplate) {
					result.add((SubTemplate) obj);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

}
