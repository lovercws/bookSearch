package com.kingbase.bookSearch.book.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kingbase.bookSearch.book.bean.Book;
import com.kingbase.bookSearch.common.action.BaseAction;

@Scope("prototype")
@Controller("CollectionManagerAction")
/**
 * 收藏管理
 * @author ganliang
 */
public class CollectionManagerAction extends BaseAction<Book>{

	private static final long serialVersionUID = 1689489090700203866L;

	/**
	 * 进入到收藏记录页面
	 * @return
	 */
	public String collectionHistory(){
		return "collectionHistory";
	}
}
