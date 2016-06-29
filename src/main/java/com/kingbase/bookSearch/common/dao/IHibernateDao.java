package com.kingbase.bookSearch.common.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.kingbase.bookSearch.common.bean.PageModel;

public interface IHibernateDao<T>{
    /**
     * 删除
     */
    public <E> void delete(E entity);

    public <E> List<E> find(String queryString);

    public <E> List<E> findAll();

    public int bulkUpdate(String queryString, Object[] values);

    public <E> void deleteAll(Collection<E> entities);

    public List<?> find(String queryString, Object[] values);

    public <E> E findUniqueEntity(String queryString, Object... params);

    public <E> List<E> findByPage(String queryString, Integer startRow,
            Integer pageSize, Object... params);

    public <E> List<E> findByNamedQuery(String queryName);

    public <E> List<E> findByNamedQuery(String queryName, Object... values);

    public <E> Iterator<E> iterate(String queryString);

    public <E> Iterator<E> iterate(String queryString, Object... values);
    
    public <E> E get(Serializable id);

    public <E> E load(Serializable id);

    public <E> void persist(E entity);

    public <E> void refresh(E entity);

    public <E> Serializable save(E entity);

    public <E> void saveAll(Collection<E> entities);

    public <E> void saveOrUpdate(E entity);

    public <E> void saveOrUpdateAll(Collection<E> entities);

    public <E> void update(E entity);

    public <E> void updateAll(Collection<E> entities);

    public <E> boolean exist(Serializable id);

    public Integer count(String hql);

    public Integer count(String hql, Object... obj);

    public <E> List<E> findByPage(String queryString, PageModel pageModel,
            List<?> params);

    public void clear();

	void deleteByHql(String hql);
}
