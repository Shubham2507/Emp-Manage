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
	public Employee getemployeeById(int medicineId) {
		Employee obj=employeeDAO.getemployeeById(medicineId);
		return obj;
	}
	
	
	
	@Override
	public boolean addEmployee(Employee medicine) {
		if (employeeDAO.employeeExists(medicine.getFirstName())) {
			return false;
		} else {
			employeeDAO.addEmployee(medicine);
			return true;
		}
	}




	@Override
	public void updateEmployee(Employee medicine) {
		employeeDAO.updateEmployee(medicine);

	}

	@Override
	public void deleteEmployee(int medicineId) {
		employeeDAO.deleteEmployee(medicineId);

	}


}

