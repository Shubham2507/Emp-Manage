package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="employees")

public class Employees {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="emp_id")
	private Integer empid;
	
	@Column(name="emp_name")
	private String name;
		
	@Column(name="emp_age")
	private Integer age;
	
	@Column(name="location")
	private String location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Dept_ID")
	private Departments department;

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public Employees(Integer empid, String name, Integer age, String location, Departments department) {
		super();
		this.empid = empid;
		this.name = name;
		this.age = age;
		this.location = location;
		this.department = department;
	}

	public Employees() {
		super();
	}
	
	

	
}
	