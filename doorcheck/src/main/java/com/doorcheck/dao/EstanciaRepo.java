package com.doorcheck.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.doorcheck.entity.Estancia;
import com.doorcheck.entity.Person;

public interface EstanciaRepo extends CrudRepository<Estancia,Long>{
	
	@Query("SELECT e FROM Estancia e where e.dni=?1 order by enter DESC")
	public List<Estancia> findAllByDni(String dni);

}
