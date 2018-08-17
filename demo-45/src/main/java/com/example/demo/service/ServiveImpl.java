package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.dao.*;

@Service
public class ServiveImpl implements IService
{
	@Autowired
	private EmployeeImpl employeeDAO;

	@Override
	public List<Employee> getAllEmployee() {
		return employeeDAO.getAllEmployee();
	}

	@Override
	public Employee getemployeeById(int employeeId) {
		Employee obj=employeeDAO.getemployeeById(employeeId);
		return obj;
	}
	
	
	
	@Override
	public boolean addEmployee(Employee employee) {
		if (employeeDAO.employeeExists(employee.getFirstName())) {
			return false;
		} else {
			employeeDAO.addEmployee(employee);
			return true;
		}
	}




	@Override
	public void updateEmployee(Employee employee) {
		employeeDAO.updateEmployee(employee);

	}

	@Override
	public void deleteEmployee(int employeeId) {
		employeeDAO.deleteEmployee(employeeId);

	}


}

