package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TravelPackage;
import com.example.demo.service.TravelPackageService;

@RestController
@RequestMapping("/travel-packages")
public class TravelPackagesController {
	private TravelPackageService travelPackageService;

	public TravelPackagesController(TravelPackageService travelPackageService) {
		super();
		this.travelPackageService = travelPackageService;
	}
	
	@GetMapping
	public Iterable<TravelPackage> retrieveAllTravelPackages() {
		return travelPackageService.findAll();
	}
	
	@PutMapping
	public Iterable<TravelPackage> updateMultipleTravelPackages(@RequestBody List<TravelPackage> travelPackageList) {
		return travelPackageService.updateMultiple(travelPackageList);
	}
	
	@PostMapping
	public List<TravelPackage> saveTravelPackages(@RequestBody List<TravelPackage> travelPackageList) {
		return travelPackageService.saveAll(travelPackageList);
	}
	
	@DeleteMapping
	public void deleteMultipleTravelPackages(@RequestParam("travelPackageId") List<Integer> travelPackageIdList) {
		travelPackageService.deleteMultiple(travelPackageIdList);
	}
}
