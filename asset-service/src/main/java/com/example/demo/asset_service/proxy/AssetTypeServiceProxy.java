package com.example.demo.asset_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.asset_service.entity.AssetType;

@FeignClient(name = "asset-type-service",url = "localhost:9000")
public interface AssetTypeServiceProxy {
	
	@GetMapping("/assettype/{id}")
	public ResponseEntity<AssetType> getAssettypeById(@PathVariable String id);
	
}
