package com.kingbase.bookSearch.common.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.kingbase.bookSearch.common.bean.PageModel;
import com.kingbase.bookSearch.common.dao.IHibernateDao;
import com.kingbase.bookSearch.common.utils.TUtils;

@SuppressWarnings("unchecked")
public class HibernateDaoImpl<T> extends HibernateDaoSupport implements IHibernateDao<T> {

	@SuppressWarnings("rawtypes")
	Class entityClass = TUtils.getTGenericSuperClass(this.getClass());
	
	/**
	 * 这个和整合ibatis是一样的
	 */
	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;

	@PostConstruct
	public void initHibernateTemplate() {
		super.setHibernateTemplate(hibernateTemplate);
	}

	public Integer count(final String hql) {
		if (StringUtils.isEmpty(hql)) {
			throw new IllegalStateException("hql is null");
		}
		Object result = this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery(hql).uniqueResult();
			}
		});
		return ((Long) result).intValue();
	}

	public int bulkUpdate(String queryString, Object[] values) {
		return getHibernateTemplate().bulkUpdate(queryString, values);
	}

	public <E> void deleteAll(Collection<E> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	public Integer count(final String hql, final Object... obj) {
		if (ObjectUtils.isEmpty(obj)) {
			return count(hql);
		} else {
			if (StringUtils.isEmpty(hql)) {
				throw new IllegalStateException("hql is null");
			}
			Object result = this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					for (int i = 0; i < obj.length; i++) {
						query.setParameter(i, obj[i]);
					}
					return query.uniqueResult();
				}
			});
			return ((Long) result).intValue();
		}
	}

	public <E> void delete(E entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public <E> boolean exist(Serializable id) {
		if (get(id) != null)
			return true;
		return false;
	}

	public <E> List<E> find(String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	public <E> List<E> findAll() {
		String hql = "FROM " + entityClass.getSimpleName();
		return find(hql);
	}

	public List<?> find(String queryString, Object[] values) {
		if (ObjectUtils.isEmpty(values)) {
			return find(queryString);
		} else {
			return getHibernateTemplate().find(queryString, values);
		}
	}

	public <E> E findUniqueEntity(final String queryString, final Object... params) {
		if (StringUtils.isEmpty(queryString)) {
			throw new IllegalStateException("queryString is null");
		}
		if (ObjectUtils.isEmpty(params)) {
			return (E) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) {
					return session.createQuery(queryString).uniqueResult();
				}
			});
		} else {
			return (E) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) {
					Query query = session.createQuery(queryString);

					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
					return query.uniqueResult();
				}
			});
		}
	}

	public <E> List<E> findByNamedQuery(String queryName) {
		if (StringUtils.isEmpty(queryName)) {
			throw new IllegalArgumentException("queryName is null");
		}
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	public <E> List<E> findByNamedQuery(String queryName, Object... values) {
		if (ObjectUtils.isEmpty(values)) {
			return this.findByNamedQuery(queryName);
		}
		return getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	public <E> List<E> findByPage(final String hql, final Integer startRow, final Integer pageSize,
			final Object... params) {
		if (StringUtils.isEmpty(hql)) {
			throw new IllegalStateException("hql is null");
		}
		if (ObjectUtils.isEmpty(params)) {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) {
					return session.createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize).list();
				}
			});
		} else {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) {
					Query query = session.createQuery(hql);
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
					return query.setFirstResult(startRow).setMaxResults(pageSize).list();
				}
			});
		}
	}

	public <E> E get(Serializable id) {
		this.getHibernateTemplate().setCacheQueries(true);
		return (E) this.getHibernateTemplate().get(entityClass, id);
	}

	public <E> Iterator<E> iterate(String queryString) {
		return getHibernateTemplate().iterate(queryString);
	}

	public <E> Iterator<E> iterate(String queryString, Object... values) {
		return getHibernateTemplate().iterate(queryString, values);
	}

	public <E> E load(Serializable id) {
		return (E) getHibernateTemplate().load(entityClass, id);
	}

	public <E> void persist(E entity) {
		getHibernateTemplate().persist(entity);
	}

	public <E> void refresh(E entity) {
		getHibernateTemplate().refresh(entity);
	}

	public <E> Serializable save(E entity) {
		if (entity == null) {
			throw new IllegalArgumentException("entity is null");
		}
		return getHibernateTemplate().save(entity);
	}

	public <E> void saveOrUpdate(E entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public <E> void saveOrUpdateAll(Collection<E> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	public <E> void update(E entity) {
		getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("hiding")
	public <T> void updateAll(Collection<T> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			throw new IllegalArgumentException("entities is null");
		}
		int i = 0;
		for (Object obj : entities) {
			if (i % 30 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
			getHibernateTemplate().update(obj);
			i++;
		}
	}

	public <E> void saveAll(Collection<E> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			throw new IllegalArgumentException("entities is null");
		}
		int i = 0;
		for (E obj : entities) {
			if (i % 30 == 0) {
				getHibernateTemplate().flush();
			}
			save(obj);
			i++;
		}
	}

	public <E> List<E> findByPage(String queryString, PageModel pageModel, List<?> params) {

		String hql = queryString;
		if (queryString.toLowerCase().indexOf("where") == -1) {
			Matcher m = Pattern.compile("and").matcher(queryString);
			if (m.find()) {
				hql = m.replaceFirst("where");
			} else {
				m = Pattern.compile("AND").matcher(queryString);
				if (m.find()) {
					hql = m.replaceFirst("WHERE");
				}
			}
		}
		int fromIndex = hql.toLowerCase().indexOf("from");
		int orderIndex = hql.toLowerCase().indexOf("group by");

		String hqlCount = "select count(*) " + hql.substring(fromIndex, orderIndex > 0 ? orderIndex : hql.length());
		int orderbyIndex = hqlCount.toLowerCase().indexOf("order by");
		hqlCount = hqlCount.substring(0, orderbyIndex > 0 ? orderbyIndex : hqlCount.length());

		int totalCount = (params == null || params.isEmpty()) ? count(hqlCount) : count(hqlCount, params.toArray());
		pageModel.setRecordCount(totalCount);
		if (totalCount == 0) {
			return new ArrayList<E>();
		}
		Object[] temps = (params == null || params.isEmpty()) ? new Object[] {} : params.toArray();
		return this.findByPage(hql, pageModel.getStartRow(), pageModel.getPageSize(), temps);
	}
	
	public void clear(){
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("DELETE FROM "+entityClass.getSimpleName()+" WHERE 1=1");
				query.executeUpdate();
				return null;
			}
		});
	}
	
	@Override
	public void deleteByHql(final String hql) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("DELETE FROM "+entityClass.getSimpleName()+" WHERE "+hql);
				query.executeUpdate();
				return null;
			}
		});
	}

}