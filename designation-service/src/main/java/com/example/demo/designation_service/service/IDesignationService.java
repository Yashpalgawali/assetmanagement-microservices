package com.example.demo.designation_service.service;

import java.util.List;

import com.example.demo.designation_service.entity.Designation;

public interface IDesignationService {

	public Designation saveDesignation(Designation designation);
	
	public List<Designation> getAllDesignations();
	
	public Designation getDesignationById(String id);
	
	public int updateDesignation(Designation designation);
	
}
