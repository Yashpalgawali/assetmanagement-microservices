package com.example.demo.employee_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

	private Integer asset_id;
	
	private String asset_name;
	
	private String asset_type_id;
	
}
