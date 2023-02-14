package com.solvd.taxiService.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDestinationDao extends IBaseDao<Destination> {
    List<Destination> getAllDestinations() throws InterruptedException;
    List<Destination> getDestinationByParameter(String parameter, Object value) throws InterruptedException;

}
