package com.solvd.taxiService.services;

import java.util.List;

import com.solvd.taxiService.Dao.IEmployeeDao;
import com.solvd.taxiService.Dao.mysql.EmployeeDao;
import com.solvd.taxiService.models.Employee;

public class EmployeeService {
	private IEmployeeDao employeeDao;

	public EmployeeService() {
		this.employeeDao = new EmployeeDao();
	}

	public List<Employee> getAllEmployees() throws InterruptedException {
		return this.employeeDao.getAllEmployees();
	}

	public List<Employee> getEmployeesByParameter(String parameter, Object value) throws InterruptedException {
		return this.employeeDao.getEmployeesByParameter(parameter, value);
	}

	public Employee getEmployeeById(long id) throws InterruptedException {
		return this.employeeDao.getEntityById(id);
	}

	public void updateEmployee(Employee employee) throws InterruptedException {
		this.employeeDao.updateEntity(employee);
	}

	public Employee createEmployee(Employee employee) throws InterruptedException {
		return this.employeeDao.createEntity(employee);
	}

	public void deleteEmployee(long id) throws InterruptedException {
		this.employeeDao.removeEntity(id);
	}