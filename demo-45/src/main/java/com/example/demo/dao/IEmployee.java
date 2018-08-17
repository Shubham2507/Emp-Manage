package com.example.demo.dao;
import java.util.List;


import com.example.demo.entity.*;
public interface IEmployee {
	List<Employee> getAllEmployee();
	Employee getemployeeById(int employeeId);
	void addEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	boolean employeeExists( String name);
	
	
}
