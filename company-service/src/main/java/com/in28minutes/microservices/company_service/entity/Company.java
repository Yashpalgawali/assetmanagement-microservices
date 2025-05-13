package com.in28minutes.microservices.company_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 
//@Table(name = "tbl_company")
@Document(collection = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	@Id
	private String company_id;

	private String comp_name;

}
