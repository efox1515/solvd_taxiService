package com.solvd.taxiService.dao;

import java.util.List;

import com.solvd.taxiService.models.passenger.Passenger;

public interface IPassengerDao extends IBaseDao<Passenger>{
	List<Passenger> getAllPassengers() throws InterruptedException;
	List<Passenger> getPassengerByParameter(String parameter, Object value) throws InterruptedException;
}
