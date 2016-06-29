package com.kingbase.bookSearch.book.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kingbase.bookSearch.book.bean.FileDownload;
import com.kingbase.bookSearch.common.action.BaseAction;

@Scope("prototype")
@Controller("DownloadManagerAction")
/**
 * 书籍下载功能
 * @author ganliang
 */
public class DownloadManagerAction extends BaseAction<FileDownload>{

	private static final long serialVersionUID = 6265192174902693797L;

	/**
	 * 书籍下载功能
	 */
	public String download(){
		request.setAttribute("fileName", "fileName");
		return "download";
	}
	
	/**
	 * 书籍下载记录
	 */
	public String downloadHistory(){
		return "downloadHistory";
	}
	
	/**
	 * 书籍下载统计
	 */
	public String downloadStatistics(){
		return "downloadStatistics";
	}
}
