package com.kingbase.bookSearch.core.solr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

import org.apache.ibatis.session.RowBounds;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import com.github.pagehelper.PageInfo;
import com.kingbase.bookSearch.core.solr.bean.BookField;
import com.kingbase.bookSearch.core.solr.bean.PageInfoFacet;
import com.kingbase.bookSearch.core.solr.solrj.StandaloneSolrServiceImpl;

public class Demo {

	private static final String urlPath = "http://192.168.8.144:8983/solr/";
	private static final String core = "core1";

	@Test
	public void testAdd() throws IOException{
		
		RandomAccessFile rm=new RandomAccessFile(new File("F:\\solr-5.5.2\\server\\solr\\configsets\\sample_techproducts_configs\\conf\\managed-schema"), "r");
		FileChannel channel = rm.getChannel();
		
		StringBuilder builder=new StringBuilder();
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		int length=-1;
		while((length=channel.read(buffer))>-1){
			buffer.rewind();
			byte[] bytes=new byte[buffer.remaining()];
			buffer.get(bytes);
			builder.append(new String(bytes,0,length));
			buffer.rewind();
		}
		rm.close();
		channel.close();
		
		System.out.println(builder.toString());
		try {
			StandaloneSolrServiceImpl solrService = new StandaloneSolrServiceImpl(urlPath, core);
			solrService.add(new BookField(1, 1, "linux", "linux大全", "linux系统全面的指示", "甘亮", "pdf", "1234", builder.toString(), 12, "", "", new Date()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuery() {
		StandaloneSolrServiceImpl solrService = new StandaloneSolrServiceImpl(urlPath, core);
		try {
			SolrQuery solrQuery=new SolrQuery("bookTitle:*");
			
			PageInfo<Object> pageInfo = solrService.query(solrQuery, new RowBounds(1, 10));
			System.out.println(pageInfo.getList().get(0));
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryHighlight() {
		StandaloneSolrServiceImpl solrService = new StandaloneSolrServiceImpl(urlPath, core);
		try {
			SolrQuery solrQuery=new SolrQuery("bookTitle:*");
			solrQuery.setHighlight(true);
			solrQuery.addHighlightField("bookTitle");// 高亮字段
			solrQuery.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
			solrQuery.setHighlightSimplePost("</font>");//后缀
			solrQuery.setHighlightSnippets(1);//结果分片数，默认为1
			solrQuery.setHighlightFragsize(1000);//每个分片的最大长度，默认为100
			
			PageInfoFacet<Object> queryFacet = solrService.queryFacet(solrQuery, new RowBounds(1, 10));
			System.out.println(queryFacet.getPageInfo());
			System.out.println(queryFacet.getPageInfo());
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryPage() {
		StandaloneSolrServiceImpl solrService = new StandaloneSolrServiceImpl(urlPath, core);
		try {
			SolrQuery solrQuery=new SolrQuery("bookTitle:*");
			
			PageInfo<BookField> query = solrService.query(solrQuery, new RowBounds(1, 10));
			System.out.println(query);
			System.out.println(query.getList().get(0));
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryFacet() {
		StandaloneSolrServiceImpl solrService = new StandaloneSolrServiceImpl(urlPath, core);
		try {
			SolrQuery solrQuery=new SolrQuery("bookTitle:*");
			
			PageInfoFacet<BookField> queryFacet = solrService.queryFacet(solrQuery, new RowBounds(1, 10));
			System.out.println(queryFacet.getPageInfo());
			System.out.println(queryFacet.getFacetFieldList());
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGet() {
		StandaloneSolrServiceImpl solrService = new StandaloneSolrServiceImpl(urlPath, core);
		try {
			solrService.get("1");
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		StandaloneSolrServiceImpl solrService = new StandaloneSolrServiceImpl(urlPath, core);
		try {
			solrService.delete("1");
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
