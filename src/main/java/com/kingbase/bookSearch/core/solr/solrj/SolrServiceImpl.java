package com.kingbase.bookSearch.core.solr.solrj;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.formula.functions.T;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.kingbase.bookSearch.core.solr.beans.PageInfoFacet;

import junit.framework.Assert;

/**
 * @author ganliang
 */
abstract class SolrServiceImpl implements SolrService {

	public abstract SolrClient getHttpSolrClient();

	@Override
	public void add(Object bean) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		client.addBean(bean);
		client.commit();
	}

	@Override
	public void adds(Collection<?> beans) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		client.addBeans(beans);
		client.commit();
	}

	@Override
	public QueryResponse query(String query) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		QueryResponse queryResponse = client.query(new SolrQuery(query));
		return queryResponse;
	}

	@Override
	public List<T> query(String query, Class<T> clazz) throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		SolrQuery solrQuery = new SolrQuery(query);
		QueryResponse queryResponse = client.query(solrQuery);
		return queryResponse.getBeans(clazz);
	}

	@Override
	public void delete(String id) throws SolrServerException, IOException {
		SolrClient client = this.getHttpSolrClient();
		client.deleteById(id);
		client.commit();
	}

	@Override
	public void delete(List<String> ids) throws SolrServerException, IOException {
		SolrClient client = this.getHttpSolrClient();
		client.deleteById(ids);
		client.commit();
	}

	@Override
	public void deleteByQuery(String query) throws SolrServerException, IOException {
		SolrClient client = this.getHttpSolrClient();
		client.deleteByQuery(query);
		client.commit();
	}

	@Override
	public PageInfo<T> query(Class<T> clazz, SolrQuery query, RowBounds rowBounds)
			throws IOException, SolrServerException {
		SolrClient client = this.getHttpSolrClient();
		if (rowBounds != null) {
			query.setStart(rowBounds.getOffset());
			query.setRows(rowBounds.getLimit());
		} else {
			Integer start = query.getStart();
			if (start == null) {
				start = 0;
			}
			Integer rows = query.getRows();
			rowBounds = new RowBounds(start, rows);
		}
		QueryResponse response = client.query(query);
		SolrDocumentList documentList = response.getResults();
		List content = new DocumentObjectBinder().getBeans(clazz, documentList);
		// Page page=new Page(rowBounds.getOffset(),rowBounds.getLimit(),
		// (int)documentList.getNumFound());
		Page page = (Page) content;
		page.setPageNum(rowBounds.getOffset());
		page.setPageSize(rowBounds.getLimit());
		page.setTotal(documentList.getNumFound());

		PageInfo<T> pageInfo = new PageInfo<T>(page);
		return pageInfo;
	}

	@Override
	public PageInfo<T> query(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {
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
		SolrDocumentList documentList = response.getResults();
		List list = (List) documentList;
		Page page = new Page(rowBounds.getOffset(), rowBounds.getLimit());
		page.setTotal(documentList.getNumFound());
		page.addAll(list);
		PageInfo pageInfo = new PageInfo(page);
		return pageInfo;
	}

	@Override
	public PageInfoFacet<T> queryFacet(SolrQuery query, RowBounds rowBounds) throws IOException, SolrServerException {
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
		SolrDocumentList documentList = response.getResults();
		List list = (List) documentList;
		Page page = new Page(rowBounds.getOffset(), rowBounds.getLimit());
		page.setTotal(documentList.getNumFound());
		page.addAll(list);
		PageInfo pageInfo = new PageInfo(page);

		PageInfoFacet pageInfoFacet = new PageInfoFacet();
		pageInfoFacet.setPageInfo(pageInfo);
		pageInfoFacet.setFacetFieldList(response.getFacetFields());
		return pageInfoFacet;
	}
}
