package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TravelPackage;
import com.example.demo.service.TravelPackageService;

@RestController
@RequestMapping("/travel-packages/{travelPackageId}")
public class TravelPackageController {
	private TravelPackageService travelPackageService;

	public TravelPackageController(TravelPackageService travelPackageService) {
		super();
		this.travelPackageService = travelPackageService;
	}
	
	@GetMapping
	public TravelPackage retrieveByTravelPackageId(@PathVariable int travelPackageId) {
		return travelPackageService.findById(travelPackageId);
	}
	
	@PutMapping
	public TravelPackage updateByTravelPackageId(@PathVariable int travelPackageId, 
			@RequestBody TravelPackage travelPackage) {
		return travelPackageService.updateById(travelPackage, travelPackageId);
	}
	
	@DeleteMapping
	public void deleteByTravelPackageId(@PathVariable int travelPackageId) {
		travelPackageService.deleteById(travelPackageId);
	}
}
