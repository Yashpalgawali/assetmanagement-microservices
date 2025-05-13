package com.example.demo.department_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.department_service.dto.Company;

 
//@FeignClient(name = "company-service",url = "localhost:7000")
@FeignClient(name = "company-service")
public interface CompanyServiceProxy {
	@GetMapping("/company/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable String id);
	

    default ResponseEntity<Company> logAndGetCompanyById(String id) {
        System.out.println("Calling /company/" + id);
        return getCompanyById(id);
    }
}
