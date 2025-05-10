package com.example.demo.asset_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.asset_service.entity.Asset;

@Repository("assetrepo")
public interface AssetRepository extends JpaRepository<Asset, Integer> {

}
