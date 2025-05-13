package com.example.demo.designation_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "designation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Designation {

	@Id
	private String designation_id;
	
	private String designation;
}
