package com.in28minutes.microservices.company_service.controller;

import java.util.List;

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

import com.in28minutes.microservices.company_service.dto.ResponseDto;
import com.in28minutes.microservices.company_service.entity.Company;
import com.in28minutes.microservices.company_service.service.CompanyService;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("company")
//@AllArgsConstructor
public class CompanyController {

	private final CompanyService compserv;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * @param compserv
	 */
	public CompanyController(CompanyService compserv) {
		super();
		this.compserv = compserv;
	}

	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveCompany(@RequestBody Company company) {
		Company comp = compserv.saveCompany(company);
		if (comp != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),
					"Company " + comp.getComp_name() + " is saved successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
							"Company " + company.getComp_name() + " is not saved "));
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> complist = compserv.getAllCompanies();
		return ResponseEntity.status(HttpStatus.OK).body(complist);
	}

	@GetMapping("/{id}")
	@Retry(name = "getCompanyById", fallbackMethod = "hardCodedResponse")
	public ResponseEntity<Company> getCompanyById(@PathVariable String id) {
		 
		Company comp = compserv.getCompanyById(id);
		return ResponseEntity.status(HttpStatus.OK).body(comp);
	}

	public ResponseEntity<Company> hardCodedResponse(String id, Throwable ex) {
		logger.warn("Fallback triggered for ID {}: {}", id, ex.getMessage());

		 Company fallbackCompany = new Company();
		    fallbackCompany.setCompany_id("0");
		    fallbackCompany.setComp_name("Company service taking longer time than usual");
		    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackCompany);
	}
	
	@PutMapping("/")
	public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
		Company comp = compserv.saveCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).body(comp);
	}

}
