/*
 * @author: Developer
 * version: 1.0
 */

package com.example.quantitymanagement.repository;

import java.time.LocalDateTime;
import java.util.List;
import com.example.quantitymanagement.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
	List<QuantityMeasurementEntity> findByOperation(String operation);
	List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);
	
	List<QuantityMeasurementEntity> findByCreatedAtAfter(LocalDateTime date);
	
	@Query("SELECT e from QuantityMeasurementEntity e WHERE e.operation = :operation " + "AND e.isError = false")
	List<QuantityMeasurementEntity> findSuccessfulOperations(
			@Param("operation") String operation
			);
	
	long countByOperationAndIsErrorFalse(String operation);
	
	List<QuantityMeasurementEntity> findByIsErrorTrue();
	
	
	



}


