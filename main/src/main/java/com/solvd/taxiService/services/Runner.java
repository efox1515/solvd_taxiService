package com.solvd.taxiService.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.taxiService.models.Payment;

public class lRunner {
	
	final static Logger logger = LogManager.getLogger(Runner.class.getName());

	public static void main(String[] args) throws InterruptedException {
		PaymentService paymentService = new PaymentService();
		Payment payment = paymentService.getPaymentById(2);
		logger.info(payment.getCreditCardNumber());
		logger.info(appt.getSecurityCode());
		logger.info(appt.getExpirationDate());
	}










}
