package com.yr.mapper;

import java.util.List;

public interface AccountMapper<T> {

	public void add(T account);

	public void delete(int id);

	public void update(T account);

	public T getQuery(int id);

	public List<T> query();

	public int login(T account);

	public void active(String name);
}
