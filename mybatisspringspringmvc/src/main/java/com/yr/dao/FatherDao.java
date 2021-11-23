package com.yr.dao;

import java.util.List;

public interface FatherDao<T> {

	public void add(T father);

	public void delete(int id);

	public void update(T father);

	public T getQuery(int id);

	public List<T> query();

}
