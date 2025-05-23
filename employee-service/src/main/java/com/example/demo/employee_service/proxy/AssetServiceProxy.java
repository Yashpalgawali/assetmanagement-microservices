package com.example.demo.employee_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.employee_service.dto.AssetDto;
import com.example.demo.employee_service.dto.ResponseDto;

@FeignClient(name =  "asset-service")
public interface AssetServiceProxy {

	@GetMapping("/asset/{id}")
	public ResponseEntity<AssetDto> getAssetById(@PathVariable Integer id);
	
	@PutMapping("/update/{assetid}/increase")
	public ResponseEntity<ResponseDto> increaseAssetQuantity(@PathVariable Integer assetid);
	
	@PutMapping("/update/{assetid}/decrease")
	public ResponseEntity<ResponseDto> decreaseAssetQuantity(@PathVariable Integer assetid);
}
