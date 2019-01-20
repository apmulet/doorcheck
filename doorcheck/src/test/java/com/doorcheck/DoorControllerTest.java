package com.doorcheck;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.doorcheck.dao.EstanciaRepo;
import com.doorcheck.dao.PersonRepo;
import com.doorcheck.entity.Person;

@RunWith(SpringRunner.class)
@WebMvcTest(DoorController.class)
public class DoorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonRepo pRepo;
	
	@MockBean
	private EstanciaRepo eRepo;
	
	
	private static Person p1 = new Person("10X","Juan");
	private static Person p2 = new Person("20K","Pedro");
	private static Person p3 = new Person("30X","Carlos");

	private List<Person> insideList = Arrays.asList(p1);

	private List<Person> outsizeList = Arrays.asList(p2,p3);


	/**
	 * un Sencillo Test del Metodo listPerson
	 * @throws Exception
	 */

	@Test
	public void listPersonsTest() throws Exception {
		
		Mockito.when(pRepo.findAllInside()).thenReturn(insideList);
		Mockito.when(pRepo.findAllOutside()).thenReturn(outsizeList);
		
		RequestBuilder rqBuilder = MockMvcRequestBuilders.get("/list/inside").accept(MediaType.APPLICATION_JSON);
		// perform 
		MvcResult result = mockMvc.perform(rqBuilder).andReturn();
		
		 
		// expected json return 
		String expected = "[{dni:10X,lastEnterDate:0,lastExitDate:0,name:Juan}]";
		// assert it using JSONAssert (menos estricto)
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		

		rqBuilder = MockMvcRequestBuilders.get("/list/outside").accept(MediaType.APPLICATION_JSON);
		// perform 
		result = mockMvc.perform(rqBuilder).andReturn();
		 
		// expected json return 
		expected = "[{dni:20K,lastEnterDate:0,lastExitDate:0,name:Pedro},{dni:30X,lastEnterDate:0,lastExitDate:0,name:Carlos}]";
		// assert it using JSONAssert (menos estricto)
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        
	}

}
