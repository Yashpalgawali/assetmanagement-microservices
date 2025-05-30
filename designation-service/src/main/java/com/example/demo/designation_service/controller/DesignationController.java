package com.example.demo.designation_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.designation_service.dto.ResponseDto;
import com.example.demo.designation_service.entity.Designation;
import com.example.demo.designation_service.service.IDesignationService;

@RestController
@RequestMapping("designation")
public class DesignationController {

	private final IDesignationService desigserv;
	
	/**
	 * @param desigserv
	 */
	public DesignationController(IDesignationService desigserv) {
		super();
		this.desigserv = desigserv;
	}


	@GetMapping("/")
	public List<Designation> getAllDesignations() {
		List<Designation> desigList = desigserv.getAllDesignations();
		return desigList;		 
	}
	
	@GetMapping("/{id}")
	public Designation getDesignationById(@PathVariable String id) {
		
		return desigserv.getDesignationById(id);
	}
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveDesignation(@RequestBody Designation designation) {
		
		Designation desigObj = desigserv.saveDesignation(designation);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(HttpStatus.CREATED.toString(), "Designation "+desigObj.getDesignation()+" is saved successfully"));
	}
	
	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateDesignation(@RequestBody Designation designation) {
		int result = desigserv.updateDesignation(designation);
		if(result>0)
		{
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseDto(HttpStatus.CREATED.toString(), "Designation "+designation.getDesignation()+" is updated successfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
					.body(new ResponseDto(HttpStatus.NOT_MODIFIED.toString(), "Designation "+designation.getDesignation()+" is not updated "));
		}
				
	}
	
}
