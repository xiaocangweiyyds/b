package com.yr.service;

import java.util.List;

public interface AccountService<T> {

	public void add(T account);

	public void active(String name);
	
	public void delete(int id);

	public void update(T account);

	public T getQuery(int id);

	public List<T> query();

	public Boolean login(T account);

}
