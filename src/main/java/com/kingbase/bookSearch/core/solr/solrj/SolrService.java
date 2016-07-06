package com.kingbase.bookSearch.core.solr.solrj;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.formula.functions.T;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.github.pagehelper.PageInfo;
import com.kingbase.bookSearch.core.solr.beans.PageInfoFacet;

/**
 * Created by Administrator on 2016/3/28.
 */
public interface SolrService {

	/**
	 * 获取域客户端
	 * 
	 * @param core
	 * @return
	 */
	public SolrClient getHttpSolrClient();

	/**
	 * 增加单个实体
	 * 
	 * @param bean 实体
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public void add(Object bean) throws IOException, SolrServerException;

	/**
	 * 增加一个集合
	 * 
	 * @param beans
	 *            实体集合
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public void adds(Collection<?> beans) throws IOException, SolrServerException;

	/**
	 * @param query
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public QueryResponse query(String query) throws IOException, SolrServerException;

	/**
	 * 查询核心下的所有文档
	 * @param query
	 * @param clazz
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public List<T> query(String query, Class<T> clazz) throws IOException, SolrServerException;

	/**
	 * 删除文档
	 * @param id
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public void delete(String id) throws SolrServerException, IOException;

	/**
	 * 删除多个文档
	 * @param ids
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public void delete(List<String> ids) throws SolrServerException, IOException;

	/**
	 * 删除查询出来的文档
	 * @param query
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public void deleteByQuery(String query) throws SolrServerException, IOException;

	/**
	 * 分页查询
	 * @param query
	 * @param rowBounds
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public PageInfo<T> query(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException;

	/**
	 * 分页查询
	 * @param clazz
	 * @param query
	 * @param rowBounds
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public PageInfo<T> query(Class<T> clazz, SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException;

	/**
	 * 分页查询
	 * @param query solrQuery 查询
	 * @param rowBounds 
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public PageInfoFacet<T> queryFacet(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException;
}
