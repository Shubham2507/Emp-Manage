package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.dto.*;
import com.example.demo.repository.*;

@Service("deptService")
public class DeptServiceImpl implements IDepartmentService {
	@Autowired
	private DepartmentRepository deptRepo;

	@Override
	public List<DepartmentsDto> getAllDept() {
		List<Departments> depts = deptRepo.findAll();
		return deptAssembler(depts);
	}

	private List<DepartmentsDto> deptAssembler(List<Departments> depts) {
		List<DepartmentsDto> ddto = new ArrayList<>();
		depts.forEach(dept -> {
			DepartmentsDto deptDto = new DepartmentsDto();
			deptDto.setDeptId(dept.getDeptId());
			deptDto.setDeptName(dept.getDeptName());
			deptDto.setLocation(dept.getLocation());
			Set<EmployeesDto> sDto = entToDto(dept.getEmployees());
			deptDto.setEmployees(sDto);
			ddto.add(deptDto);

		});
		return ddto;
	}

	@Override
	public String deleteOneDept(int deptId) {
		Departments dept = deptRepo.getOne(deptId);
		deptRepo.delete(dept);
		return "Deletion Successful";
	}

	@Override
	public DepartmentsDto findOneDept(int deptId) {
		Departments dept = deptRepo.getOne(deptId);
		DepartmentsDto ddto = new DepartmentsDto();
		ddto.setDeptId(dept.getDeptId());
		ddto.setDeptName(dept.getDeptName());
		ddto.setLocation(dept.getLocation());
		Set<EmployeesDto> sDto = entToDto(dept.getEmployees());
		ddto.setEmployees(sDto);

		return ddto;
	}

	@Override
	public DepartmentsDto addNewDept(DepartmentsDto newDto) {
		Departments dept = new Departments();
		dept.setDeptName(newDto.getDeptName());
		dept.setLocation(newDto.getLocation());
		Set<Employees> emp = empEntity(newDto.getEmployees());
		dept.setEmployees(emp);
		deptRepo.save(dept);

		return newDto;

	}

	public Set<EmployeesDto> entToDto(Set<Employees> emp) {

		Set<EmployeesDto> sDto = new HashSet<EmployeesDto>();
		emp.forEach(eSet -> {
			EmployeesDto eDto = new EmployeesDto();
			eDto.setEmpId(eSet.getEmpid());
			eDto.setName(eSet.getName());
			eDto.setAge(eSet.getAge());
			eDto.setLocation(eSet.getLocation());
			sDto.add(eDto);
		});
		return sDto;
	}

	Set<Employees> empEntity(Set<EmployeesDto> entEmp) {

		Set<Employees> entSet = new HashSet<>();
		entEmp.forEach(empSet -> {
			Employees emp = new Employees();
			emp.setEmpid(empSet.getEmpId());
			emp.setAge(empSet.getAge());
			emp.setLocation(empSet.getLocation());
			emp.setName(empSet.getName());
			entSet.add(emp);
		});
		return entSet;

	}

}
