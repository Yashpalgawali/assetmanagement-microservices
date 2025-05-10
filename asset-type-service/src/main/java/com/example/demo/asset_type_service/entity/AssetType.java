package com.example.demo.asset_type_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_assettype")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetType {

	@Id
	@SequenceGenerator(name = "assetype_seq",allocationSize = 1,initialValue = 1)
	@GeneratedValue(generator = "assetype_seq",strategy = GenerationType.IDENTITY)
	private Integer asset_type_id;
	
	private String asset_type;
}
