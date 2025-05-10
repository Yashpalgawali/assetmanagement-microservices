package com.example.demo.designation_service.entity;

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
@Table(name="tbl_designation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Designation {

	@Id
	@SequenceGenerator(name="desig_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(generator = "desig_seq",strategy = GenerationType.IDENTITY)
	private Integer designation_id;
	
	private String designation;
}
