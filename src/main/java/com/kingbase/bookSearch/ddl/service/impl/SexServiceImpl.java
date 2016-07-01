package com.kingbase.bookSearch.ddl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingbase.bookSearch.ddl.bean.SexDDL;
import com.kingbase.bookSearch.ddl.dao.ISexDDLDao;
import com.kingbase.bookSearch.ddl.service.ISexDDLService;

@Service
public class SexServiceImpl implements ISexDDLService {

	@Resource
	private ISexDDLDao sexDDLDao;

	@Override
	public List<SexDDL> getAll() {
		List<SexDDL> sexList = sexDDLDao.findAll();
		return sexList;
	}

	@Override
	public void saveOrUpdate(SexDDL sexDDL) {
		sexDDLDao.saveOrUpdate(sexDDL);
	}

	@Override
	public void delete(SexDDL sexDDL) {
		sexDDLDao.delete(sexDDL);
	}

	@Override
	public void deleteAll() {
		sexDDLDao.deleteAll();
	}

}
