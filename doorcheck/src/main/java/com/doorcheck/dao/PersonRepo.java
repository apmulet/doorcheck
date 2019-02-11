package com.doorcheck.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doorcheck.entity.Person;
import com.doorcheck.entity.PersonEstancia;

public interface PersonRepo extends JpaRepository<Person,String>{
	
	@Query("FROM Person p where p.lastEnterDate=0 order by name")
	public List<Person> findAllOutside();
	
	@Query("FROM Person p where (p.lastEnterDate > 0) order by name")
	public List<Person> findAllInside();
	
	@Query("from Person p WHERE (p.lastEnterDate=0) and  (upper(p.name) LIKE %:name%)")
	public List<Person> findAllOutsideNameLike(@Param("name") String name);
	
	@Query("FROM Person p where ((p.lastEnterDate>0) and (upper(p.name) like %:name%))")
	public List<Person> findAllInsideNameLike(@Param("name") String name);
 	
	@Query("from Person p  where (p.lastEnterDate = 0) and (p.lastExitDate>=:enter) and (p.lastExitDate<=:exit)")
	public   List<Person> findAllOutsideinRange(@Param("enter") long enter,@Param("exit") long exit);
	
	@Query("from Person p join p.estancias e where (upper(p.name) like %:name%) and (p.lastEnterDate = 0) and (p.lastExitDate>=:enter) and (p.lastExitDate<=:exit)")
	public   List<Person> findAllOutsideinRangeLikeName(@Param("enter") long enter,@Param("exit") long exit,@Param("name") String name);
	
	@Query("from Person p  where (p.lastEnterDate >= 0) and (p.lastEnterDate>=:enter) and (p.lastEnterDate<=:exit)")
	public   List<Person> findAllInsideinRange(@Param("enter") long enter,@Param("exit") long exit);
	
	@Query("from Person p join p.estancias e where (upper(p.name) like %:name%) and (p.lastEnterDate >= 0) and (p.lastEnterDate>=:enter) and (p.lastEnterDate<=:exit)")
	public   List<Person> findAllInsideinRangeLikeName(@Param("enter") long enter,@Param("exit") long exit,@Param("name") String name);
	
	
}
