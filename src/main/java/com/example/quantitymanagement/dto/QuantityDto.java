
/*
 * @author: Developer
 * version: 1.0
 */

package com.example.quantitymanagement.dto;
import com.example.quantitymanagement.model.*;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class QuantityDto {

		private double value;
		@Enumerated(EnumType.STRING)
		private Unit unit;
		private String measurementType;
		public double getValue() {
			return value;
		}
		public QuantityDto() {
			
		}
		public void setValue(double value) {
			this.value = value;
		}
		public Unit getUnit() {
			return unit;
		}
		public QuantityDto(double value, Unit unit, String measurementType) {
			
			this.value = value;
			this.unit = unit;
			this.measurementType = measurementType;
		}
		public void setUnit(Unit unit) {
			this.unit = unit;
		}
		public String getMeasurementType() {
			return measurementType;
		}
		public void setMeasurementType(String measurementType) {
			this.measurementType = measurementType;
		}
		
		
}
