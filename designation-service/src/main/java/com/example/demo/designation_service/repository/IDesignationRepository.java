package com.example.demo.designation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.designation_service.entity.Designation;

@Repository("desigrepo")
public interface IDesignationRepository extends JpaRepository<Designation, Integer> {

}
