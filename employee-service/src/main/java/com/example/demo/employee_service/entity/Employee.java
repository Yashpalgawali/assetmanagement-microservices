package com.example.demo.employee_service.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Employee {

	@Id
	@SequenceGenerator(name = "emp_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "emp_seq", strategy = GenerationType.IDENTITY)
	Integer emp_id;

	String emp_name;

	String designation_id;

	String dept_id;

	@Transient
	String multi_assets;

	@Transient
	List<String> asset_ids;

	@Transient
	String comments;

	@Transient
	String assigned_assets;

}
