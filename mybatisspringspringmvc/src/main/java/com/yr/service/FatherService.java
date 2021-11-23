package com.yr.service;

import java.util.List;

public interface FatherService<T> {

	public void add(T father);

	public void delete(int id);

	public void update(T father);

	public T getQuery(int id);

	public List<T> query();

}
