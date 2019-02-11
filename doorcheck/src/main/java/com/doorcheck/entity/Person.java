package com.doorcheck.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput.View;
import org.springframework.lang.Nullable;

import com.doorcheck.dao.PersonRepo;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
@Data
@Entity
public class Person {
	
	@Id
	@JsonView(View.class)
	private String dni;
	@Nullable
	@JsonView(View.class)
	private long lastEnterDate;
	@Nullable
	@JsonView(View.class)
	private long lastExitDate;
	@JsonView(View.class)
	private String name;
	
	@OneToMany(mappedBy="person") 
	List<Estancia> estancias;
	
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
