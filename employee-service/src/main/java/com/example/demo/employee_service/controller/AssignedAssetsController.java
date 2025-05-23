package com.example.demo.employee_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.employee_service.entity.AssignedAssets;
import com.example.demo.employee_service.service.AssignedAssetService;

@RestController
@RequestMapping("assigned_assets")
public class AssignedAssetsController {

	private final AssignedAssetService assignedassetserv;

	public AssignedAssetsController(AssignedAssetService assignedassetserv) {
		super();
		this.assignedassetserv = assignedassetserv;
	} 

	@GetMapping("/{empid}")
	public ResponseEntity<List<AssignedAssets>> getAllAssignedAssetsByEmpId(@PathVariable Integer empid) {
		List<AssignedAssets> list = assignedassetserv.getAllAssignedAssetsByEmpId(empid);
		list.stream().forEach(System.out::println);
		return null;
	}

	
	@GetMapping("/viewassignedassets")
	public ResponseEntity<List<AssignedAssets>> getAllAssignedAssets() {
		
		return null;
	}
	
}
