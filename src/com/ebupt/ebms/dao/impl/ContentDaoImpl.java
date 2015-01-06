package com.ebupt.ebms.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.ContentDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.Content;

/**   
 * @author QiChen
 * Create on 2011-3-18
 * @version 1.0  
 */
@Repository
public class ContentDaoImpl extends DaoSupport implements ContentDao {

	private static Logger log = Logger.getLogger(ContentDaoImpl.class);
	@Override
	public Content getContentType(String contentid) {
		try {
			String sql = "from Content where contentid = '" + contentid + "'";
			 return (Content) this.findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

}
