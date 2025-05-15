package com.example.demo.employee_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.employee_service.dto.DepartmentDto;

//@FeignClient(name =  "department-service", url = "localhost:8000")
@FeignClient(name =  "department-service")
public interface DepartmentServiceProxy {

	@GetMapping("/department/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentDtoById(@PathVariable String id);
}
