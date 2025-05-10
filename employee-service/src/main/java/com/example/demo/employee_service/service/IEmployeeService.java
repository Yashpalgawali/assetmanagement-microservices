package com.example.demo.employee_service.service;

import java.util.List;

import com.example.demo.employee_service.entity.Employee;

public interface IEmployeeService {
	
	public Employee saveEmployee(Employee emp);
	
	public Employee getEmployeeById(Integer id);
	
	public int updateEmployee(Employee emp);
	
	public List<Employee> getAllEmployees();
}
