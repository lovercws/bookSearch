package com.kingbase.bookSearch.core.solr.bean;

import java.util.List;

import org.apache.solr.client.solrj.response.FacetField;

import com.github.pagehelper.PageInfo;


/**
 * Created by Administrator on 2016/3/30.
 */
public class PageInfoFacet<T> {

    private PageInfo<T> pageInfo;

    private List<FacetField> facetFieldList;

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<FacetField> getFacetFieldList() {
        return facetFieldList;
    }

    public void setFacetFieldList(List<FacetField> facetFieldList) {
        this.facetFieldList = facetFieldList;
    }
}
