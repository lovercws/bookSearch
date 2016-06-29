package com.kingbase.bookSearch.book.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingbase.bookSearch.book.bean.FileDownload;
import com.kingbase.bookSearch.common.action.BaseAction;

@Scope("prototype")
@Controller("BookManagerAction")
/**
 * 书籍下载功能
 * @author ganliang
 */
public class BookManagerAction extends BaseAction<FileDownload>{

	private static final long serialVersionUID = 6265192174902693797L;

	/**
	 * 书籍列表
	 */
	public String bookList(){
		return "bookList";
	}
	
	/**
	 * 书籍分类
	 */
	public String bookClassify(){
		return "bookClassify";
	}
	
	/**
	 * 书籍分类统计
	 */
	public String bookClassifyStatistics(){
		return "bookClassifyStatistics";
	}
	
	/**
	 * 通过分类id来查询书籍
	 * @throws IOException 
	 */
	@ResponseBody
	public void queryBookByCategory() throws IOException{
		String categoryId = request.getParameter("categoryId");
		System.out.println(categoryId);
		response.getWriter().print("[]");
	}
	
	/**
	 * 通过书籍信息 来 查询书籍
	 * @throws IOException 
	 */
	@ResponseBody
	public void queryBook() throws IOException{
		response.getWriter().print("[]");
	}
}
