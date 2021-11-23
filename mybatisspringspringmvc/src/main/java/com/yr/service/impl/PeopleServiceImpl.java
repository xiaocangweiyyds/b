package com.yr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.PeopleDao;
import com.yr.entity.People;
import com.yr.service.PeopleService;

@Service("peopleService")
public class PeopleServiceImpl implements PeopleService<People> {

	@Autowired
	private PeopleDao<People> peopleDao;

	@Override
	public void add(People people) {
		peopleDao.add(people);
	}

	@Override
	public void delete(int id) {
		peopleDao.delete(id);
	}

	@Override
	public void update(People people) {
		peopleDao.update(people);
	}

	@Override
	public People getQuery(int id) {
		return peopleDao.getQuery(id);
	}

	@Transactional
	@Override
	public List<People> query() {
		return peopleDao.query();
	}

}
