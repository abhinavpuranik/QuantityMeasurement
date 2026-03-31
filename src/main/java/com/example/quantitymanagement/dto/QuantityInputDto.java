/*
 * @author: Developer
 * version: 1.0
 */

package com.example.quantitymanagement.dto;

public class QuantityInputDto {

	private String thisQuantity;
	private String thatQuantity;
	
	public QuantityInputDto(String thisQuantity, String thatQuantity) {
		
		this.thisQuantity = thisQuantity;
		this.thatQuantity = thatQuantity;
	}
	public QuantityInputDto() {
	}
	public String getThisQuantity() {
		return thisQuantity;
	}
	public void setThisQuantity(String thisQuantity) {
		this.thisQuantity = thisQuantity;
	}
	public String getThatQuantity() {
		return thatQuantity;
	}
	public void setThatQuantity(String thatQuantity) {
		this.thatQuantity = thatQuantity;
	}
	
}
