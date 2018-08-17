package com.example.demo.service;
import java.util.List;
import com.example.demo.dto.DepartmentsDto;;
public interface IDepartmentService {
	List<DepartmentsDto> getAllDept();
	String deleteOneDept(int deptId);
	DepartmentsDto findOneDept(int deptId);
	DepartmentsDto addNewDept(DepartmentsDto newDto);

}
