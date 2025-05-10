package com.example.demo.employee_service.dto;

import com.example.demo.employee_service.entity.AssetType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AssetDto {
	private Integer asset_id;

	private String asset_name;

	private AssetType atype;
}
