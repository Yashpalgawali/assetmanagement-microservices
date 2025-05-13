package com.in28minutes.microservices.company_service.service;

import java.util.List;

import com.in28minutes.microservices.company_service.entity.Company;

public interface CompanyService {

	public Company saveCompany(Company company);
	public List<Company> getAllCompanies();
	public Company getCompanyById(String id);
	public int updateCompany(Company company);
}
