/*
 * @author: Developer
 * version: 1.0
 */

package com.example.quantitymanagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="quantity_measurement_entity", indexes= {
		@Index(name = "idx_operation", columnList="operation"),
		@Index(name = "idx_measurement_app", columnList="this_measurement_type"),
		@Index(name = "idx_created_at", columnList="created_at")
		
		
})
@Data
@NoArgsConstructor
@AllArgsConstructor

public class QuantityMeasurementEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="this_value", nullable=false)
	private double thisValue;
	@Column(name = "this_unit", nullable = false)
	public String thisUnit;

	@Column(name = "this_measurement_type", nullable = false)
	public String thisMeasurementType;

	@Column(name = "that_value")
	public double thatValue;

	@Column(name = "that_unit")
	public String thatUnit;

	@Column(name = "that_measurement_type")
	public String thatMeasurementType;

	// e.g., "COMPARE", "CONVERT", "ADD", "SUBTRACT", "DEVIDE"
	@Column(name = "operation", nullable = false)
	public String operation;
	
	@Column(name = "result_value")
    public double resultValue;

    @Column(name = "result_unit")
    public String resultUnit;

    @Column(name = "result_measurement_type")
    public String resultMeasurementType;

    // For comparison results like "Equal" or "Not Equal"
    @Column(name = "result_string")
    public String resultString;

    // Flag to indicate if an error occurred during the operation
    @Column(name = "is_error")
    public boolean isError;

    // For capturing any error messages during operations
    @Column(name = "error_message")
    public String errorMessage;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
    	createdAt = LocalDateTime.now();
    	updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public QuantityMeasurementEntity(double thisQuantity, String thisUnit, String thisMeasurementType,
            String operation,
            double resultValue, String resultUnit, String resultMeasurementType) {

    	this.thisValue = thisQuantity;
    	this.thisUnit = thisUnit;
    	this.thisMeasurementType = thisMeasurementType;

    	this.operation = operation;

    	this.resultValue = resultValue;
    	this.resultUnit = resultUnit;
    	this.resultMeasurementType = resultMeasurementType;

    	this.isError = false;
}
    
    public QuantityMeasurementEntity(double thisQuantity, String thisUnit, String thisMeasurementType,
            double thatQuantity, String thatUnit, String thatMeasurementType,
            String operation,
            double resultValue, String resultUnit, String resultMeasurementType) {

    	this.thisValue = thisQuantity;
    	this.thisUnit = thisUnit;
    	this.thisMeasurementType = thisMeasurementType;
    	
    	this.thatValue = thatQuantity;
    	this.thatUnit = thatUnit;
    	this.thatMeasurementType = thatMeasurementType;

    	this.operation = operation;

    	this.resultValue = resultValue;
    	this.resultUnit = resultUnit;
    	this.resultMeasurementType = resultMeasurementType;

    	this.isError = false;
}
    
    public QuantityMeasurementEntity(double thisQuantity, String thisUnit, String thisMeasurementType,
            double thatQuantity, String thatUnit, String thatMeasurementType,
            String operation,
            String errorMessage, boolean isError) {

    	this.thisValue = thisQuantity;
    	this.thisUnit = thisUnit;
    	this.thisMeasurementType = thisMeasurementType;

    	this.thatValue = thatQuantity;
    	this.thatUnit = thatUnit;
    	this.thatMeasurementType = thatMeasurementType;

    	this.operation = operation;

    	this.errorMessage = errorMessage;
    	this.isError = isError;
}
  
    
	
	
	
	
}
