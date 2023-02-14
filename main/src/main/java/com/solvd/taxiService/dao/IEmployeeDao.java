package com.solvd.taxiService.dao;

import java.util.List;

import com.solvd.hospitalsystem.models.employee.Employee;

public interface IEmployeeDao extends IBaseDao<Employee>{
    List<Employee> getAllEmployees() throws InterruptedException;
    List<Employee> getEmployeeByParameter(String parameter, Object value) throws InterruptedException;
}