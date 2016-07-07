package com.kingbase.bookSearch.book.bean;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * 书籍
 * 
 * @author ganliang
 */
public class Book implements Serializable {

	private static final long serialVersionUID = -3818352808679384363L;

	// 书籍主键
	private int bookId;// 书籍主键
	private int bookCateggoryId;// 书籍分类id
	private int userId;// 上传书籍者

	// 书籍基本信息
	private String bookName;// 书籍名称
	private String bookTitle;// 书籍标题
	private String bookDesc;// 书籍简要描述

	private String bookAuthor;// 书籍作者
	private String bookType;// 书籍类型
	private String isbn;// 书籍编号
	private Blob bookContent;// 书籍内容

	private float bookPrice;// 书籍市面价格
	private String pictureUrl;// 数据封面
	private String bookUrl;// 书籍存放地址

	private Date uploadDate;// 书籍上传日期

	private Date offDate;// 书籍下线日期
	private boolean isDelete;// 是否删除

	// 书籍统计分析
	private int downloadCount;// 书籍下载次数
	private int collectCount;// 收藏次数
	private int attachCount;// 关注次数

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBookCateggoryId() {
		return bookCateggoryId;
	}

	public void setBookCateggoryId(int bookCateggoryId) {
		this.bookCateggoryId = bookCateggoryId;
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

	public Blob getBookContent() {
		return bookContent;
	}

	public void setBookContent(Blob bookContent) {
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

	public Date getOffDate() {
		return offDate;
	}

	public void setOffDate(Date offDate) {
		this.offDate = offDate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public int getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}

	public int getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
}
