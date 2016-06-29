package com.kingbase.bookSearch.core.solr;

import java.util.logging.Logger;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.stereotype.Service;

@Service
public class SolrServiceImpl extends SolrServiceParent {

    private static final Logger logger= Logger.getLogger(SolrServiceImpl.class.toString());

    private String urlPath;

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }


    @Override
    public SolrClient getHttpSolrClient(String core) {
        return new HttpSolrClient(urlPath+core);
    }


}
