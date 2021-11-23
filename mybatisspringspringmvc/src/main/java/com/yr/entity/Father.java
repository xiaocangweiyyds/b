package com.yr.entity;

import java.util.List;

public class Father {

	private int id;
	private String name;
	private List<People> people;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<People> getPeople() {
		return people;
	}

	public void setPeople(List<People> people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "Father [id=" + id + ", name=" + name + ", people=" + people + "]";
	}

}
