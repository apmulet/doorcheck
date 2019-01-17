package com.doorcheck.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estancia {
	
	@Id
	long enter;
	long exit;
	int personId;

}
