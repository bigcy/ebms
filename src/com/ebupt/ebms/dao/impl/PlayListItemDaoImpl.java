package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.PlayListItemDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.PlayListItem;

/**
 * @author QiChen Create on 2011-3-17
 * @version 1.0
 */
@Repository
public class PlayListItemDaoImpl extends DaoSupport implements PlayListItemDao {

	private static Logger log = Logger.getLogger(PlayListItemDaoImpl.class);
	
	@Override
	public List<PlayListItem> findItems(String playlistid) {
		try {
			String sql = "from PlayListItem where playlistid = '" + playlistid + "'";
			List<PlayListItem> result = new ArrayList<PlayListItem>();
			for (Object obj : find(sql)) {
				if (obj instanceof PlayListItem) {
					result.add((PlayListItem) obj);
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
