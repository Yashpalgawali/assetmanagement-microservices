package com.example.demo.employee_service.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.employee_service.dto.ResponseDto;
import com.example.demo.employee_service.entity.AssignedAssets;
import com.example.demo.employee_service.entity.Employee;
import com.example.demo.employee_service.exceptinos.ResourceNotFoundException;
import com.example.demo.employee_service.proxy.AssetServiceProxy;
import com.example.demo.employee_service.repository.AssignedAssetsRepository;
import com.example.demo.employee_service.repository.EmployeeRepository;
import com.example.demo.employee_service.service.IEmployeeService;

import lombok.AllArgsConstructor;

@Service("empserv")

public class EmployeeServiceImpl implements IEmployeeService {

	private final EmployeeRepository emprepo;

	private final AssignedAssetsRepository assignedassetrepo;
	
	private final AssetServiceProxy assetproxy;
	
	DateTimeFormatter tday = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter ttime = DateTimeFormatter.ofPattern("hh:mm:ss");
	
	
	public EmployeeServiceImpl(EmployeeRepository emprepo, AssignedAssetsRepository assignedassetrepo,
			AssetServiceProxy assetproxy) {
		super();
		this.emprepo = emprepo;
		this.assignedassetrepo = assignedassetrepo;
		this.assetproxy = assetproxy;
	}

	@Override
	public Employee saveEmployee(Employee emp) {

		Employee savedEmployee = emprepo.save(emp);

		if(savedEmployee!=null) {
			String asset_ids = emp.getAsset_ids().toString().replace("[", "").replace("]", "").replace(" ", "");
			String[] asset_arr = asset_ids.split(",");
			for(int i=0;i<asset_arr.length;i++) {
				 
				AssignedAssets assign = new AssignedAssets();
				
				int ast_id = Integer.parseInt(asset_arr[i]);
				
				assign.setEmployee(savedEmployee);
				assign.setAsset_id(ast_id);
				assign.setAssign_date(tday.format(LocalDateTime.now()));
				assign.setAssign_time(ttime.format(LocalDateTime.now()));
				if(assign!=null) {
					ResponseEntity<ResponseDto> qtyResult = assetproxy.decreaseAssetQuantity(ast_id);
					HttpStatusCode res = qtyResult.getStatusCode();
					if(res.is2xxSuccessful()) {
						
					}else {
						
					}
				}
			}
			
			return savedEmployee;
		}
		else {
			return null;
		}
	}

	@Override
	public Employee getEmployeeById(Integer id) {

		return emprepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No employee found for given ID " + id));
	}

	@Override
	public int updateEmployee(Employee emp) {
		Employee employee = emprepo.save(emp);
		return employee != null ? 1 : 0;
	}

	@Override
	public List<Employee> getAllEmployees() {

		return emprepo.findAll();
	}

}
