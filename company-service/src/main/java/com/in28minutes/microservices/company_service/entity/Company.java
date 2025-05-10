package com.in28minutes.microservices.company_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	@Id
	@SequenceGenerator(name = "comp_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "comp_seq", strategy = GenerationType.SEQUENCE)
	private Integer company_id;

	private String comp_name;

}
