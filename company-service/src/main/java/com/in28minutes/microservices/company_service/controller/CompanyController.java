package com.in28minutes.microservices.company_service.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.company_service.dto.ResponseDto;
import com.in28minutes.microservices.company_service.entity.Company;
import com.in28minutes.microservices.company_service.service.CompanyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("company")
@AllArgsConstructor
public class CompanyController {
 
	private final CompanyService compserv;
	 
	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveCompany(@RequestBody Company company) {
		Company comp = compserv.saveCompany(company);
		if(comp!=null)
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),"Company "+comp.getComp_name()+" is saved successfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"Company "+company.getComp_name()+" is not saved "));
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> complist = compserv.getAllCompanies();
		return ResponseEntity.status(HttpStatus.OK).body(complist);
	} 
		
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable String id) {
	    Company comp = compserv.getCompanyById(id);
	    return ResponseEntity.status(HttpStatus.OK).body(comp);
	}
	
	
	@PutMapping("/")
	public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
		Company comp = compserv.saveCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).body(comp);
	}
	 
}
