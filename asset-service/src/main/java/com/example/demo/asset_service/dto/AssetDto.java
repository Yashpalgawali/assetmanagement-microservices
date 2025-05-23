package com.example.demo.asset_service.dto;

import com.example.demo.asset_service.entity.AssetType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDto {

	private Integer asset_id;
	private String asset_name;
	private int qty ;
	private AssetType atype;
}
