package com.kingbase.bookSearch.core.solr.beans;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class Book implements Serializable {

	private static final long serialVersionUID = -6142075711596750654L;
	
	@Field
	private int id;
	
	@Field
	private int bookCount;
	
	@Field
	private int bookPages;
	
	@Field
	private float bookPrice;
	
	@Field
	private String bookTitle;
	
	@Field
	private String bookAuthor;
	
	@Field
	private String bookName;
	
	@Field
	private String bookDesc;
	
	@Field
	private String bookContent;
	
	@Field
	private Date createDate;

	public Book() {
		super();
	}

	public Book(int id, int bookCount, int bookPages, float bookPrice, String bookTitle, String bookAuthor,
			String bookName, String bookDesc, String bookContent, Date createDate) {
		this.id = id;
		this.bookCount = bookCount;
		this.bookPages = bookPages;
		this.bookPrice = bookPrice;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookName = bookName;
		this.bookDesc = bookDesc;
		this.bookContent = bookContent;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public int getBookPages() {
		return bookPages;
	}

	public void setBookPages(int bookPages) {
		this.bookPages = bookPages;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public String getBookContent() {
		return bookContent;
	}

	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
