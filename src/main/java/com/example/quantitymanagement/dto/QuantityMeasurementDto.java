
/*
 * @author: Developer
 * version: 1.0
 */
package com.example.quantitymanagement.dto;

public class QuantityMeasurementDto {

	private String thisMeasurmentType;
	private String thatMeasurementType;
	public String getThisMeasurmentType() {
		return thisMeasurmentType;
	}
	public void setThisMeasurmentType(String thisMeasurmentType) {
		this.thisMeasurmentType = thisMeasurmentType;
	}
	public String getThatMeasurementType() {
		return thatMeasurementType;
	}
	public void setThatMeasurementType(String thatMeasurementType) {
		this.thatMeasurementType = thatMeasurementType;
	}
	public String getThatValue() {
		return thatValue;
	}
	public void setThatValue(String thatValue) {
		this.thatValue = thatValue;
	}
	public double getThisValue() {
		return thisValue;
	}
	public void setThisValue(double thisValue) {
		this.thisValue = thisValue;
	}
	public String getThisUnit() {
		return thisUnit;
	}
	public void setThisUnit(String thisUnit) {
		this.thisUnit = thisUnit;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	public double getResultValue() {
		return resultValue;
	}
	public void setResultValue(double resultValue) {
		this.resultValue = resultValue;
	}
	public String getResultUnit() {
		return resultUnit;
	}
	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}
	public String getResultMeasurementType() {
		return resultMeasurementType;
	}
	public void setResultMeasurementType(String resultMeasurementType) {
		this.resultMeasurementType = resultMeasurementType;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private String thatValue;
	private double thisValue;
	private String thisUnit;
	
	public QuantityMeasurementDto() {
		
	}
	public QuantityMeasurementDto(double thisValue, String thisUnit, String thisMeasurement, String thatUnit, String thatMeasurementType, String operation, String resultValue, String resultUnit, String resultMeasurementType, String errorMessage, boolean error) 
	{
		this.thisValue = thisValue;
		this.thatMeasurementType  = thatMeasurementType;
		this.thatValue = thatValue;
		this.thisUnit = thisUnit;
		this.operation = operation;
		this.resultMeasurementType = resultMeasurementType;
		this.resultString = resultString;
		this.resultUnit = resultUnit;
		this.error = error;
		this.errorMessage = errorMessage;
		
		
	}
	
	private String operation;
	private String resultString;

	private double resultValue;
	private String resultUnit;
	private String resultMeasurementType;
	
	private boolean error;
	private String errorMessage;
}
