package com.example.demo.asset_type_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "assettype")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetType {

	@Id
	private String asset_type_id;

	private String asset_type;
}
