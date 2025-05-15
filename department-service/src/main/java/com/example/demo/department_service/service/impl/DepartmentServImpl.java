package com.example.demo.department_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.department_service.entity.Department;
import com.example.demo.department_service.exception.ResourceNotFoundException;
import com.example.demo.department_service.repository.DepartmentRepository;
import com.example.demo.department_service.service.IDepartmentService;

import lombok.AllArgsConstructor;

@Service("deptserv") 
@AllArgsConstructor
public class DepartmentServImpl implements IDepartmentService {

	private final DepartmentRepository deptrepo; 
	
	@Override
	public Department saveDepartment(Department dept) {
	
		return deptrepo.save(dept);
	}

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return deptrepo.findAll();
	}

	@Override
	public Department getDepartmentById(Integer id) {

		return deptrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Department Found for given ID "+id ));
	}
	
	@Override
	public int updateDepartment(Department dept) {
		Department department = deptrepo.save(dept);
		return department!=null ? 1 :0;			
	}

}
