package com.solvd.taxiService.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Destination")
@XmlRootElement(name = "Destination")
@XmlAccessorType(XmlAccessType.FIELD)
public class Destination extends Model {

	@JsonProperty("address")
	@XmlElement
	private String address;

	public Destination() {
		super();
	}

	public Room(long id, String address) {
		super(id);
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
