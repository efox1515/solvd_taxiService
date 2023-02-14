package com.solvd.taxiService.models.passenger;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.taxiService.models.Person;

@JsonRootName("Passenger")
@XmlRootElement(name = "Passenger")
@XmlAccessorType(XmlAccessType.FIELD)
public class Passenger extends Person {

	public Employee() {
		super();
	}

	public Employee(long id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}

}