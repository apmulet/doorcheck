package com.doorcheck.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput.View;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
@Entity
public class Estancia {

	// no encuentro como hacer un Entity sin ID
	// pero añadir un identificador unico no nos viene mal
	// puede ayudar con operaciones tipo borrar/ajustar estancias estimadas 
	// lo dejo asi para seguir avanzando y hasta que surja otra idea
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	@JsonView(View.class)
	private long id;
	
	@JsonView(View.class)
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="P_DNI", nullable=false)
	private Person person;
	 
	
	/** tiempos de entrada y salida, pude haber usado LocalDateTime
	 *   
	 */
	@JsonView(View.class)
	private long enter;
	@JsonView(View.class)
	private long exit;
	
	public Estancia() {
	}

	public Estancia(Person p, long enter, long exit) {
		this.person = p;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(String dni) {
		this.person = person;
	}

}
