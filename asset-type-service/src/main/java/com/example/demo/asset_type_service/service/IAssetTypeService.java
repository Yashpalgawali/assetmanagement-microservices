package com.example.demo.asset_type_service.service;

import java.util.List;

import com.example.demo.asset_type_service.entity.AssetType;

public interface IAssetTypeService {

	public AssetType saveAssetType(AssetType atype);
	
	public AssetType getAssetTypeById(String id);
	
	public int updateAssetType(AssetType atype);
	
	public List<AssetType> getAllAssetTypes();
}
