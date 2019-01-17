package com.doorcheck.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
	
	@Id
	private int id;
	private String name;
	private long lastEnterDate;
	private long lastExitDate;
	
	
	public long getLastEnterDate() {
		return lastEnterDate;
	}

	public void setLastEnterDate(long lastEnterDate) {
		this.lastEnterDate = lastEnterDate;
	}

	public long getLastExitDate() {
		return lastExitDate;
	}

	public void setLastExitDate(long lastExitDate) {
		this.lastExitDate = lastExitDate;
	}
	
	
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
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
	
	

}
