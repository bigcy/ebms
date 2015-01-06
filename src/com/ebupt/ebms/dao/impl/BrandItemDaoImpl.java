package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.BrandItemDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.BrandItem;

/**
 * @author 
 * 2012-09-27
 * @version 1.0
 */
@Repository
public class BrandItemDaoImpl extends DaoSupport implements BrandItemDao {

	private static Logger log = Logger.getLogger(BrandItemDaoImpl.class);
	
	public List<BrandItem> getBrandItemsByTermId(String terminalId) {
		try {
			String sql = "from BrandItem where terminalid = '" + terminalId + "'";
			List<BrandItem> results = new ArrayList<BrandItem>();
			for (Object obj : this.find(sql)) {
				if (obj instanceof BrandItem) {
					results.add((BrandItem) obj);
				}
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	public List<BrandItem> getBrandItemsByBrandId(String brandId) {
		try {
			String sql = "from BrandItem where brandid = '" + brandId + "'";
			List<BrandItem> results = new ArrayList<BrandItem>();
			for (Object obj : this.find(sql)) {
				if (obj instanceof BrandItem) {
					results.add((BrandItem) obj);
				}
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}
	
	public List<String> getBrandItemsByBrandAndOperatorId(String brandId,String operatorid) {
		try {
			List<String> results = new ArrayList<String>();
			String sql = "";
			
			String checkopsql = "select locationid from Operator where operatorid = '"+operatorid+"'";
			String ops = (String) this.findSingleObject(checkopsql);
			//根据品牌id和操作员的位置去查找终端
			if("-1".equals(ops) || "-1".equals(operatorid) || "-1".equals(brandId)){
				sql = "select terminalid from BrandItem where brandid = '" + brandId + "'";
			}else{
				sql = "select br.terminalid from BrandItem br,Operator op,Terminal t where t.terminalid=br.terminalid and t.locationid=op.locationid and br.brandid = '" + brandId + "' and op.operatorid = '"+operatorid+"'";
			}
			
			for (Object obj : this.find(sql)) {
				if (obj instanceof String) {
					results.add((String) obj);
				}
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}
	
	public List<String> getTermianlIdsByBrandId(String brandId) {
		try {
			List<String> results = new ArrayList<String>();
			// 根据群组id去查找终端
			String sql = "select terminalid from BrandItem where brandid = '" + brandId + "'";
			for (Object obj : this.find(sql)) {
				if (obj instanceof String) {
					results.add((String) obj);
				}
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
