package com.example.demo.employee_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.employee_service.entity.Employee;

@Repository("emprepo")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
