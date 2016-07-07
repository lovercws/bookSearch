package com.kingbase.bookSearch.core.solr.solrj;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.kingbase.bookSearch.core.solr.bean.PageInfoFacet;

/**
 * @author ganliang
 */
abstract class SolrServiceImpl implements SolrService {
    
	private static final Logger log=Logger.getLogger(SolrServiceImpl.class);
	
	public abstract SolrClient getHttpSolrClient();

	@Override
	public void add(Object bean) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		UpdateResponse response = client.addBean(bean);
		log.info("添加文档:bean="+bean+",添加结果"+response);
		client.commit();
	}

	@Override
	public void adds(Collection<?> beans) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		UpdateResponse response = client.addBeans(beans);
		log.info("添加文档:beans="+beans+"】,添加结果"+response);
		client.commit();
	}

	@Override
	public void delete(String id) throws SolrServerException, IOException {
		SolrClient client = this.getHttpSolrClient();
		UpdateResponse updateResponse = client.deleteById(id);
		log.info("删除文档:id="+id+",删除结果"+updateResponse);
		client.commit();
	}
	
	@Override
	public void delete(List<String> ids) throws SolrServerException, IOException {
		SolrClient client = this.getHttpSolrClient();
		UpdateResponse updateResponse = client.deleteById(ids);
		log.info("删除文档:ids="+ids+",删除结果:"+updateResponse);
		client.commit();
	}

	@Override
	public void deleteByQuery(String query) throws SolrServerException, IOException {
		SolrClient client = this.getHttpSolrClient();
		UpdateResponse updateResponse = client.deleteByQuery(query);
		log.info("删除文档:query="+query+"】,删除结果:"+updateResponse);
		client.commit();
	}
	
	@Override
	public void update(String id,SolrInputDocument document) throws SolrServerException, IOException{
		SolrClient client = this.getHttpSolrClient();
		try {
			client.deleteById(id);
			client.add(document);
			client.commit();
			log.info("更新文档:id="+id+",更新文档:"+document);
		} catch (Exception e) {
			client.rollback();
			throw new SolrServerException(e);
		}
	}
	
	@Override
	public void update(List<String> ids,List<SolrInputDocument> documents) throws SolrServerException, IOException{
		SolrClient client = this.getHttpSolrClient();
		try {
			client.deleteById(ids);
			client.add(documents);
			client.commit();
			log.info("批量更新文档:ids="+ids+",更新文档:"+documents);
		} catch (Exception e) {
			client.rollback();
			throw new SolrServerException(e);
		}
	}
	
	@Override
	public SolrDocument get(String id) throws SolrServerException, IOException{
		SolrClient client = this.getHttpSolrClient();
		SolrDocument solrDocument = client.getById(id);
		log.info("获取文档:id="+id+",获取结果:"+solrDocument);
		return solrDocument;
	}

	@Override
	public <T> T get(String id,Class<T> clazz) throws SolrServerException, IOException{
		SolrClient client = this.getHttpSolrClient();
		SolrDocument solrDocument = client.getById(id);
		log.info("获取文档:id="+id+",获取结果:"+solrDocument);
		return client.getBinder().getBean(clazz, solrDocument);
	}
	
	@Override
	public SolrDocumentList get(List<String> ids) throws SolrServerException, IOException{
		SolrClient client = this.getHttpSolrClient();
		SolrDocumentList solrDocumentList = client.getById(ids);
		log.info("获取文档:ids="+ids+",获取结果:"+solrDocumentList);
		return solrDocumentList;
	}
	
	@Override
	public <T> List<T> get(List<String> ids,Class<T> clazz) throws SolrServerException, IOException{
		SolrClient client = this.getHttpSolrClient();
		SolrDocumentList solrDocumentList = client.getById(ids);
		log.info("获取文档:ids="+ids+",获取结果:"+solrDocumentList);
		return client.getBinder().getBeans(clazz, solrDocumentList);
	}

	@Override
	public QueryResponse query(SolrQuery query) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		QueryResponse queryResponse = client.query(query);
		log.info("查询文档:SolrQuery="+query+",查询结果:"+queryResponse);
		return queryResponse;
	}

	@Override
	public <T> List<T> query(SolrQuery query, Class<T> clazz) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		QueryResponse queryResponse = client.query(query);
		log.info("查询文档:SolrQuery="+query+",查询结果:"+queryResponse);
		return queryResponse.getBeans(clazz);
	}

	@Override
	public <T> PageInfo<T> query(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		if (rowBounds != null) {
			query.setStart(rowBounds.getOffset()- 1);
			query.setRows(rowBounds.getLimit());
		} else {
			Integer start = query.getStart();
			if (start == null) {
				start = 1;
			}
			Integer rows = query.getRows();
			rowBounds = new RowBounds(start, rows);
		}
		QueryResponse response = client.query(query);
		log.info("查询文档:SolrQuery="+query+",查询结果:"+response);
		
		SolrDocumentList documentList = response.getResults();
		
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) documentList;
		
		Page<T> page = new Page<T>(rowBounds.getOffset(), rowBounds.getLimit());
		page.setTotal(documentList.getNumFound());
		page.addAll(list);
		PageInfo<T> pageInfo = new PageInfo<T>(page);
		return pageInfo;
	}

	@Override
	public <T> PageInfo<T> query(SolrQuery query, RowBounds rowBounds,Class<T> clazz)
			throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		if (rowBounds != null) {
			query.setStart(rowBounds.getOffset()- 1);
			query.setRows(rowBounds.getLimit());
		} else {
			Integer start = query.getStart();
			if (start == null) {
				start = 0;
			}
			Integer rows = query.getRows();
			if(rows==null){
				rows=RowBounds.NO_ROW_LIMIT;
			}
			rowBounds = new RowBounds(start, rows);
		}
		QueryResponse response = client.query(query);
		log.info("查询文档:SolrQuery="+query+",查询结果:"+response);
		
		List<T> beans = response.getBeans(clazz);
		
		Page<T> page = new Page<T>(rowBounds.getOffset(), rowBounds.getLimit());
		page.setTotal(beans.size());
		page.addAll(beans);

		PageInfo<T> pageInfo = new PageInfo<T>(page);
		return pageInfo;
	}

	@Override
	public <T> PageInfoFacet<T> queryFacet(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		if (rowBounds != null) {
			query.setStart(rowBounds.getOffset() - 1);
			query.setRows(rowBounds.getLimit());
		} else {
			Integer start = query.getStart();
			if (start == null) {
				start = 1;
			}
			Integer rows = query.getRows();
			rowBounds = new RowBounds(start, rows);
		}
		QueryResponse response = client.query(query);
		log.info("查询文档:SolrQuery="+query+",查询结果:"+response);
		
		SolrDocumentList documentList = response.getResults();
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) documentList;
		
		Page<T> page = new Page<T>(rowBounds.getOffset(), rowBounds.getLimit());
		page.setTotal(documentList.getNumFound());
		page.addAll(list);
		PageInfo<T> pageInfo = new PageInfo<T>(page);

		PageInfoFacet<T> pageInfoFacet = new PageInfoFacet<T>();
		pageInfoFacet.setPageInfo(pageInfo);
		pageInfoFacet.setFacetFieldList(response.getFacetFields());
		return pageInfoFacet;
	}
	
	@Override
	public SolrPingResponse ping() throws SolrServerException, IOException{
		SolrClient client = this.getHttpSolrClient();
		return client.ping();
	}
}
