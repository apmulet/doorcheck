package com.doorcheck.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estancia {

	// no encuentro como hacer un Entity sin ID
	// pero añadir un identificador unico no nos viene mal
	// puede ayudar con operaciones tipo borrar/ajustar estancias estimadas 
	// lo dejo asi para seguir avanzando y hasta que surja otra idea
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	private String dni;
	private long enter;
	private long exit;
	
	public Estancia() {
	}

	public Estancia(String dni, long enter, long exit) {
		this.dni = dni;
		this.enter = enter;
		this.exit = exit;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getEnter() {
		return enter;
	}

	public void setEnter(long enter) {
		this.enter = enter;
	}

	public long getExit() {
		return exit;
	}

	public void setExit(long exit) {
		this.exit = exit;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
