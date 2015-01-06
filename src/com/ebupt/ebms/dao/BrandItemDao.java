package com.ebupt.ebms.dao;

import java.util.List;

import com.ebupt.ebms.dao.support.Dao;
import com.ebupt.ebms.entity.BrandItem;

/**   
 * @author
 * 2012-09-27
 * @version 1.0  
 */
public interface BrandItemDao extends Dao {

	public List<BrandItem> getBrandItemsByTermId(String terminalId);
	
	public List<BrandItem> getBrandItemsByBrandId(String brandId);
	
	public List<String> getBrandItemsByBrandAndOperatorId(String brandId,String operatorid);
	
	public List<String> getTermianlIdsByBrandId(String brandId);

}