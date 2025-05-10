package com.example.demo.asset_service.mapper;

import com.example.demo.asset_service.dto.AssetDto;
import com.example.demo.asset_service.entity.Asset;

public class AssetMapper {

	private static Asset mapToAsset(AssetDto assetdto , Asset asset) {
			
			asset.setAsset_id(assetdto.getAsset_id());
			asset.setAsset_name(assetdto.getAsset_name());
			asset.setAsset_type_id(assetdto.getAtype().getAsset_type_id());
			
			return asset;
		} 

	private static AssetDto mapToAssetDto(Asset asset , AssetDto assetdto) {
		
		assetdto.setAsset_id(asset.getAsset_id());
		assetdto.setAsset_name(asset.getAsset_name());
		
		
		return assetdto;
	} 
}
