package com.example.demo.dao;
import java.util.List;


import com.example.demo.entity.*;
public interface IEmployee {
	List<Employee> getAllEmployee();
	Employee getemployeeById(int medicineId);
	void addEmployee(Employee medicine);
	
	void updateEmployee(Employee medicine);
	void deleteEmployee(int medicineId);
	boolean employeeExists( String name);
	
	
}
