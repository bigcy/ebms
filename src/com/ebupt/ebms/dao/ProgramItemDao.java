package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.ProgramItem;

/**   
 * @author QiChen
 * Create on 2011-3-18
 * @version 1.0  
 */
public interface ProgramItemDao extends Dao {

	public List<ProgramItem> findItemsByProgramId(String programid);

}