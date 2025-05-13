package com.example.demo.asset_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetType {
	private String asset_type_id;
	
	private String asset_type;
}
