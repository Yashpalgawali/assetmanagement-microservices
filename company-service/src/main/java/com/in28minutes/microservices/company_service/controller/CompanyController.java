package com.in28minutes.microservices.company_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.company_service.entity.Company;
import com.in28minutes.microservices.company_service.service.CompanyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("company")
@AllArgsConstructor
public class CompanyController {

	private final CompanyService compserv;
	
	@PostMapping("/")
	public ResponseEntity<Company> saveCompany(Company company) {
		Company comp = compserv.saveCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).body(comp);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> complist = compserv.getAllCompanies();
		return ResponseEntity.status(HttpStatus.OK).body(complist);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Integer id) {
		Company comp = compserv.getCompanyById(id);
		return ResponseEntity.status(HttpStatus.OK).body(comp);
	}	
}
