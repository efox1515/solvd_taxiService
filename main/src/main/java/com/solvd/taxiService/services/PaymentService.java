package com.solvd.taxiService.services;

import java.util.List;

import com.solvd.taxiService.Dao.IPaymentDao;
import com.solvd.taxiService.Dao.mysql.PaymentDao;
import com.solvd.taxiService.models.Payment;

public class PaymentService {
	private IPaymentDao PaymentDao;

	public PaymentService() {
		this.paymentDao = new PaymentDao();
	}

	public List<Payment> getAllPayments() throws InterruptedException {
		return this.PaymentDao.getAllPayments();
	}

	public List<Payment> getPaymentsByParameter(String parameter, Object value) throws InterruptedException {
		return this.PaymentDao.getPaymentsByParameter(parameter, value);
	}

	public Payment getPaymentById(long id) throws InterruptedException {
		return this.PaymentDao.getEntityById(id);
	}

	public void updatePayment(Payment Payment) throws InterruptedException {
		this.PaymentDao.updateEntity(payment);
	}

	public Payment createPayment(Payment Payment) throws InterruptedException {
		return this.PaymentDao.createEntity(payment);
	}

	public void deletePayment(long id) throws InterruptedException {
		this.PaymentDao.removeEntity(id);
	}