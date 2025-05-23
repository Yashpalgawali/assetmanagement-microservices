package com.example.demo.employee_service.exceptinos;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.employee_service.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest request ){
	
		ErrorResponseDto error = new ErrorResponseDto(
					request.getDescription(false),
					HttpStatus.NOT_FOUND,
					exception.getMessage(),
					LocalDateTime.now()
				);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(ContentNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleContentNotFoundException(ContentNotFoundException exception,WebRequest request ){
	
		ErrorResponseDto error = new ErrorResponseDto(
					request.getDescription(false),
					HttpStatus.NOT_FOUND,
					exception.getMessage(),
					LocalDateTime.now()
				);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
