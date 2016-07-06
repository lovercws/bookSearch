package com.kingbase.bookSearch.core.solr.solrj;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * 单机solr
 * @author ganliang
 *
 */
public class StandaloneSolrServiceImpl extends SolrServiceImpl {

	private String urlPath;// solr服务器地址
	private String core;// solr的核心

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getCore() {
		return core;
	}

	public void setCore(String core) {
		this.core = core;
	}

	@Override
	public SolrClient getHttpSolrClient() {
		return new HttpSolrClient(urlPath + core);
	}

}
