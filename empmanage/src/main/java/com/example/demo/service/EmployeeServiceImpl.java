package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.dto.*;
import com.example.demo.repository.*;
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository empRepository;
	
//==================================================GET ALL EMPLOYEES======================================================
	@Override
	public List<EmployeesDto> getAllEmployees() {
		List<Employees> userList = empRepository.findAll();
		return empAssembler(userList);
	}
	
	DeptServiceImpl dsl = new DeptServiceImpl();
	
	
//=======================================================ASSEMBLER=========================================================
	private List<EmployeesDto> empAssembler(List<Employees> userList) {
		List<EmployeesDto> employees = new ArrayList<>();
		userList.forEach( emp -> {
			EmployeesDto empDto = new EmployeesDto();
			empDto.setEmpId(emp.getEmpid());
			empDto.setAge(emp.getAge());
			empDto.setLocation(emp.getLocation());
			empDto.setName(emp.getName());
			DepartmentsDto dDTO = convertToDtoDept(emp.getDepartment());
			empDto.setDepartment(dDTO);
						employees.add(empDto);
		});
		
		return employees;
	}


//=====================================================GET ONE EMPLOYEE=======================================================
		@Override
		public EmployeesDto findOneEmp(int empId) {
			Employees empl = empRepository.getOne(empId);
			EmployeesDto empDto = new EmployeesDto();
			empDto.setEmpId(empl.getEmpid());
			empDto.setAge(empl.getAge());
			empDto.setLocation(empl.getLocation());
			empDto.setName(empl.getName());
			DepartmentsDto dDTO = convertToDtoDept(empl.getDepartment());
			empDto.setDepartment(dDTO);
						return empDto;
		}
		
//====================================================ADD NEW EMPLOYEE=====================================================
		@Override
		public EmployeesDto addNewEmp(EmployeesDto newDto) {
				
			Employees employee = new Employees();
			employee.setName(newDto.getName());	
			employee.setAge(newDto.getAge());
			employee.setLocation(newDto.getLocation());
			Departments dept = convertToEntDept(newDto.getDepartment());
			employee.setDepartment(dept);
			empRepository.save(employee);
				
			return newDto;
		}

//============================================UPDATE EMPLOYEES(id in payload)=================================================
		@Override
		public EmployeesDto updateEmp(EmployeesDto empDto) {
			Employees emp = empRepository.getOne(empDto.getEmpId());
			emp.setLocation(empDto.getLocation());
			Departments dept = convertToEntDept(empDto.getDepartment());
			emp.setDepartment(dept);

			empRepository.save(emp);
			return empDto;
		}
		
//============================================UPDATE EMPLOYEES(id in URL)=====================================================

		@Override
		@Transactional
		public EmployeesDto updateEmpURL(int eId, EmployeesDto empDto) {
			Employees emp = empRepository.getOne(eId);
			emp.setLocation(empDto.getLocation());

			return empDto;
		}

//========================================================DELETE ONE==========================================================
	@Override
	public String deleteOneEmp(int empId) {
		empRepository.delete(empRepository.getOne(empId));
		return "Deletion Successful";		
	}
	
//========================================================DELETE ALL==========================================================
	@Override
	public void deleteAllEmp() {
		empRepository.deleteAll();
	}

//=======================================Conversions DTO to Entity and vice-versa==========================================

	private Departments convertToEntDept(DepartmentsDto ddto) {
		Departments department = new Departments();
		department.setDeptId(ddto.getDeptId());

		return department;
}

	private DepartmentsDto convertToDtoDept(Departments department) {
		DepartmentsDto ddto = new DepartmentsDto();
		ddto.setDeptId(department.getDeptId());
		ddto.setDeptName(department.getDeptName());
		ddto.setLocation(department.getLocation());
		return ddto;
}
	
	

	

}

