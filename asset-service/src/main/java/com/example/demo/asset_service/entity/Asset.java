package com.example.demo.asset_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_asset")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

	@Id
	@SequenceGenerator(name="asset_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(generator = "asset_seq",strategy = GenerationType.IDENTITY)
	private Integer asset_id;
	
	private String asset_name;
	
	private String asset_type_id;
	
}
