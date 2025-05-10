package com.example.demo.employee_service.dto;

import com.example.demo.employee_service.entity.Designation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private Integer emp_id;

	private String emp_name;

	private Designation designation;

	private AssetDto assetdto;
	
	private DepartmentDto departmentDto;
}
