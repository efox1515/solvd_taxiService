package com.solvd.taxiService.services;

import java.util.List;

import com.solvd.taxiService.Dao.IDestinationDao;
import com.solvd.taxiService.Dao.mysql.DestinationDao;
import com.solvd.taxiService.models.Destination;

public class DestinationService {
	private IDestinationDao destinationDao;

	public DestinationService() {
		this.destinationDao = new DestinationDao();
	}

	public List<Destination> getAllDestinations() throws InterruptedException {
		return this.DestinationDao.getAllDestinations();
	}

	public List<Destination> getDestinationsByParameter(String parameter, Object value) throws InterruptedException {
		return this.DestinationDao.getDestinationsByParameter(parameter, value);
	}

	public Destination getDestinationById(long id) throws InterruptedException {
		return this.DestinationDao.getEntityById(id);
	}

	public void updateDestination(Destination destination) throws InterruptedException {
		this.DestinationDao.updateEntity(destination);
	}

	public Destination createDestination(Destination destination) throws InterruptedException {
	}

	public void deleteDestination(long id) throws InterruptedException {
		this.DestinationDao.removeEntity(id);
	}