package com.kingbase.bookSearch.core.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;

/**
 * Created by Administrator on 2016/4/11.
 */
public class CloudSolrServerImpl extends SolrServiceParent {

    private String urlPath;

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    @Override
    public SolrClient getHttpSolrClient(String core) {
        //参数：zookeeper服务器的地址列表。
        CloudSolrClient server = new CloudSolrClient(urlPath);
        //指定默认连接的collection
        server.setDefaultCollection(core);
        return server;
    }
}
