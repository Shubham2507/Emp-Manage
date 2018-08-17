package com.example.demo.dao;
import java.util.List;


import com.example.demo.entity.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;



@Transactional
@Repository
public class EmployeeImpl implements IEmployee {
	@PersistenceContext	
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployee() {
		String hql = "FROM Employee as atcl ORDER BY atcl.id";
		return (List<Employee>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Employee getemployeeById(int employeeId) {
		return entityManager.find(Employee.class, employeeId);
	}

	@Override
	public void addEmployee(Employee employee) {
		entityManager.persist(employee);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee artcl = getemployeeById(employee.getId());
		artcl.setFirstName(employee.getFirstName());
		artcl.setLastName(employee.getLastName());
		artcl.setLocation(employee.getLocation());
		entityManager.flush();
	}

	@Override
	public void deleteEmployee(int employeeId) {
		entityManager.remove(getemployeeById(employeeId));
		
	}

	@Override
	public boolean employeeExists(String name) {
		String hql = "FROM Employee WHERE Firstname = '"+name+"'"; 
		return entityManager.createQuery(hql).getResultList().size() > 0 ? true : false;
	}

	

	

}
