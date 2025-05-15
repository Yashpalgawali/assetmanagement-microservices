package com.in28minutes.microservices.company_service.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.in28minutes.microservices.company_service.entity.Company;
import com.in28minutes.microservices.company_service.exception.ResourceNotFoundException;
import com.in28minutes.microservices.company_service.repository.CompanyRepository;
import com.in28minutes.microservices.company_service.service.CompanyService;

@Service("compserv")
public class CompanyServImpl implements CompanyService {

	private final CompanyRepository comprepo;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public CompanyServImpl(CompanyRepository comprepo) {
		super();
		this.comprepo = comprepo;
	}

	@Override
	public Company saveCompany(Company company) {
		 
		Company savedCompany = comprepo.save(company);
		logger.info("Saved company is {} ",savedCompany);
		return savedCompany;
	}

	@Override
	public List<Company> getAllCompanies() {
		 
		return comprepo.findAll();
	}

	@Override
	public Company getCompanyById(String id) {
	 
		return comprepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No company Found for given ID "+id));
	}

	@Override
	public int updateCompany(Company company) {
		 
		Company compObj = comprepo.save(company);
		return compObj!=null ? 1 : 0;
	}

}
