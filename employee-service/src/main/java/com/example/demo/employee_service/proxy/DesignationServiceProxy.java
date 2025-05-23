package com.example.demo.employee_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.employee_service.entity.Designation;

@FeignClient(name =  "designation-service")
public interface DesignationServiceProxy {

	@GetMapping("/designation/{id}")
	public ResponseEntity<Designation> getDesignationById(@PathVariable String id);
}
