package com.example.demo.asset_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.asset_service.dto.AssetDto;
import com.example.demo.asset_service.dto.ResponseDto;
import com.example.demo.asset_service.entity.Asset;
import com.example.demo.asset_service.entity.AssetType;
import com.example.demo.asset_service.proxy.AssetTypeServiceProxy;
import com.example.demo.asset_service.service.IAssetService;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("asset")
public class AssetController {

	private final IAssetService assetserv;

	private final AssetTypeServiceProxy assetproxy;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveAssetType(@RequestBody Asset asset) {
		Asset assetObj = assetserv.saveAsset(asset);
		if(assetObj!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),"Asset type "+asset.getAsset_name()+" is saved successfully "));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString().toString(),"Asset type "+asset.getAsset_name()+" is not saved "));
		}
	}
	
	@GetMapping("/")
	@Retry(name="getAssetTypeForAssetList" , fallbackMethod = "handleGetAssetTypeForAssetList")
	public ResponseEntity<List<AssetDto>> getAllAssets() {
		List<Asset> assetlist = assetserv.getAllAssets();
		 
		List<AssetDto> assetObj = assetlist.stream().map(asset -> {
			AssetDto assetDtoObj = new AssetDto();
			assetDtoObj.setAsset_id(asset.getAsset_id());
			assetDtoObj.setAsset_name(asset.getAsset_name());
			
			ResponseEntity<AssetType> assetType = assetproxy.getAssettypeById(asset.getAsset_type_id());
			assetDtoObj.setAtype(assetType.getBody());
			
			return assetDtoObj;
		}).collect(Collectors.toList());
		 
		if(assetlist.size()>0) {
			return ResponseEntity.status(HttpStatus.OK).body(assetObj);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
	
	public ResponseEntity<List<AssetDto>> handleGetAssetTypeForAssetList(Exception ex ) {
		List<Asset> assetlist = assetserv.getAllAssets();
		 
		List<AssetDto> assetListObj = assetlist.stream().map(asset -> {
			AssetDto assetDtoObj = new AssetDto();
			assetDtoObj.setAsset_id(asset.getAsset_id());
			assetDtoObj.setAsset_name(asset.getAsset_name());
			
			assetDtoObj.setAtype( new AssetType());
			
			return assetDtoObj;
		}).collect(Collectors.toList());
		 
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(assetListObj);		 
	}
	
	@GetMapping("/{id}")
	@Retry(name="getAssetTypeForSingleAsset" , fallbackMethod = "handleGetAssetTypeForAsset")
	public ResponseEntity<AssetDto> getAssetById(@PathVariable Integer id) {
		Asset asset = assetserv.getAssetById(id);
		ResponseEntity<AssetType> assetType = assetproxy.getAssettypeById(asset.getAsset_type_id());
		AssetDto assetDto = new AssetDto();
		assetDto.setAsset_id(asset.getAsset_id());
		assetDto.setAsset_name(asset.getAsset_name());
		
		assetDto.setAtype(assetType.getBody());
		
		return ResponseEntity.status(HttpStatus.OK).body(assetDto);
	}
	
	public ResponseEntity<AssetDto> handleGetAssetTypeForAsset(Integer id,Exception ex) {
		Asset asset = assetserv.getAssetById(id);
	
		AssetDto assetDto = new AssetDto();
		assetDto.setAsset_id(asset.getAsset_id());
		assetDto.setAsset_name(asset.getAsset_name());
		
		assetDto.setAtype(new AssetType());
		
		return ResponseEntity.status(HttpStatus.OK).body(assetDto);
	}
	
	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateAssetType(@RequestBody Asset asset) {
		int result = assetserv.updateAsset(asset);
		if(result > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),"Asset type "+asset.getAsset_name()+" is updated "));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto(HttpStatus.NOT_MODIFIED.toString().toString(),"Asset type "+asset.getAsset_name()+" is not updated "));
		}
	}
}
