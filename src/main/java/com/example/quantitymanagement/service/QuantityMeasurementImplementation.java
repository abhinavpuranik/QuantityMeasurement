
/*
 * @author: Developer
 * version: 1.0
 */
package com.example.quantitymanagement.service;
import com.example.quantitymanagement.dto.QuantityDto;
import com.example.quantitymanagement.dto.QuantityMeasurementDto;
import com.example.quantitymanagement.model.Unit;
import com.example.quantitymanagement.model.MeasurementType;
import com.example.quantitymanagement.repository.QuantityMeasurementRepository;
import com.example.quantitymanagement.model.QuantityMeasurementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


		

@Service

public class QuantityMeasurementImplementation implements IQuantityMeasurementService{

	public QuantityMeasurementImplementation(QuantityMeasurementRepository repo) {
	    this.repository = repo;
	}
	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementImplementation.class);
	@Autowired
	private QuantityMeasurementRepository repository;
	
	@Override
	public QuantityMeasurementDto convert(QuantityDto input, String targetUnit) {
		try {
            Unit fromUnit = input.getUnit();
            Unit toUnit = Unit.valueOf(targetUnit.toUpperCase());

            // must be same measurement type
            if (fromUnit.getType() != toUnit.getType()) {
                return errorResult(input, targetUnit, "CONVERT",
                        "Cannot convert between different measurement types");
            }

            double result = convertValue(input.getValue(), fromUnit, toUnit);

            // save to DB
            repository.save(new QuantityMeasurementEntity(
                    input.getValue(), fromUnit.name(), fromUnit.getType().name(),
                    "CONVERT",
                    result, toUnit.name(), toUnit.getType().name()
            ));
            logger.info("Successfully saved data");

            return successResult(input.getValue(), fromUnit.name(),
                    fromUnit.getType().name(), result,
                    toUnit.name(), toUnit.getType().name(), "CONVERT");

        } catch (IllegalArgumentException e) {
            return errorResult(input, targetUnit, "CONVERT", "Unknown unit: " + targetUnit);
        }
	}

	@Override
	public QuantityMeasurementDto compare(QuantityDto first, QuantityDto second) {
	    try {
            validateSameType(first, second);

            double firstInBase = toBase(first);
            double secondInBase = toBase(second);

            String comparisonResult = (Math.abs(firstInBase - secondInBase) < 0.0001)
                    ? "Equal" : "Not Equal";

            repository.save(new QuantityMeasurementEntity(
                    first.getValue(), first.getUnit().name(), first.getUnit().getType().name(),
                    second.getValue(), second.getUnit().name(), second.getUnit().getType().name(),
                    "COMPARE",
                    0, comparisonResult, first.getUnit().getType().name()
            ));
            logger.info("Successfully saved data");

            QuantityMeasurementDto dto = new QuantityMeasurementDto();
            dto.setResultString(comparisonResult);
            dto.setOperation("COMPARE");
            dto.setError(false);
            return dto;

        } catch (IllegalArgumentException e) {
            return errorResult(first, second, "COMPARE", e.getMessage());
        }
    }
	

	@Override
	public QuantityMeasurementDto add(QuantityDto first, QuantityDto second) {
		try {
            validateSameType(first, second);
            double result = toBase(first) + toBase(second);
            Unit baseUnit = getBaseUnit(first.getUnit().getType());
            saveAndReturn(first, second, "ADD", result, baseUnit);
            logger.info("Successfully saved data");
            return successResult(first, second, "ADD", result, baseUnit);
        } catch (IllegalArgumentException e) {
            return errorResult(first, second, "ADD", e.getMessage());
        }
    }
	

	@Override
	public QuantityMeasurementDto subtract(QuantityDto first, QuantityDto second) {
		try {
            validateSameType(first, second);
            double result = toBase(first) - toBase(second);
            Unit baseUnit = getBaseUnit(first.getUnit().getType());
            saveAndReturn(first, second, "SUBTRACT", result, baseUnit);
            logger.info("Successfully saved data");
            return successResult(first, second, "SUBTRACT", result, baseUnit);
        } catch (IllegalArgumentException e) {
            return errorResult(first, second, "SUBTRACT", e.getMessage());
        }
	}

	@Override
	public QuantityMeasurementDto divide(QuantityDto first, QuantityDto second) {
		try {
            validateSameType(first, second);
            if (toBase(second) == 0) throw new IllegalArgumentException("Cannot divide by zero");
            double result = toBase(first) / toBase(second);
            Unit baseUnit = getBaseUnit(first.getUnit().getType());
            saveAndReturn(first, second, "DIVIDE", result, baseUnit);
            logger.info("Successfully saved data");
            return successResult(first, second, "DIVIDE", result, baseUnit);
        } catch (IllegalArgumentException e) {
            return errorResult(first, second, "DIVIDE", e.getMessage());
        }
	}
	
	private QuantityMeasurementDto successResult(double thisValue, String thisUnit,
            String thisType, double resultValue, String resultUnit,
            String resultType, String operation) {
        QuantityMeasurementDto dto = new QuantityMeasurementDto();
        dto.setThisValue(thisValue);
        dto.setThisUnit(thisUnit);
        dto.setThisMeasurmentType(thisType);
        dto.setResultValue(resultValue);
        dto.setResultUnit(resultUnit);
        dto.setResultMeasurementType(resultType);
        dto.setOperation(operation);
        dto.setError(false);
        return dto;
    }

    private QuantityMeasurementDto successResult(QuantityDto first, QuantityDto second,
            String operation, double result, Unit baseUnit) {
        QuantityMeasurementDto dto = new QuantityMeasurementDto();
        dto.setThisValue(first.getValue());
        dto.setThisUnit(first.getUnit().name());
        dto.setThisMeasurmentType(first.getUnit().getType().name());
        dto.setThatValue(String.valueOf(second.getValue()));
        dto.setThatMeasurementType(second.getUnit().getType().name());
        dto.setResultValue(result);
        dto.setResultUnit(baseUnit.name());
        dto.setResultMeasurementType(baseUnit.getType().name());
        dto.setOperation(operation);
        dto.setError(false);
        return dto;
    }

    private QuantityMeasurementDto errorResult(QuantityDto input, String targetUnit,
            String operation, String message) {
        QuantityMeasurementDto dto = new QuantityMeasurementDto();
        dto.setOperation(operation);
        dto.setError(true);
        dto.setErrorMessage(message);
        return dto;
    }

    private QuantityMeasurementDto errorResult(QuantityDto first, QuantityDto second,
            String operation, String message) {
        QuantityMeasurementDto dto = new QuantityMeasurementDto();
        dto.setOperation(operation);
        dto.setError(true);
        dto.setErrorMessage(message);
        return dto;
    }
    

    private double toBase(QuantityDto dto) {
        return dto.getUnit().toBase(dto.getValue());
    }

    private double convertValue(double value, Unit from, Unit to) {
        double baseValue = from.toBase(value);   // Step 1
        return to.fromBase(baseValue);           // Step 2 ✅
    }

    private void validateSameType(QuantityDto first, QuantityDto second) {
        if (first.getUnit().getType() != second.getUnit().getType()) {
            throw new IllegalArgumentException(
                "Mismatched types: " + first.getUnit().getType() + " vs " + second.getUnit().getType()
            );
        }
    }

    // returns the base unit for a given measurement type (e.g. LENGTH -> METER)
    private Unit getBaseUnit(MeasurementType type) {
        for (Unit u : Unit.values()) {
            if (u.getType() == type && u.toBase(1.0) == 1.0) return u;
        }
        throw new IllegalArgumentException("No base unit for type: " + type);
    }

    private void saveAndReturn(QuantityDto first, QuantityDto second,
                                String operation, double result, Unit baseUnit) {
        repository.save(new QuantityMeasurementEntity(
                first.getValue(), first.getUnit().name(), first.getUnit().getType().name(),
                second.getValue(), second.getUnit().name(), second.getUnit().getType().name(),
                operation,
                result, baseUnit.name(), baseUnit.getType().name()
        ));
    }

	
	
	
}
