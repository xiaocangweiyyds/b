package com.yr.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yr.dao.FatherDao;
import com.yr.entity.Father;
import com.yr.mapper.FatherMapper;

@Repository("fatherDao")
public class FatherDaoImpl implements FatherDao<Father> {

	@Autowired
	private FatherMapper<Father> fatherMapper;

	@Override
	public void add(Father father) {
		fatherMapper.add(father);
	}

	@Override
	public void delete(int id) {
		fatherMapper.delete(id);
	}

	@Override
	public void update(Father father) {
		fatherMapper.update(father);
	}

	@Override
	public Father getQuery(int id) {	
		return fatherMapper.getQuery(id);
	}

	@Override
	public List<Father> query() {
		return fatherMapper.query();
	}

}
