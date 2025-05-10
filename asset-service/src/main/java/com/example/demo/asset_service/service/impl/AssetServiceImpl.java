package com.example.demo.asset_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.asset_service.entity.Asset;
import com.example.demo.asset_service.exceptions.ResourceNotFoundException;
import com.example.demo.asset_service.repository.AssetRepository;
import com.example.demo.asset_service.service.IAssetService;

import lombok.AllArgsConstructor;

@Service("assetserv")
@AllArgsConstructor
public class AssetServiceImpl implements IAssetService {

	private final AssetRepository assetrepo;
	
	@Override
	public Asset saveAsset(Asset asset) {

		return assetrepo.save(asset);
	}

	@Override
	public List<Asset> getAllAssets() {
		 
		return assetrepo.findAll();
	}

	@Override
	public int updateAsset(Asset asset) {
		Asset assetObj = assetrepo.save(asset);
		return assetObj!=null ? 1 :  0;
	}

	@Override
	public Asset getAssetById(Integer id) {
		 
		return assetrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Asset not found for given Id"+id));
	}

}
