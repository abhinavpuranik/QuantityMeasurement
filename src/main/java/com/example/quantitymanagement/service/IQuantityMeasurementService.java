/*
 * @author: Developer
 * version: 1.0
 */

package com.example.quantitymanagement.service;

import com.example.quantitymanagement.dto.QuantityDto;
import com.example.quantitymanagement.dto.QuantityMeasurementDto;

public interface IQuantityMeasurementService {

	QuantityMeasurementDto convert(QuantityDto input, String targetUnit);
	QuantityMeasurementDto compare(QuantityDto first, QuantityDto second);
	QuantityMeasurementDto add(QuantityDto first, QuantityDto second);
	QuantityMeasurementDto subtract(QuantityDto first, QuantityDto second);
	QuantityMeasurementDto divide(QuantityDto first, QuantityDto second);
	
}
