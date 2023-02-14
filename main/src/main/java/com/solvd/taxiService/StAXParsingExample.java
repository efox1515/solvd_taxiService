package com.solvd.taxiService;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.hospitalsystem.models.appointment.Appointment;
import com.solvd.hospitalsystem.models.appointment.AppointmentDiagnosis;
import com.solvd.hospitalsystem.models.appointment.AppointmentMedicine;
import com.solvd.hospitalsystem.models.appointment.AppointmentSymptom;

public class StAXParsingExample {
	final static Logger logger = LogManager.getLogger(StAXParsingExample.class.getName());
	private Payment payment;	
	private CreditCardNumber creditCardNumber;
	private SecurityCode securityCode;
	private ExpirationDate expirationDate;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public void parseXML() {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("Payment.xml"));
			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {	
				case XMLStreamConstants.START_ELEMENT:
					if (reader.getLocalName().equalsIgnoreCase("payment")) {
						payment = new Payment();
					} else if (reader.getLocalName().equalsIgnoreCase("creditCardNumber")) {
						payment.setCreditCardNumber(new ArrayList<>());
					} else if (reader.getLocalName().equalsIgnoreCase("creditCardNumber")) {
						payment = new CreditCardNumber();
					} else if (reader.getLocalName().equalsIgnoreCase("securityCode")) {
						payment.setSecurityCode(new ArrayList<>());
					} else if (reader.getLocalName().equalsIgnoreCase("securityCode")) {
						securityCode = new SecurityCode();
					} else if (reader.getLocalName().equalsIgnoreCase("expirationDate")) {
						payment.setExpirationDate(new ArrayList<>());
					} else if (reader.getLocalName().equalsIgnoreCase("expirationDate")) {
						expirationDate = new ExpirationDate();
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					String data = reader.getText().trim();
					if (payment != null && payment.getId() == 0) {
						payment.setId(Long.parseLong(data));
					} else if (creditCardNumber != null && creditCardNumber.getCreditCardNumber() == null) {
						creditCardNumber.setCreditCardNumber(data);
					} else if (securityCode != null && securityCode.getSecurityCode() == null) {
						securityCode.setSecurityCode(data);
					} else if (expirationDate != null && expirationDate.getExpirationDate() == null) {
						expirationDate.setExpirationDate(data);
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					if (reader.getLocalName().equalsIgnoreCase("payment")) {
						// Add appointment object to a list or do any other processing
					} else if (reader.getLocalName().equalsIgnoreCase("creditCardNumber")) {
						payment.getCreditCardNumber().add(creditCardNumber);
						creditCardNumber = null;
					} else if (reader.getLocalName().equalsIgnoreCase("securityCode")) {
						payment.getSecurityCode().add(securityCode);
						securityCode = null;
					} else if (reader.getLocalName().equalsIgnoreCase("expirationDate")) {
						payment.getExpirationDate().add(expirationDate);
						expirationDate = null;
					}
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
