package com.ebupt.ebms.dao.support;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class DaoSupport extends HibernateDaoSupport implements Dao {

	@Autowired
	public void anyMethodName(
			@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebupt.ebms.dao.support.Dao#add(java.lang.Object)
	 */
	public Object add(Object t) {
		this.getHibernateTemplate().save(t);
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebupt.ebms.dao.support.Dao#modify(java.lang.Object)
	 */
	public Object modify(Object t) {
		this.getHibernateTemplate().update(t);
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebupt.ebms.dao.support.Dao#remove(java.lang.Object)
	 */
	public void remove(Object t) {
		this.getHibernateTemplate().delete(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebupt.ebms.dao.support.Dao#find(java.lang.String)
	 */
	public List<Object> find(String sql) {
		List<Object> result = new ArrayList<Object>();
		List objs = getHibernateTemplate().find(sql);
		if(objs != null && objs.size() > 0){
			for (Object obj : objs) {
				result.add(obj);
			}
		}
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebupt.ebms.dao.support.Dao#findSingleObject(java.lang.String)
	 */
	public Object findSingleObject(String sql) {
		List<Object> result = new ArrayList<Object>();
		List objs = getHibernateTemplate().find(sql);
		if(objs != null && objs.size() > 0){
			for (Object obj : objs) {
				result.add(obj);
			}
		}
		return result.size() == 0 ? null : result.get(0);
	}

	public Object find(@SuppressWarnings("rawtypes") Class entityClass,
			String id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ebupt.ebms.dao.support.Dao#excuteSql(java.lang.String)
	 */
	public boolean excuteSql(String sql) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery(sql).executeUpdate();
		tx.commit();
		session.close();
		return true;
	}

	public void saveObjects(final List<Object> objs) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				int rows = 0;
				for (Object obj : objs) {
					//session.saveOrUpdate(obj);
					session.save(obj);
					rows++;
					if (rows % 20 == 0) { // 20, same as the JDBC batch size
											// //20,与JDBC批量设置相同 //指定每次提交SQL的数量
						// flush a batch of inserts and release memory:
						// 将本批插入的对象立即写入数据库并释放内存
						session.flush();
						session.clear();
					}
				}
				session.close();
				return Integer.valueOf(rows);
			}
		});
	}
	
	
}
