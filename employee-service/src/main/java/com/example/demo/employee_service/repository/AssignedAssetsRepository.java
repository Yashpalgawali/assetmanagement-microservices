package com.example.demo.employee_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.employee_service.entity.AssignedAssets;
import java.util.List;

@Repository("assignedassetrepo")
public interface AssignedAssetsRepository extends JpaRepository<AssignedAssets, Integer> {

//	public List<AssignedAssets> findByEmp_id(Integer emp_id);
	
	@Query("SELECT a FROM AssignedAssets a JOIN a.employee WHERE a.employee.emp_id=:emp_id")
	public List<AssignedAssets> findAssignedAssetsByEmpId(Integer emp_id);
	
	@Query("SELECT a FROM AssignedAssets a JOIN a.employee")
	public List<AssignedAssets> getAllAssignedAssets();
	
	
//	@Query(value = "SELECT tas.*,GROUP_CONCAT(ta.asset_name),GROUP_CONCAT(at.type_name),
//		te.emp_name,te.emp_email,te.emp_contact,te.desig_id,des.desig_name,td.dept_id,td.dept_name,td.comp_id,tc.comp_name,
//		GROUP_CONCAT(ta.model_number) 
//	 	FROM tbl_assigned_assets tas JOIN tbl_assets ta ON ta.asset_id=tas.asset_id 
//		JOIN tbl_assettype AS at ON at.type_id=ta.type_id JOIN tbl_employee AS te ON te.emp_id=tas.emp_id 
//		JOIN tbl_designation AS des ON des.desig_id=te.desig_id 
//		JOIN tbl_department AS td ON td.dept_id=te.dept_id 
//		JOIN tbl_company AS tc ON tc.comp_id=td.comp_id GROUP BY te.emp_id", nativeQuery = true)
//	public List<Object[]> getAllNewAssignedAssets();
}
 