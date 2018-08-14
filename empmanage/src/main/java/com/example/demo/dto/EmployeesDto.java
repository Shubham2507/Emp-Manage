package com.example.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeesDto {

	@NotNull(message = "EmpID is required field")
	private Integer empId;
	@NotNull(message = "Name is required")
	private String name;
	@Min(18)
	private Integer age;
	private String location;

	private DepartmentsDto department;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
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

	public DepartmentsDto getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentsDto department) {
		this.department = department;
	}

	public EmployeesDto(@NotNull(message = "EmpID is required field") Integer empId,
			@NotNull(message = "Name is required") String name, @Min(18) Integer age, String location,
			DepartmentsDto department) {
		super();
		this.empId = empId;
		this.name = name;
		this.age = age;
		this.location = location;
		this.department = department;
	}

	public EmployeesDto() {
		super();
	}

}
