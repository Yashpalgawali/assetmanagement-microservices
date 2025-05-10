package com.example.demo.department_service.service;

import java.util.List;

import com.example.demo.department_service.entity.Department;

public interface IDepartmentService {

	public Department saveDepartment(Department dept);
	
	public List<Department> getAllDepartments();
	
	public Department getDepartmentById(Integer id);
	
	public int updateDepartment(Department dept);
}
