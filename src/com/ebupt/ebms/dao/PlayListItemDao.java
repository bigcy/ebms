package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.PlayListItem;

/**   
 * @author QiChen
 * Create on 2011-3-17
 * @version 1.0  
 */
public interface PlayListItemDao extends Dao {

	public List<PlayListItem> findItems(String playlistid);

}