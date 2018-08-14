package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Departments;

@Repository("deptRepository")
public interface DepartmentRepository extends JpaRepository<Departments, Integer> {

}
