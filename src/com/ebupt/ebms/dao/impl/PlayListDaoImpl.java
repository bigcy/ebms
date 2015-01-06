package com.ebupt.ebms.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.PlayListDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.PlayList;;

/**   
 * @author QiChen
 * Create on 2011-3-17
 * @version 1.0  
 */
@Repository
public class PlayListDaoImpl extends DaoSupport implements PlayListDao {

	private static final Logger log = Logger.getLogger(PlayListDaoImpl.class);
	
	@Override
	public PlayList findPlayList(String playlistid) {
		try{
			String sql = "from PlayList where playlistid = '" + playlistid +"'";
			return (PlayList)this.findSingleObject(sql);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

}
