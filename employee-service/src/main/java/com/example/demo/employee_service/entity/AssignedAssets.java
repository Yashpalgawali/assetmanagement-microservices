package com.example.demo.employee_service.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

public class AssignedAssets {

	private Long assigned_asset_id;

//	@ManyToOne(targetEntity = Employee.class , cascade = {CascadeType.MERGE})
//	@JoinColumn(name="emp_id",referencedColumnName = "emp_id")
	private Employee employee;

	private String assign_date;

	private String assign_time;

	@Transient
	private String assigned_assets;

	@Transient
	private List<String> ass_assets;

	@Transient
	private List<String> assigned_asset_types;

	@Transient
	private String assigned;

	@Transient
	private String assigned_types;
	
	@Transient
	private String model_numbers;
	
	@Transient
	private Long asset_id;
	
	@Transient
	private Long emp_id;
	
	@Transient
	private String multi_assets;

}
