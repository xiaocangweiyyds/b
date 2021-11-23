package com.yr.mapper;

import java.util.List;

public interface PeopleMapper<T> {

	public void add(T people);

	public void delete(int id);

	public void update(T people);

	public T getQuery(int id);

	public List<T> query();

}
