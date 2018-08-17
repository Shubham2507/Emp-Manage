package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.*;

public interface IService {
	List<Employee> getAllEmployee();

	Employee getemployeeById(int employeeid);

	

	boolean addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(int employeeid);

}
