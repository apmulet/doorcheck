package com.doorcheck;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doorcheck.dao.EstanciaRepo;
import com.doorcheck.dao.PersonRepo;
import com.doorcheck.entity.Estancia;
import com.doorcheck.entity.Person;

@Controller
public class DoorController {
	
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

	@RequestMapping("/addPerson")
	public String addPerson(Person person) {
		System.out.print("adding person");
		pRepo.save(person);
		return "register.jsp";
	}

	@RequestMapping("/enter/{dni}")
	@ResponseBody
	public String enter(@PathVariable("dni") String dni) {
		long time = System.currentTimeMillis();
		final String[] resp = {"Error unknow dni:"+dni};
		pRepo.findById(dni).ifPresent((p) -> {
			long lastEnter = p.getLastEnterDate();
			if (lastEnter > 0) {
				// here the person do not check last exit so
				// error or create estancia with a valid constant exit calculated what ever will
				// be specified:
				// non specified solution:
				long lastExit = Math.min(time, lastEnter+MaximumEstaciaIfNotCheck);
				Estancia e = new Estancia(p.getDni(), lastEnter, lastExit);
				p.setLastEnterDate(time);
				pRepo.save(p);
				eRepo.save(e);
				resp[0]="Warning person:"+p.getName()+" do not check last EXIT,an estimated was taken";
			} else {
				System.out.print("person " + p + " enters at:" + time);
				p.setLastEnterDate(time);
				pRepo.save(p);
				resp[0]="Check ENTER OK person:"+p.getName();
			}
		});

		return resp[0];
	}

	@RequestMapping("/exit/{dni}")
	@ResponseBody
	public String exit(@PathVariable("dni") String dni) {
		long time = System.currentTimeMillis();
		final String[] resp = {"Error Unknow dni:"+dni};
		pRepo.findById(dni).ifPresent((p) -> {
			long lastEnter = p.getLastEnterDate();
			if (lastEnter > 0) {
				Estancia e = new Estancia(p.getDni(), lastEnter, time);
				p.setLastExitDate(time);
				p.setLastEnterDate(0);
				pRepo.save(p);
				eRepo.save(e);
				resp[0]="Check EXIT OK person:"+p.getName();
			} else {
				// caso una salida sin entrada
				// considero una 
				long lastEntry = Math.max(p.getLastExitDate(),time-MaximumEstaciaIfNotCheck);
				Estancia e = new Estancia(p.getDni(), lastEntry, time);
				p.setLastExitDate(time);
				p.setLastEnterDate(0);
				pRepo.save(p);
				eRepo.save(e);
				resp[0]="Warning person:"+p.getName()+" do not check last ENTER!,an estimated was taken";
			}
		});

		return resp[0];
	}

	@RequestMapping("/insideBuilding")
	@ResponseBody
	public List<Person> getInsideBuilding() {
		return pRepo.findAllInside();
	}

	@RequestMapping("/outsideBuilding")
	@ResponseBody
	public List<Person> getOutsideBuilding() {
		return pRepo.findAllOutside();
	}

	@RequestMapping("/list/{type}")
	@ResponseBody
	public List<Person> listPersons(@PathVariable("type") String type) {
		if (type != null)
			switch (type) {
			case "inside":
				return pRepo.findAllInside();
			case "outside":
				return pRepo.findAllOutside();
			}
		return pRepo.findAll();
	}
	
	
	@RequestMapping("/estancias/{dni}")
	@ResponseBody
	public List<Estancia> listEstancias(@PathVariable("dni") String dni) {
		return eRepo.findAllByDni(dni);
	}

	@RequestMapping("/status/{dni}")
	public String status(@PathVariable("dni") String dni) {
		final String[] status = { "unknown" };
		pRepo.findById(dni).ifPresent((p) -> {
			long lastEnter = p.getLastEnterDate();
			if (lastEnter > 0) {
				status[0] = "inside";
			} else {
				status[0] = "outside";
			}
		});
		return status[0];
	}

}
