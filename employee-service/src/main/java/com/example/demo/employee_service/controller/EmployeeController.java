package com.example.demo.employee_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.employee_service.dto.AssetDto;
import com.example.demo.employee_service.dto.DepartmentDto;
import com.example.demo.employee_service.dto.EmployeeDto;
import com.example.demo.employee_service.dto.ResponseDto;
import com.example.demo.employee_service.entity.Asset;
import com.example.demo.employee_service.entity.Designation;
import com.example.demo.employee_service.entity.Employee;
import com.example.demo.employee_service.proxy.AssetServiceProxy;
import com.example.demo.employee_service.proxy.DepartmentServiceProxy;
import com.example.demo.employee_service.proxy.DesignationServiceProxy;
import com.example.demo.employee_service.service.IEmployeeService;

import lombok.AllArgsConstructor;

@RestController

@RequestMapping("employee")
public class EmployeeController {

	private final IEmployeeService empserv;
	private final DesignationServiceProxy designationproxy;
	private final AssetServiceProxy assetproxy;
	private final DepartmentServiceProxy deptproxy;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public EmployeeController(IEmployeeService empserv, DesignationServiceProxy designationproxy,
			AssetServiceProxy assetproxy, DepartmentServiceProxy deptproxy) {
		super();
		this.empserv = empserv;
		this.designationproxy = designationproxy;
		this.assetproxy = assetproxy;
		this.deptproxy = deptproxy;
	}

	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveEmployee(@RequestBody Employee employee) {

		Employee emp = empserv.saveEmployee(employee);
		if (emp != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),
					"Employee " + emp.getEmp_name() + " is saved successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		List<Employee> emplist = empserv.getAllEmployees();
		List<EmployeeDto> empListDto = emplist.stream().map(emp -> {
			EmployeeDto empdto = new EmployeeDto();
			empdto.setEmp_id(emp.getEmp_id());
			empdto.setEmp_name(emp.getEmp_name());

			ResponseEntity<Designation> designation = designationproxy.getDesignationById(emp.getDesignation_id());
//			ResponseEntity<AssetDto> asset = assetproxy.getAssetById(emp.getAsset_id());
			ResponseEntity<DepartmentDto> deptDto = deptproxy.getDepartmentDtoById(emp.getDept_id());
			ResponseEntity<AssetDto> asset = assetproxy.getAssetById(1);
			
			empdto.setDesignation(designation.getBody());
			empdto.setAssetdto(asset.getBody());
			empdto.setDepartmentDto(deptDto.getBody());
			
			return empdto;

		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(empListDto);
	} 

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id) {

		Employee emp = empserv.getEmployeeById(id);
		
		ResponseEntity<Designation> designation = designationproxy.getDesignationById(emp.getDesignation_id());
		//ResponseEntity<AssetDto> asset = assetproxy.getAssetById(emp.getAsset_id());
		ResponseEntity<AssetDto> asset = assetproxy.getAssetById(1);
		ResponseEntity<DepartmentDto> deptDto = deptproxy.getDepartmentDtoById(emp.getDept_id());
		
		EmployeeDto empDto = new EmployeeDto();
		
		empDto.setEmp_id(id);
		empDto.setEmp_name(emp.getEmp_name());
		empDto.setDesignation(designation.getBody());
		empDto.setAssetdto(asset.getBody());
		empDto.setDepartmentDto(deptDto.getBody());
		
		return ResponseEntity.status(HttpStatus.OK).body(empDto);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateEmployee(@RequestBody Employee employee) {

		int result = empserv.updateEmployee(employee);
		if (result > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),
					"Employee " + employee.getEmp_name() + " is updated successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto(
					HttpStatus.NOT_MODIFIED.toString(), "Employee " + employee.getEmp_name() + " is not updated "));
		}
	}
}
