package com.example.demo.employee_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.employee_service.entity.AssignedAssets;
import com.example.demo.employee_service.entity.Employee;
import com.example.demo.employee_service.exceptinos.ResourceNotFoundException;
import com.example.demo.employee_service.repository.EmployeeRepository;
import com.example.demo.employee_service.service.IEmployeeService;

import lombok.AllArgsConstructor;

@Service("empserv")
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

	private final EmployeeRepository emprepo; 
	
	@Override
	public Employee saveEmployee(Employee emp) {

		String asset_ids = emp.getAsset_ids().toString().replace("[", "").replace("]", "").replace(" ", "");
		String[] asset_arr = asset_ids.split(",");
//		for(int i=0;i<asset_arr.length;i++){
//			
//			AssignedAssets assignasset = new AssignedAssets();
//			int qty =0;
//			Long astid = Long.valueOf(asset_arr[i]);
//			Assets ast = new Assets();
//			Assets getasset = assetserv.getAssetsById(astid);
//			
//			AssetType atype = new AssetType();
//			atype = atypeserv.getAssetTypeById(getasset.getAtype().getType_id());
//			
//			ast.setAtype(atype);
//			
//			ast.setAsset_id(astid);
//			ast.setAsset_name(getasset.getAsset_name());
//			ast.setAsset_number(getasset.getAsset_number());
//			ast.setModel_number(getasset.getModel_number());
//			ast.setQuantity(getasset.getQuantity());
//			
//			assignasset.setEmployee(emp);
//			assignasset.setAsset(ast);
//		
//			assignasset.setAssign_date(ddate.format(LocalDateTime.now()));
//			assignasset.setAssign_time(dtime.format(LocalDateTime.now()));
//			
//			isassigned = assignserv.saveAssignedAssets(assignasset);
//			
//			if(isassigned!=null) {	
//				qty = (Integer)assetserv.getAssetQuantityByAssetId(astid);
//				qty-=1;
//				assetserv.updateAssetQuantityByAssetId(astid, ""+qty);
//				AssetAssignHistory ahist = new AssetAssignHistory();
//			
//				ahist.setAsset(ast);
//				ahist.setEmployee(emp);
//				ahist.setOperation_date(ddate.format(LocalDateTime.now()));
//				ahist.setOperation_time(dtime.format(LocalDateTime.now()));
//				ahist.setOperation("Asset Assigned");
//				
//				ahistserv.saveAssetAssignHistory(ahist);
//			}
//		}
		return emprepo.save(emp);
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		 
		return emprepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No employee found for given ID "+id));
	}

	@Override
	public int updateEmployee(Employee emp) {
		Employee employee = emprepo.save(emp);
		return employee!=null ? 1 : 0;
	}

	@Override
	public List<Employee> getAllEmployees() {
	 
		return emprepo.findAll();
	}
		
}
