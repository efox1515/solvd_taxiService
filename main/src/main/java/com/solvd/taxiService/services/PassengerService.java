package com.solvd.taxiService.services;

import java.util.List;

import com.solvd.taxiService.Dao.IPassengerDao;
import com.solvd.taxiService.Dao.mysql.PassengerDao;
import com.solvd.taxiService.models.Passenger;

public class PassengerService {
	private IPassengerDao PassengerDao;

	public PassengerService() {
		this.passengerDao = new PassengerDao();
	}

	public List<Passenger> getAllPassengers() throws InterruptedException {
		return this.PassengerDao.getAllPassengers();
	}

	public List<Passenger> getPassengersByParameter(String parameter, Object value) throws InterruptedException {
		return this.PassengerDao.getPassengersByParameter(parameter, value);
	}

	public Passenger getPassengerById(long id) throws InterruptedException {
		return this.PassengerDao.getEntityById(id);
	}

	public void updatePassenger(Passenger Passenger) throws InterruptedException {
		this.PassengerDao.updateEntity(passenger);
	}

	public Passenger createPassenger(Passenger Passenger) throws InterruptedException {
		return this.PassengerDao.createEntity(passenger);
	}

	public void deletePassenger(long id) throws InterruptedException {
		this.PassengerDao.removeEntity(id);
	}