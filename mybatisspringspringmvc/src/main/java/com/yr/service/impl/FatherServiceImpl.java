package com.yr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.FatherDao;
import com.yr.entity.Father;
import com.yr.service.FatherService;

@Service("fatherService")
public class FatherServiceImpl implements FatherService<Father> {

	@Autowired
	private FatherDao<Father> fatherDao;

	@Override
	public void add(Father father) {
		fatherDao.add(father);
	}

	@Override
	public void delete(int id) {
		fatherDao.delete(id);
	}

	@Override
	public void update(Father father) {
		fatherDao.update(father);
	}

	@Override
	public Father getQuery(int id) {
		return fatherDao.getQuery(id);
	}

	@Transactional
	@Override
	public List<Father> query() {
		return fatherDao.query();
	}

}
