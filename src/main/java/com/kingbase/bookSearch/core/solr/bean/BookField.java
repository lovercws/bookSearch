package com.kingbase.bookSearch.core.solr.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 保存到solr的字段
 * @author ganliang
 *
 */
public class BookField implements Serializable {

	private static final long serialVersionUID = -6142075711596750654L;

	@Field
	private int bookId;// 书籍主键

	@Field
	private int userId;// 上传书籍者 用户id

	@Field
	private String bookName;// 书籍名称

	@Field
	private String bookTitle;// 书籍标题

	@Field
	private String bookDesc;// 书籍简要描述

	@Field
	private String bookAuthor;// 书籍作者

	@Field
	private String bookType;// 书籍类型

	@Field
	private String isbn;// 书籍编号

	@Field
	private String bookContent;// 书籍内容

	@Field
	private float bookPrice;// 书籍市面价格

	@Field
	private String pictureUrl;// 数据封面

	@Field
	private String bookUrl;// 书籍存放地址

	@Field
	private Date uploadDate;// 书籍上传日期

	public BookField() {
		super();
	}

	public BookField(int bookId, int userId, String bookName, String bookTitle, String bookDesc, String bookAuthor,
			String bookType, String isbn, String bookContent, float bookPrice, String pictureUrl, String bookUrl,
			Date uploadDate) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.bookName = bookName;
		this.bookTitle = bookTitle;
		this.bookDesc = bookDesc;
		this.bookAuthor = bookAuthor;
		this.bookType = bookType;
		this.isbn = isbn;
		this.bookContent = bookContent;
		this.bookPrice = bookPrice;
		this.pictureUrl = pictureUrl;
		this.bookUrl = bookUrl;
		this.uploadDate = uploadDate;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookContent() {
		return bookContent;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

}


/* 
<!-- 书籍主键 -->
<field name="bookId" type="string" indexed="true" stored="true" required="true"/> 

<!-- 上传书籍者 用户id -->
<field name="userId"  type="int"    indexed="true"  stored="true"/>
<field name="bookName" type="int"    indexed="true"  stored="true"/>
<field name="bookTitle" type="string"  indexed="true"  stored="true"/>
<field name="bookDesc"  type="string"  indexed="true"  stored="true" />
<field name="bookAuthor"  type="string"  indexed="true"  stored="true" />
<field name="bookType" type="string"  indexed="true"  stored="true"/>
<field name="isbn" type="string"  indexed="true"  stored="true"/>
<field name="bookContent"  type="text_en"    indexed="true"  stored="true"/>
<field name="bookPrice"  type="float"    indexed="true"  stored="true"/>
<field name="pictureUrl"  type="string"    indexed="true"  stored="true"/>
<field name="bookUrl"  type="string"    indexed="true"  stored="true"/>
<field name="uploadDate"  type="date"    indexed="true"  stored="true"/>
*/