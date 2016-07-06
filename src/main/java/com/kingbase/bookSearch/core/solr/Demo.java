package com.kingbase.bookSearch.core.solr;

import java.io.IOException;
import java.util.Date;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.kingbase.bookSearch.core.solr.beans.Book;

public class Demo {
	
	public static void main(String[] args) {
		try {
			HttpSolrClient solrClient = new HttpSolrClient("http://192.168.92.135:8983/solr/core1");
			System.out.println(solrClient);

			Book book = new Book(1, 200, 560, 20, "lucene权威指南", "甘亮", "lucene", "数据检索工具集", "  ass", new Date());
			solrClient.addBean(book);
			solrClient.commit();

			QueryResponse queryResponse = solrClient.query(new SolrQuery("*:*"));
			System.out.println(queryResponse);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException s) {
			s.printStackTrace();
		}
	}
}
