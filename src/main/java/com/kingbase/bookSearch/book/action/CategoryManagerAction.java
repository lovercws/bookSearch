package com.kingbase.bookSearch.book.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingbase.bookSearch.book.bean.BookCategory;
import com.kingbase.bookSearch.common.action.BaseAction;

@Scope("prototype")
@Controller("CategoryManagerAction")
/**
 * 书籍分类管理
 * @author ganliang
 */
public class CategoryManagerAction extends BaseAction<BookCategory>{

	private static final long serialVersionUID = -996237449582443607L;

	/**
	 * 获取分类下拉框树
	 * @throws IOException
	 */
	@ResponseBody
	public void getCategoryCombotree() throws IOException{
		response.getWriter().print("[]");
	}
	
	/**
	 * 获取分类列表
	 * @throws IOException
	 */
	public String categoryTable() throws IOException{
		return "categoryTable";
	}
	
	/**
	 * 获取分类树
	 * @throws IOException
	 */
	public String categoryTree() throws IOException{
		return "categoryTree";
	}
	
}
