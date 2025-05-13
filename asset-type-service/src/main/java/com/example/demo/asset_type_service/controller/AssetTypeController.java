package com.example.demo.asset_type_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.asset_type_service.dto.ResponseDto;
import com.example.demo.asset_type_service.entity.AssetType;
import com.example.demo.asset_type_service.service.IAssetTypeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("assettype")
@AllArgsConstructor
public class AssetTypeController {

	private final IAssetTypeService assetypeserv;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveAssetType(@RequestBody AssetType atype) {
		AssetType assetType = assetypeserv.saveAssetType(atype);
		if(assetType!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString(),"Asset type "+atype.getAsset_type()+" is saved successfully "));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString().toString(),"Asset type "+atype.getAsset_type()+" is not saved "));
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<AssetType>> getAllAssetTypes() {
		 List<AssetType> atypelist = assetypeserv.getAllAssetTypes();
		if(atypelist.size()>0) {
			return ResponseEntity.status(HttpStatus.OK).body(atypelist);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AssetType> getAssettypeById(@PathVariable String id) {
		AssetType assettype = assetypeserv.getAssetTypeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(assettype);
	}
	
	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateAssetType(@RequestBody AssetType atype) {
		int result = assetypeserv.updateAssetType(atype);
		if(result > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),"Asset type "+atype.getAsset_type()+" is saved updated "));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto(HttpStatus.NOT_MODIFIED.toString().toString(),"Asset type "+atype.getAsset_type()+" is not updated "));
		}
	}
}
