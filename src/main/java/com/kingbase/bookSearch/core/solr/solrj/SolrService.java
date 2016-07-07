package com.kingbase.bookSearch.core.solr.solrj;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.github.pagehelper.PageInfo;
import com.kingbase.bookSearch.core.solr.bean.PageInfoFacet;

/**
 * solr
 * @author ganliang
 *
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
	public QueryResponse query(SolrQuery query) throws IOException, SolrServerException;

	/**
	 * 查询核心下的所有文档
	 * @param query
	 * @param clazz
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public <T> List<T> query(SolrQuery query, Class<T> clazz) throws IOException, SolrServerException;

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
	 * 更新文档
	 * @param id 文档主键
	 * @param document 文档
	 * @throws SolrServerException
	 * @throws IOException
	 */
	void update(String id, SolrInputDocument document) throws SolrServerException, IOException;

	/**
	 * 批量更新文档
	 * @param ids 文档主键集合
	 * @param documents 文档集合
	 * @throws SolrServerException
	 * @throws IOException
	 */
	void update(List<String> ids, List<SolrInputDocument> documents) throws SolrServerException, IOException;
	
	/**
	 * 根据文档主键id 直接获取文档
	 * @param id 文档主键id
	 * @return SolrDocument
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public SolrDocument get(String id) throws SolrServerException, IOException;

	/**
	 * 根据文档主键id 直接获取文档实体对象
	 * @param id 文档主键id
	 * @param clazz 实体字节码
	 * @return 对象
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public <T> T get(String id, Class<T> clazz) throws SolrServerException, IOException;

	/**
	 * 获取文档集合
	 * @param ids
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public SolrDocumentList get(List<String> ids) throws SolrServerException, IOException;

	/**
	 * 文档集合实体对象
	 * @param ids
	 * @param clazz
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public <T> List<T> get(List<String> ids, Class<T> clazz) throws SolrServerException, IOException;

	/**
	 * 分页查询
	 * @param query
	 * @param rowBounds
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public <T> PageInfo<T> query(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException;

	/**
	 * 分页查询
	 * @param clazz
	 * @param query
	 * @param rowBounds
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public <T> PageInfo<T> query(SolrQuery query, RowBounds rowBounds,Class<T> clazz) throws IOException, SolrServerException;

	/**
	 * 分页查询
	 * @param query solrQuery 查询
	 * @param rowBounds 
	 * @return
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public <T> PageInfoFacet<T> queryFacet(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException;

	/**
	 * ping 服务器
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public SolrPingResponse ping() throws SolrServerException, IOException;
}
