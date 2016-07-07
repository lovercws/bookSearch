package com.kingbase.bookSearch.core.solr.solrj;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;

/**
 * 集群 solr
 * @author ganliang
 *
 */
public class CloudSolrServerImpl extends SolrServiceImpl {

	private String urlPath;// zookeeper服务器的地址列表
	private String core;// solr的核心

	public CloudSolrServerImpl() {
		super();
	}

	public CloudSolrServerImpl(String urlPath, String core) {
		super();
		this.urlPath = urlPath;
		this.core = core;
	}

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
		CloudSolrClient server = new CloudSolrClient(urlPath);
		server.setDefaultCollection(core);
		return server;
	}
}
