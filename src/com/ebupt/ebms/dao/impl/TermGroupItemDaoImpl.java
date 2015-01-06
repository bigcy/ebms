package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.TermGroupItemDao;
import com.ebupt.ebms.dao.support.DaoSupport;
import com.ebupt.ebms.entity.TermGroupItem;

/**
 * @author QiChen Create on 2011-3-8
 * @version 1.0
 */
@Repository
public class TermGroupItemDaoImpl extends DaoSupport implements TermGroupItemDao {

	private static Logger log = Logger.getLogger(TermGroupItemDaoImpl.class);
	
	public List<TermGroupItem> getItemsByTermId(String terminalId) {
		try {
			String sql = "from TermGroupItem where terminalid = '" + terminalId + "'";
			List<TermGroupItem> results = new ArrayList<TermGroupItem>();
			for (Object obj : this.find(sql)) {
				if (obj instanceof TermGroupItem) {
					results.add((TermGroupItem) obj);
				}
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	public List<TermGroupItem> getItemsByGroupId(String groupId) {
		try {
			String sql = "from TermGroupItem where groupid = '" + groupId + "'";
			List<TermGroupItem> results = new ArrayList<TermGroupItem>();
			for (Object obj : this.find(sql)) {
				if (obj instanceof TermGroupItem) {
					results.add((TermGroupItem) obj);
				}
			}
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}
	
//	public List<String> getItemsByGroupAndOperatorId(String groupId,String operatorid) {
//		try {
//			List<String> results = new ArrayList<String>();
//			String sql = "";
//			if("-1".equals(groupId)){
//				//群组为-1，表示根节点，这时根据操作员id去查找终端
//				sql = "select terminalid from TermOperator where operatorid = '" + operatorid + "'";
//				for (Object obj : this.find(sql)) {
//					if (obj instanceof String) {
//						results.add((String) obj);
//					}
//				}
//			}else{
//				//根据群组id去查找终端
//				sql = "select terminalid from TermGroupItem where groupid = '" + groupId + "'";
//				for (Object obj : this.find(sql)) {
//					if (obj instanceof String) {
//						results.add((String) obj);
//					}
//				}
//			}
//			return results;
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error(e.getMessage(),e);
//		}
//		return null;
//	}
	public List<String> getItemsByGroupAndOperatorId(String groupId,String operatorid) {
		try {
			List<String> results = new ArrayList<String>();
			String sql = "";
			
			String checkopsql = "select locationid from Operator where operatorid = '"+operatorid+"'";
			String ops = (String) this.findSingleObject(checkopsql);
			//根据群组id和操作员的位置去查找终端
			if("-1".equals(ops) || "-1".equals(groupId) || "-1".equals(operatorid)){
				sql = "select terminalid from TermGroupItem where groupid = '" + groupId + "'";
			}else{
				sql = "select tgi.terminalid from TermGroupItem tgi,Operator op,Terminal t where t.terminalid=tgi.terminalid and t.locationid=op.locationid and tgi.groupid = '" + groupId + "' and op.operatorid = '"+operatorid+"'";
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
	
	public List<String> getTermianlIdsByGroupId(String groupId) {
		try {
			List<String> results = new ArrayList<String>();
			// 根据群组id去查找终端
			String sql = "select terminalid from TermGroupItem where groupid = '" + groupId + "'";
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
