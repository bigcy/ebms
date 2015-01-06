package com.ebupt.ebms.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ebupt.ebms.dao.TerminalDao;
import com.ebupt.ebms.dao.support.DaoSupport;

/**
 * @author lishuhua Create time 2011-03-04
 * @version 1.0
 */
@Repository
public class TerminalDaoImpl extends DaoSupport implements TerminalDao {

	private static Logger log = Logger.getLogger(TerminalDaoImpl.class);

	@Override
	public ArrayList<String> getAllTerminals() {
		try {
			String sql = "select terminalid from Terminal";
			ArrayList<String> result = new ArrayList<String>();
			for (Object obj : find(sql)) {
				result.add((String) obj);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public ArrayList<String> getTerminalsByLocationId(String locationid) {
		try {
			String sql = "select terminalid from Terminal where locationid = '" + locationid + "'";
			ArrayList<String> result = new ArrayList<String>();
			for (Object obj : find(sql)) {
				result.add((String) obj);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public ArrayList<String> getTerminalsByCode(String code) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			String sql = "select distinct a.terminalid from Terminal a,Location b where b.code = '" + code
					+ "' and b.locationId = a.locationid";
			for (Object obj : find(sql)) {
				result.add((String) obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return result;
	}

	@Override
	public Set<String> getPhonesByTerminalid(String terminalid) {
		Set<String> result = new HashSet<String>();
		try {
			String sql = "select phone from Terminal where terminalid = '" + terminalid + "' ";
			String phone = (String)findSingleObject(sql);
			if(phone != null){
				result.add(phone);
			}

			sql = "select distinct a.phone from Operator a,TermOperator b where b.terminalid = '" + terminalid
					+ "' and b.operatorid = a.operatorid";
			for (Object obj : find(sql)) {
				result.add((String) obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
		return result;
	}
}
