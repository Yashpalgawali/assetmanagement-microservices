package com.example.demo.asset_type_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.asset_type_service.entity.AssetType;

@Repository("assettyperepo")
public interface AssetTypeRepository extends JpaRepository<AssetType, Integer> {

}
