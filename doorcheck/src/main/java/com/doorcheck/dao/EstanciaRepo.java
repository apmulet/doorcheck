package com.doorcheck.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.doorcheck.entity.Estancia;
import com.doorcheck.entity.Person;


/**
 * 
 * @author nhome
 *
 */
public interface EstanciaRepo extends CrudRepository<Estancia,Long>{
	
	 
	public List<Estancia> findAllByPerson(Person p);
	
	
	
	//@Query("from Estancia e order by e.enter limit 50")
	public List<Estancia> findTop50ByOrderByEnterAsc();
	
 
	/**
	 * Listado de historicos de estancia
	 * @param enter
	 * @param exit
	 * @param name
	 * @return
	 */
	@Query("from Estancia e join fetch e.person p where (upper(p.name) like %:name%) and ((e.enter >= :enter) and (e.exit<=:exit))")
	public   List<Estancia> findByEstanciasLikeName(@Param("enter") long enter,@Param("exit") long exit,@Param("name") String name);
	
	/**
	 * Listado de historicos de estancia
	 * @param enter
	 * @param exit
	 * @return
	 */
	@Query("from Estancia e join fetch e.person p where ((e.enter >= :enter) and (e.exit <=:exit))")
	public   List<Estancia> findByEstancias(@Param("enter") long enter,@Param("exit") long exit);
	/**
	 * Listado de historicos de estancia
	 * @param name
	 * @return
	 */
	@Query("from Estancia e join fetch e.person p where (upper(p.name) like %:name%)")
	public   List<Estancia> findByLikeName(@Param("name") String name);
	
	

}
