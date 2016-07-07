package com.kingbase.bookSearch.core.solr.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.solr.client.solrj.SolrClient;

import com.kingbase.bookSearch.core.solr.solrj.StandaloneSolrServiceImpl;

public class MulThread {
	private static final String urlPath = "http://192.168.8.144:8983/solr/";
	private static final String core = "core1";
	
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 1000; i++) {
			final int j=i;
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					StandaloneSolrServiceImpl solrService = new StandaloneSolrServiceImpl(urlPath, core);
					SolrClient httpSolrClient = solrService.getHttpSolrClient();
					System.out.println(j+" "+httpSolrClient);
				}
			});
		}
		
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		threadPool.shutdown();
	}
}
