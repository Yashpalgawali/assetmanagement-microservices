package com.example.demo.designation_service.service;

import java.util.List;

import com.example.demo.designation_service.entity.Designation;

public interface IDesignationService {

	public Designation saveDesignation(Designation designation);
	
	public Designation getDesignationById(Integer id);
	
	public int updateDesignation(Designation designation);
	
	public List<Designation> getAllDesignations();
	
}
