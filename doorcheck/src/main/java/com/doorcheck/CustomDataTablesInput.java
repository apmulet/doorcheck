package com.doorcheck;

 
import javax.annotation.Nullable;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput.View;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
/**
 * campos personalizados de los filtros 
 * @author nhome
 *
 */
@Data
public class CustomDataTablesInput extends DataTablesInput {
	
	/**
	 * name (like) 
	 */
	 
	public String name;
	 
	/**
	 *  rango de fechas 
	 */
	 
	public long dateStart;
	 
	public long dateEnds;
	
	/**
	 * tipo de busqueda inside/outside/history
	 */
	public String searchType;

}
