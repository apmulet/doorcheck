package com.doorcheck.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doorcheck.entity.Person;

public interface PersonRepo extends JpaRepository<Person,String>{
    
 
	
	@Query("SELECT p FROM Person p where p.lastEnterDate=0 order by name")
	public List<Person> findAllOutside();
	
	@Query("SELECT p FROM Person p where p.lastEnterDate>0 order by name")
	public List<Person> findAllInside();
	
}
