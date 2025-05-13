package com.example.demo.department_service.controller;

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

import com.example.demo.department_service.dto.Company;
import com.example.demo.department_service.dto.DepartmentDto;
import com.example.demo.department_service.dto.ResponseDto;
import com.example.demo.department_service.entity.Department;
import com.example.demo.department_service.proxy.CompanyServiceProxy;
import com.example.demo.department_service.service.IDepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("department")  
public class DepartmentController {

	private final IDepartmentService deptserv;
	
	private final CompanyServiceProxy companyProxy;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @param deptserv
	 * @param companyProxy
	 */
	public DepartmentController(IDepartmentService deptserv, CompanyServiceProxy companyProxy) {
		super();
		this.deptserv = deptserv;
		this.companyProxy = companyProxy;
	}

	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveDepartment(@RequestBody Department department)
	{	
		Department dept = deptserv.saveDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(), "Department "+dept.getDept_name()+" is saved successfully"));
	} 
	
	@GetMapping("/")
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
		List<Department> deptList = deptserv.getAllDepartments();

		List<DepartmentDto> dept_list = deptList.stream().map(dept -> {

			DepartmentDto deptdto = new DepartmentDto();
			ResponseEntity<Company> company = companyProxy.getCompanyById(dept.getCompany_id());

			companyProxy.logAndGetCompanyById(dept.getCompany_id());

			Company companyObj = new Company();

			companyObj = company.getBody();

			deptdto.setDept_id(dept.getDept_id());
			deptdto.setDept_name(dept.getDept_name());
			deptdto.setCompany(companyObj);
			return deptdto;

		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dept_list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentbyId(@PathVariable Integer id) {
		
		Department department = deptserv.getDepartmentById(id);
		DepartmentDto deptDto = new DepartmentDto();
		deptDto.setDept_id(department.getDept_id());
		deptDto.setDept_name(department.getDept_name());
		
		ResponseEntity<Company> company = companyProxy.getCompanyById(department.getCompany_id());
		
		Company companyObj = new Company();		
		companyObj = company.getBody();
		deptDto.setCompany(companyObj);
		
//		deptDto.setComp_name(company.getBody().getComp_name());		
//		deptDto.setCompany_id(company.getBody().getCompany_id());
		
		return ResponseEntity.status(HttpStatus.OK).body(deptDto);
	}
	
	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateDepartment(@RequestBody Department department)
	{	
		int result = deptserv.updateDepartment(department);
		if(result > 0 ) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(), "Department "+department.getDept_name()+" is updated successfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto(HttpStatus.NOT_MODIFIED.toString(), "Department "+department.getDept_name()+" is not updated "));
		}		
	}
}
