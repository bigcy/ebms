package com.ebupt.ebms.dao.support;

import java.util.List;

/**   
 * @author QiChen
 * Create on 2011-3-2
 * @version 1.0  
 */
public interface Dao {

	public Object add(Object t);

	public Object modify(Object t);

	public void remove(Object t);

	public List<Object> find(String sql);

	public Object findSingleObject(String sql);

	public boolean excuteSql(String sql);
	
	public Object find(@SuppressWarnings("rawtypes") Class entityClass, String id);
	
	public void saveObjects(final List<Object> objs);
}