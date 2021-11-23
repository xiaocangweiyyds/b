package com.yr.service;

import java.util.List;

public interface PeopleService<T> {

	public void add(T people);

	public void delete(int id);

	public void update(T people);

	public T getQuery(int id);

	public List<T> query();

}
