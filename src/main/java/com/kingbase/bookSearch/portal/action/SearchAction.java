package com.kingbase.bookSearch.portal.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.portal.bean.SearchBean;

@Scope("prototype")
@Controller("SearchAction")
public class SearchAction extends BaseAction<SearchBean> {

	private static final long serialVersionUID = -3447200358759108816L;

	private SearchBean searchBean = this.getModel();

	/**
	 * 获取搜索页面头
	 * @return
	 */
	public String header() {
		return "header";
	}

	/**
	 * 获取搜索页面体
	 * @return
	 */
	public String main() {
		return "main";
	}

	/**
	 * 获取搜索页面尾
	 * @return
	 */
	public String bottom() {
		return "bottom";
	}

	/**
	 * 搜索
	 * @return
	 */
	public String search() {
		String content = searchBean.getContent();
		System.out.println(content);
		return "search";
	}

	public String showSearchDetail() {
		return "searchDetail";
	}
}
