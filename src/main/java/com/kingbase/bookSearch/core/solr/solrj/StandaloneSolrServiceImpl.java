package com.kingbase.bookSearch.core.solr.solrj;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * 单机solr
 * 
 * @author ganliang
 *
 */
public class StandaloneSolrServiceImpl extends SolrServiceImpl {

	private String urlPath;// solr服务器地址
	private String core;// solr的核心
	private int timeout = 3000;//
	private int connectionTimeout = 1000;//
	private int maxTotalConnections = 10;//
	private int maxConnectionsPerHost = 1000;//
	private boolean allowCompression = true;//

	public String getUrlPath() {
		return urlPath;
	}

	public StandaloneSolrServiceImpl() {
		super();
	}

	public StandaloneSolrServiceImpl(String urlPath, String core) {
		super();
		this.urlPath = urlPath;
		this.core = core;
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
		HttpSolrClient httpSolrClient = new HttpSolrClient(urlPath + core);
		
		httpSolrClient.setSoTimeout(timeout);
		httpSolrClient.setConnectionTimeout(connectionTimeout);
		httpSolrClient.setMaxTotalConnections(maxTotalConnections);
		httpSolrClient.setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
		httpSolrClient.setAllowCompression(allowCompression);
		
		return httpSolrClient;
	}

}
