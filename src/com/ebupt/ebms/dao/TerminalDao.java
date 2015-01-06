package com.ebupt.ebms.dao;

import java.util.ArrayList;
import java.util.Set;

import com.ebupt.ebms.dao.support.Dao;

/**   
 * @author lishuhua
 * Create time 2011-03-04
 * @version 1.0  
 */
public interface TerminalDao extends Dao {
	
	public ArrayList<String> getAllTerminals();
	
	public ArrayList<String> getTerminalsByLocationId(String locationid);
	
	public ArrayList<String> getTerminalsByCode(String code);
	
	public Set<String> getPhonesByTerminalid(String terminalid);
}