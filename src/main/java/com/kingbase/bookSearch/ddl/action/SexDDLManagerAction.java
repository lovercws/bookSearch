package com.kingbase.bookSearch.ddl.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kingbase.bookSearch.common.action.BaseAction;
import com.kingbase.bookSearch.ddl.bean.SexDDL;
import com.kingbase.bookSearch.ddl.service.ISexDDLService;

@Scope("prototype")
@Controller("SexDDLManagerAction")
/**
 * 性别数据字典
 * @author ganliang
 */
public class SexDDLManagerAction extends BaseAction<SexDDL> {

	private static final long serialVersionUID = 6265192174902693797L;
    private static final Logger log=Logger.getLogger(SexDDLManagerAction.class);
    
    private SexDDL sexDDL=this.getModel();
    private Gson gson=new Gson();
    
	@Resource
	private ISexDDLService sexDDLService;

	/**
	 * 性别列表
	 */
	public String table() {
		log.info("获取性别-->"+sexDDL);
		List<SexDDL> sexList=sexDDLService.getAll();
		
		log.info("性别列表-->>"+sexList);
		request.setAttribute("sexList", gson.toJson(sexList).replace("\"", "'"));
		return "table";
	}
	
	/**
	 * 保存性别数据字典
	 * @throws IOException 
	 */
	@ResponseBody
	public void save() throws IOException{
		sexDDL = gson.fromJson(sexDDL.getData(), SexDDL.class);
		log.info("保存性别数据字典-->"+sexDDL);
		sexDDLService.saveOrUpdate(sexDDL);
		response.getWriter().print("{success:true}");
	}
	
	/**
	 * 保存性别数据字典
	 * @throws IOException 
	 */
	@ResponseBody
	public void delete() throws IOException{
		log.info("删除性别数据字典-->"+sexDDL);
		sexDDLService.delete(sexDDL);
		response.getWriter().print("{success:true}");
	}
	
	/**
	 * 保存性别数据字典
	 * @throws IOException 
	 */
	@ResponseBody
	public void deleteAll() throws IOException{
		log.info("删除所有性别数据字典-->");
		sexDDLService.deleteAll();
		response.getWriter().print("{success:true}");
	}
}
