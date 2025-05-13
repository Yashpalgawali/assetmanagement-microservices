package com.example.demo.designation_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.designation_service.entity.Designation;
import com.example.demo.designation_service.exception.ResourceNotFoundException;
import com.example.demo.designation_service.repository.DesignationRepository;
import com.example.demo.designation_service.service.IDesignationService;

@Service("desigserv")
public class DesignationServiceImpl implements IDesignationService {

	private final DesignationRepository desigrepo; 
	
	/**
	 * @param desigrepo
	 */
	public DesignationServiceImpl(DesignationRepository desigrepo) {
		super();
		this.desigrepo = desigrepo;
	}

	@Override
	public Designation saveDesignation(Designation designation) {
		 
		return desigrepo.save(designation);
	}

	@Override
	public List<Designation> getAllDesignations() {
		 
		List<Designation> desigList = desigrepo.findAll();
		if(desigList.size()>0) {
			return desigList;
		}	
		else {
			throw new ResourceNotFoundException("No Items found");
		}
		
	}

	@Override
	public Designation getDesignationById(String id) {
		 
		return desigrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Designation found for given ID "+id));
	}

	@Override
	public int updateDesignation(Designation designation) {
		Designation desig = desigrepo.save(designation);
		return desig!=null ? 1 : 0;
	}

}
