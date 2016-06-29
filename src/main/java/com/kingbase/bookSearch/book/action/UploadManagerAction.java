package com.kingbase.bookSearch.book.action;

import java.awt.print.Book;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kingbase.bookSearch.common.action.BaseAction;

@Scope("prototype")
@Controller("UploadManagerAction")
public class UploadManagerAction extends BaseAction<Book>{

	private static final long serialVersionUID = 1570804103094060707L;

	/**
	 * 进入到上传文件设置页面
	 * @return
	 */
	public String uploadSetting(){
		return "uploadSetting";
	}
	
	/**
	 * 进入到添加书籍页面
	 * @return
	 */
	public String uploadBook(){
		return "uploadBook";
	}
	
	/**
	 * 进入到书籍上传历史记录页面
	 * @return
	 */
	public String uploadHistory(){
		return "uploadHistory";
	}
	
	/**
	 * 进入到书籍上传历史记录页面
	 * @return
	 */
	public String uploadStatistics(){
		return "uploadStatistics";
	}
	
}
