package com.doorcheck;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import com.doorcheck.dao.EstanciaRepo;
import com.doorcheck.dao.PersonRepo;
import com.doorcheck.entity.Estancia;
import com.doorcheck.entity.Person;

@RequestMapping("/")
@RestController
public class DoorController {

	private final long MaximumEstaciaIfNotCheck = 4 * 60 * 60 * 1000;// 4h

	@Autowired
	private PersonRepo pRepo;
	@Autowired
	private EstanciaRepo eRepo;

	@RequestMapping("/addPerson")
	public String addPerson(Person person) {
		System.out.print("adding person");
		pRepo.save(person);
		return "register.jsp";
	}

	/**
	 * service for enter
	 * 
	 * @param dni person id
	 * @return
	 */
	@RequestMapping("/enter/{dni}")
	@ResponseBody
	public String enter(@PathVariable("dni") String dni) {
		long time = System.currentTimeMillis();
		final String[] resp = { "Error unknow dni:" + dni };
		pRepo.findById(dni).ifPresent((p) -> {
			long lastEnter = p.getLastEnterDate();
			if (lastEnter > 0) {
				// here the person do not check last exit so
				// error or create estancia with a valid constant exit calculated what ever will
				// be specified:
				// non specified solution:
				long lastExit = Math.min(time, lastEnter + MaximumEstaciaIfNotCheck);
				Estancia e = new Estancia(p, lastEnter, lastExit);
				p.setLastEnterDate(time);
				pRepo.save(p);
				eRepo.save(e);
				resp[0] = "Warning person:" + p.getName() + " do not check last EXIT,an estimated was taken";
			} else {
				System.out.print("person " + p + " enters at:" + time);
				p.setLastEnterDate(time);
				pRepo.save(p);
				resp[0] = "Check ENTER OK person:" + p.getName();
			}
		});

		return resp[0];
	}

	/**
	 * service for exit
	 * 
	 * @param dni person id
	 * @return
	 */
	@RequestMapping("/exit/{dni}")
	@ResponseBody
	public String exit(@PathVariable("dni") String dni) {
		long time = System.currentTimeMillis();
		final String[] resp = { "Error Unknow dni:" + dni };
		pRepo.findById(dni).ifPresent((p) -> {
			long lastEnter = p.getLastEnterDate();
			if (lastEnter > 0) {
				Estancia e = new Estancia(p, lastEnter, time);
				p.setLastExitDate(time);
				p.setLastEnterDate(0);
				pRepo.save(p);
				eRepo.save(e);
				resp[0] = "Check EXIT OK person:" + p.getName();
			} else {
				// caso una salida sin entrada
				// considero una
				long lastEntry = Math.max(p.getLastExitDate(), time - MaximumEstaciaIfNotCheck);
				Estancia e = new Estancia(p, lastEntry, time);
				p.setLastExitDate(time);
				p.setLastEnterDate(0);
				pRepo.save(p);
				eRepo.save(e);
				resp[0] = "Warning person:" + p.getName() + " do not check last ENTER!,an estimated was taken";
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

	/**
	 * Datatable list rest service with filters TODO:orderable & pageable aun no
	 * implementado.
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/dtlist", method = RequestMethod.POST)
	@JsonView(DataTablesOutput.View.class)
	public DataTablesOutput<Estancia> dtlist(@Valid @RequestBody CustomDataTablesInput input) {

		String name = input.name;
		if ((name != null) && (name.length() == 0))
			name = null;
		else
			name = name.toUpperCase();
		long dateStart = input.dateStart;
		long dateEnds = input.dateEnds;
		String type = input.searchType;
		List<Estancia> lst = null;
		switch (type) {
		case "inside":
			if ((dateStart == 0) && (dateEnds == 0)) { // not in range
				if (name != null) {
					lst = toEstancias(pRepo.findAllInsideNameLike(name));
				} else {
					lst = toEstancias(pRepo.findAllInside());
				}
			} else {
				if (dateEnds == 0) {
					dateEnds = Long.MAX_VALUE;
				}
				if (name == null) {
					lst = toEstancias(pRepo.findAllInsideinRange(dateStart, dateEnds));
				} else {
					lst = toEstancias(pRepo.findAllInsideinRangeLikeName(dateStart, dateEnds, name));
				}
			}
			break;
		case "outside":

			if ((dateStart == 0) && (dateEnds == 0)) { // not in range
				if (name != null) {
					lst = toEstancias(pRepo.findAllOutsideNameLike(name));
				} else {
					lst = toEstancias(pRepo.findAllOutside());
				}
			} else {
				if (dateEnds == 0) {
					dateEnds = Long.MAX_VALUE;
				}
				if (name == null) {
					lst = toEstancias(pRepo.findAllOutsideinRange(dateStart, dateEnds));
				} else {
					lst = toEstancias(pRepo.findAllOutsideinRangeLikeName(dateStart, dateEnds, name));
				}

			}

			break;
		case "history":

			if ((dateStart == 0) && (dateEnds == 0)) {
				if (name != null) {
					lst = eRepo.findByLikeName(name);
				} else {
					lst = eRepo.findTop50ByOrderByEnterAsc();// avoid full list on history until Implemented pagination
				}
			} else {
				if (dateEnds == 0) {
					dateEnds = Long.MAX_VALUE;
				}
				if (name == null) {
					lst = eRepo.findByEstancias(dateStart, dateEnds);
				} else {
					lst = eRepo.findByEstanciasLikeName(dateStart, dateEnds, name);
				}
			}
			break;
		}
		DataTablesOutput<Estancia> rsp = new DataTablesOutput<Estancia>();
		rsp.setData(lst);
		rsp.setRecordsFiltered(lst.size());
		return rsp;

	}

	/**
	 * como las estancias aun no cerradas estan en Person "status"
	 * need to build estancia list 
	 * 
	 * @param plist
	 * @return
	 */
	private List<Estancia> toEstancias(List<Person> plist) {
		List<Estancia> lst = new ArrayList<Estancia>();
		for (Person p : plist) {
			lst.add(new Estancia(p, p.getLastEnterDate(), p.getLastExitDate()));
		}
		return lst;
	}

	@RequestMapping("/estancias/{dni}")
	@ResponseBody
	public List<Estancia> listEstancias(@PathVariable("dni") String dni) {
		Optional<Person> o = pRepo.findById(dni);
		return (o.isPresent()) ? eRepo.findAllByPerson(o.get()) : Collections.emptyList();
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
