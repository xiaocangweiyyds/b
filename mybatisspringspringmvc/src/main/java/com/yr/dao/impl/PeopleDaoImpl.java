package com.yr.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yr.dao.PeopleDao;
import com.yr.entity.People;
import com.yr.mapper.PeopleMapper;

@Repository("peopleDao")
public class PeopleDaoImpl implements PeopleDao<People> {

	@Autowired
	private PeopleMapper<People> peopleMapper;

	@Override
	public void add(People people) {
		peopleMapper.add(people);
	}

	@Override
	public void delete(int id) {
		peopleMapper.delete(id);
	}

	@Override
	public void update(People people) {
		peopleMapper.update(people);
	}

	@Override
	public People getQuery(int id) {	
		return peopleMapper.getQuery(id);
	}

	@Override
	public List<People> query() {
		return peopleMapper.query();
	}

}
