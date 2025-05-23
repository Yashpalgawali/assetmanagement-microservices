package com.example.demo.asset_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.asset_service.entity.Asset;

@Repository("assetrepo")
public interface AssetRepository extends JpaRepository<Asset, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE  Asset a SET a.qty=:qty WHERE a.asset_id=:assetid")
	public int updateAssetQuantity(Integer assetid, int qty);
	
	@Query("SELECT a.qty from Asset a WHERE a.asset_id=:id")
	public int getAssetQuantityByAssetId(Integer id);
	
}
