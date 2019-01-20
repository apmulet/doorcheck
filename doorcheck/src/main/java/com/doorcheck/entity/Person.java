package com.doorcheck.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import com.doorcheck.dao.PersonRepo;

@Entity
public class Person {
	
	@Id
	private String dni;
	@Nullable
	private long lastEnterDate;
	@Nullable
	private long lastExitDate;
	private String name;
	 
	//List<Estancia> estancias;
	
	public Person() {}
	
	public Person(String dni, String name) {
		 this.dni=dni;
		 this.name=name;
	}

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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	

}
