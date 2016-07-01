package com.kingbase.bookSearch.ddl.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kingbase.bookSearch.ddl.bean.SexDDL;

@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=true)
public interface ISexDDLService {

	/**
	 * 获取所有的性别
	 * @return
	 */
	public List<SexDDL> getAll();

	/**
	 * 保存或者更新性别的数据字典
	 * @param sexDDL
	 */
	@Transactional(readOnly=false)
	public void saveOrUpdate(SexDDL sexDDL);

	/**
	 * 删除一个性别数据字典
	 * @param sexDDL
	 */
	@Transactional(readOnly=false)
	public void delete(SexDDL sexDDL);

	/**
	 * 删除所有的性别数据字典
	 */
	@Transactional(readOnly=false)
	public void deleteAll();


}
