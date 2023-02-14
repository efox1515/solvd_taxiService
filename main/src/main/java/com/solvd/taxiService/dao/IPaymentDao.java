package com.solvd.taxiService.dao;

import java.sql.SQLException;
import java.util.List;

public interface IPaymentDao extends IBaseDao<Payment> {
    List<Payment> getAllPayments() throws InterruptedException;
    List<Payment> getPaymentByParameter(String parameter, Object value) throws InterruptedException;

}
