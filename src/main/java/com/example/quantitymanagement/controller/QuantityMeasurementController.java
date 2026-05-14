/*
 * @author: Developer
 * version: 1.0
 */

package com.example.quantitymanagement.controller;

import com.example.quantitymanagement.dto.QuantityDto;
import com.example.quantitymanagement.dto.QuantityMeasurementDto;
import com.example.quantitymanagement.repository.QuantityMeasurementRepository;
import com.example.quantitymanagement.service.IQuantityMeasurementService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.quantitymanagement.model.QuantityMeasurementEntity;

@RestController
@RequestMapping("/quantity")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;
    @Autowired
    private QuantityMeasurementRepository repository;

    @PostMapping("/convert/{targetUnit}")
    public QuantityMeasurementDto convert(
            @RequestBody QuantityDto input,
            @PathVariable String targetUnit) {
        return service.convert(input, targetUnit);
    }

    @PostMapping("/compare")
    public QuantityMeasurementDto compare(
            @RequestBody QuantityDto[] inputs) {
        return service.compare(inputs[0], inputs[1]);
    }

    @PostMapping("/add")
    public QuantityMeasurementDto add(
            @RequestBody QuantityDto[] inputs) {
        return service.add(inputs[0], inputs[1]);
    }

    @PostMapping("/subtract")
    public QuantityMeasurementDto subtract(
            @RequestBody QuantityDto[] inputs) {
        return service.subtract(inputs[0], inputs[1]);
    }

    @PostMapping("/divide")
    public QuantityMeasurementDto divide(
            @RequestBody QuantityDto[] inputs) {
        return service.divide(inputs[0], inputs[1]);
    }
    
    @GetMapping("/history")
    public List<QuantityMeasurementEntity> getHistory() 
    {
    	return repository.findAll();
    }

    @GetMapping("/success")
    public String success(){
        return "successful";
        
    }
    
} 
