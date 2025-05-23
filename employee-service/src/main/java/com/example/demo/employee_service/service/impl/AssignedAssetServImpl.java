package com.example.demo.employee_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.employee_service.entity.AssignedAssets;
import com.example.demo.employee_service.exceptinos.ContentNotFoundException;
import com.example.demo.employee_service.proxy.AssetServiceProxy;
import com.example.demo.employee_service.repository.AssignedAssetsRepository;
import com.example.demo.employee_service.service.AssignedAssetService;

@Service("assignedassetserv")
public class AssignedAssetServImpl implements AssignedAssetService {

	private final AssignedAssetsRepository assignedassetrepo;

	private final AssetServiceProxy assetproxy;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public AssignedAssetServImpl(AssignedAssetsRepository assignedassetrepo, AssetServiceProxy assetproxy) {
		super();
		this.assignedassetrepo = assignedassetrepo;
		this.assetproxy = assetproxy;
	}

	@Override
	public List<AssignedAssets> getAllAssignedAssetsByEmpId(Integer empid) {
		List<AssignedAssets> assetList = assignedassetrepo.findAssignedAssetsByEmpId(empid);
		if (assetList.size() > 0) {
			return assetList;
		} else {
			throw new ContentNotFoundException("No assets are assigned to the employee");
		}
	}

	@Override
	public List<AssignedAssets> getAllAssignedAssets() {

		List<AssignedAssets> allAssignedAssets = assignedassetrepo.getAllAssignedAssets();

		logger.info("Assigned assets are {} ",allAssignedAssets);
//		allAssignedAssets.stream().map(asset->{
//			
//		}).collect(Collectors.toList());
		return null;
	}

}
