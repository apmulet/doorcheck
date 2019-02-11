package com.doorcheck;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput.View;
 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.annotation.JsonView;

import com.doorcheck.dao.EstanciaRepo;
import com.doorcheck.dao.PersonRepo;
import com.doorcheck.entity.Estancia;
import com.doorcheck.entity.Person;
@RequestMapping("/")
@Controller
public class RouterController {
	
	private final long MaximumEstaciaIfNotCheck = 4*60*60*1000;// 4h

	@Autowired
	private PersonRepo pRepo;
	@Autowired
	private EstanciaRepo eRepo;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("/home")
	public String home1() {
		return "home.jsp";
	}

	@RequestMapping("/register")
	public String register() {
		return "register.jsp";
	}

	@RequestMapping("/list")
	public String list() {
		return "list.jsp";
	}

	@RequestMapping("/doorcheck")
	public String doorcheck() {
		return "doorcheck.jsp";
	}

 
}
