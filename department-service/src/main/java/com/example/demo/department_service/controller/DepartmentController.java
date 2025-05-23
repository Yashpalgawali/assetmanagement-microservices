package com.example.demo.department_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.client.RestTemplate;

import com.example.demo.department_service.dto.Company;
import com.example.demo.department_service.dto.DepartmentDto;
import com.example.demo.department_service.dto.ResponseDto;
import com.example.demo.department_service.entity.Department;
import com.example.demo.department_service.proxy.CompanyServiceProxy;
import com.example.demo.department_service.service.IDepartmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

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
	public ResponseEntity<ResponseDto> saveDepartment(@RequestBody Department department) {
		Department dept = deptserv.saveDepartment(department);
		if (dept != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),
					"Department " + dept.getDept_name() + " is saved successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
							"Department " + department.getDept_name() + " is not saved "));
		}
	}

	@GetMapping("/")
//	@Retry(name = "getCompanyByIdForDeptList", fallbackMethod = "handleCompanyResponse")
	@CircuitBreaker(name = "default" , fallbackMethod = "handleCompanyResponse")
	@RateLimiter(name = "getCompanyByIdForDeptListRateLimiter")
	public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
		List<Department> deptList = deptserv.getAllDepartments();

		List<DepartmentDto> dept_list = deptList.stream().map(dept -> {

			DepartmentDto deptdto = new DepartmentDto();

			ResponseEntity<Company> company = companyProxy.getCompanyById(dept.getCompany_id());

			Map<String, String> hashMap = new HashMap<>();
			hashMap.put("id", dept.getCompany_id());
 
			deptdto.setDept_id(dept.getDept_id());
			deptdto.setDept_name(dept.getDept_name());
			deptdto.setCompany(company.getBody());
			return deptdto;

		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(dept_list);
	}

	public ResponseEntity<List<DepartmentDto>> handleCompanyResponse(Exception ex) {

		List<Department> allDepts = deptserv.getAllDepartments();
		List<DepartmentDto> deptDtoList = allDepts.stream().map(d -> {
			DepartmentDto deptdto = new DepartmentDto();

			deptdto.setDept_id(d.getDept_id());
			deptdto.setDept_name(d.getDept_name());

			Company comp = new Company();
			deptdto.setCompany(comp);

			return deptdto;
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(deptDtoList);
	}

	@GetMapping("/{id}")
	@Retry(name = "getCompanyById", fallbackMethod = "handleGetCompanyByIdResponse")
	public ResponseEntity<DepartmentDto> getDepartmentbyId(@PathVariable Integer id) {

		Department department = deptserv.getDepartmentById(id);
		DepartmentDto deptDto = new DepartmentDto();
		deptDto.setDept_id(department.getDept_id());
		deptDto.setDept_name(department.getDept_name());

		ResponseEntity<Company> company = companyProxy.getCompanyById(department.getCompany_id());

		Company companyObj = new Company();
		companyObj = company.getBody();
		deptDto.setCompany(companyObj);

		return ResponseEntity.status(HttpStatus.OK).body(deptDto);
	}
	
	public ResponseEntity<DepartmentDto> handleGetCompanyByIdResponse(Integer id,Exception ex) {

		Department department = deptserv.getDepartmentById(id);
		DepartmentDto deptDto = new DepartmentDto();
		deptDto.setDept_id(department.getDept_id());
		deptDto.setDept_name(department.getDept_name());

		deptDto.setCompany(new Company());

		return ResponseEntity.status(HttpStatus.OK).body(deptDto);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateDepartment(@RequestBody Department department) {
		int result = deptserv.updateDepartment(department);
		if (result > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),
					"Department " + department.getDept_name() + " is updated successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
					.body(new ResponseDto(HttpStatus.NOT_MODIFIED.toString(),
							"Department " + department.getDept_name() + " is not updated "));
		}
	}
}
