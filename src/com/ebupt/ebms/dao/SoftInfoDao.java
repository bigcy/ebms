package com.ebupt.ebms.dao;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.SoftInfo;

/**   
 * @author lishuhua
 * Create time 2011-3-7
 * @version 1.0  
 */
//»Ìº˛–≈œ¢
public interface SoftInfoDao extends Dao {

	public SoftInfo findSoft(String version, String factoryid);
	
}