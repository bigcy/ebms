package com.ebupt.ebms.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.ImessageGroupDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.ImessageGroup;
import com.ebupt.ebms.utils.ConvertUtil;

/**
 * @author QiChen Create on 2011-3-8
 * @version 1.0
 */
@Repository
public class ImessageGroupDaoImpl extends DaoSupport implements ImessageGroupDao {

	private static Logger log = Logger.getLogger(ImessageGroupDaoImpl.class);
	
	@Override
	public ImessageGroup findLastMessageByGroupIds(String groupIds) {
		try {
			String[] _groupIds = groupIds.split(",");
			String sql = null;
			if (_groupIds.length == 1) {
				sql = "from ImessageGroup where groupid = '" + _groupIds[0]
						+ "' and approvestatus = 'P' order by createtime DESC";
			} else {
				sql = "from ImessageGroup where groupid in " + ConvertUtil.getSqlQuery(groupIds)
						+ " and approvestatus = 'P' order by createtime DESC";
			}

			List<Object> objs = find(sql);
			if (objs != null && !objs.isEmpty()) {
				if (objs.get(0) instanceof ImessageGroup) {
					return (ImessageGroup) objs.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}

		return null;
	}

}
