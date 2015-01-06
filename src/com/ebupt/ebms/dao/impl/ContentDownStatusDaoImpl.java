package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.ContentDownStatusDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.ContentDownStatus;

/**
 * @author lishuhua Create time 2011-3-4
 * @version 1.0
 */
@Repository
public class ContentDownStatusDaoImpl extends DaoSupport implements ContentDownStatusDao {

	private static Logger log = Logger.getLogger(ContentDownStatusDaoImpl.class);
	
	@Override
	public ContentDownStatus findContent(String terminalid, String contentid, String playlistid) {
		try {
			String sql = "from ContentDownStatus where terminalid = '" + terminalid + "' and contentid = '" + contentid
					+ "' and playlistid = '" + playlistid + "'";
			return (ContentDownStatus) this.findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public ContentDownStatus findContent(String terminalid, String contentid, String playlistid, String status) {
		try {
			String sql = "from ContentDownStatus where terminalid = '" + terminalid + "' and contentid = '" + contentid
					+ "' and playlistid = '" + playlistid + "' and status = '" + status + "'";
			return (ContentDownStatus) this.findSingleObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public List<ContentDownStatus> findAllContent() {
		String sql = "from ContentDownStatus";
		List<ContentDownStatus> result = new ArrayList<ContentDownStatus>();

		for (Object obj : find(sql)) {
			if (obj instanceof ContentDownStatus) {
				result.add((ContentDownStatus) obj);
			}
		}

		return result.isEmpty() ? null : result;
	}

}
