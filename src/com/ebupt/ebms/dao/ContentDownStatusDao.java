package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.ContentDownStatus;

/**   
 * @author lishuhua
 * Create time 2011-3-4
 * @version 1.0  
 */
public interface ContentDownStatusDao extends Dao {
	
	public List<ContentDownStatus> findAllContent();

	public ContentDownStatus findContent(String terminalid, String contentid, String playlistid);
	
	public ContentDownStatus findContent(String terminalid, String contentid, String playlistid, String staus);
	
}