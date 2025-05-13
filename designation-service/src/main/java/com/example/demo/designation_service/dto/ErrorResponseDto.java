package com.example.demo.designation_service.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDto {

	public String apiPath;
	
	public HttpStatus errorStatus;
	
	public String errorMessage;
	
	public LocalDateTime errortTime;
}
