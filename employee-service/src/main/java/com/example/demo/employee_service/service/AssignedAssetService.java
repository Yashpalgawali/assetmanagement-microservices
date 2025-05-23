package com.example.demo.employee_service.service;

import java.util.List;

import com.example.demo.employee_service.entity.AssignedAssets;

public interface AssignedAssetService {

	public List<AssignedAssets> getAllAssignedAssetsByEmpId(Integer empid);
	
	public List<AssignedAssets> getAllAssignedAssets();
}
