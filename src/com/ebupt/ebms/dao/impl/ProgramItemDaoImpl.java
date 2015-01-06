package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.ProgramItemDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.ProgramItem;

/**
 * @author QiChen Create on 2011-3-18
 * @version 1.0
 */
@Repository
public class ProgramItemDaoImpl extends DaoSupport implements ProgramItemDao {

	private static Logger log = Logger.getLogger(ProgramItemDaoImpl.class);
	
	@Override
	public List<ProgramItem> findItemsByProgramId(String programid) {
		try {
			List<ProgramItem> result = new ArrayList<ProgramItem>();
			String sql = "from ProgramItem where programid = '" + programid + "' order by position asc";
			for (Object obj : find(sql)) {
				if (obj instanceof ProgramItem) {
					result.add((ProgramItem) obj);
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
