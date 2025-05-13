package com.example.demo.designation_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.designation_service.entity.Designation;

@Repository("desigrepo")
public interface DesignationRepository extends MongoRepository<Designation, String> {

}
