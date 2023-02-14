package com.solvd.taxiService.models.employee;

import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.taxiService.models.Person;

@JsonRootName("Payment")
@XmlRootElement(name = "Payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment extends Model {

	@JsonProperty("creditCardNumber")
	@XmlElement
	private String creditCardNumber;

	@JsonProperty("securityCode")
	@XmlElement
	private String securityCode;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("expirationDate")
	@XmlElement
	private Date expirationDate;


	public Payment() {
		super();
	}

	public Employee(long id, long creditCardNumber, long securityCode, date expirationDate) {
		super(id, creditCardNumber, securityCode, expirationDate);
		this.creditCardNumber = creditCardNumber;
		this.securityCode = securityCode;
		this.expirationDate = expirationDate;
	}

	public long getCreditCardNumber() {
		return phoneNumber;
	}

	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public long getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(long securityCode) {
		this.securityCode = securityCode;
	}
	
	public long getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
	}



}
