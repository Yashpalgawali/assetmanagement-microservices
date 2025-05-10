package com.in28minutes.microservices.company_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.microservices.company_service.entity.Company;

@Repository("comprepo")
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
