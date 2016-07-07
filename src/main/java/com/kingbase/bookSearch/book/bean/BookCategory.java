package com.kingbase.bookSearch.book.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 书籍分类
 * @author ganliang
 *
 */
public class BookCategory implements Serializable {

	private static final long serialVersionUID = 1885480123100967101L;

	private int categoryId;// 分类id
	private int parentCategoryId;// 父分类id

	private String categoryCode;// 菜单代号
	private String categoryTitle;// 菜单标题
	private String categoryDesc;// 分类简述

	private int categoryOrder;// 分类顺序

	private boolean isUse;// 菜单是否显示
	private Date createDate;// 分类创建日期

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public int getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public boolean isUse() {
		return isUse;
	}

	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
