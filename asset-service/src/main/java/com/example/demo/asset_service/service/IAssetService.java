package com.example.demo.asset_service.service;

import java.util.List;

import com.example.demo.asset_service.entity.Asset;

public interface IAssetService {

	public Asset saveAsset(Asset asset);
	
	public List<Asset> getAllAssets();
	
	public int updateAsset(Asset asset);
	
	public Asset getAssetById(Integer id);
	
	public int increaseAssetQuantity(Integer assetid);
	
	public int decreaseAssetQuantity(Integer assetid);
}
