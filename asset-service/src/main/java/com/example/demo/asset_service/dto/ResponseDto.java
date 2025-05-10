package com.example.demo.asset_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResponseDto {

	private String statusCode;
	
	private String responseMessage;
}
