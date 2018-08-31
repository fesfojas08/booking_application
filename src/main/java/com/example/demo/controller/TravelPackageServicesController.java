package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Service;
import com.example.demo.model.TravelPackage;
import com.example.demo.service.TravelService;

@RestController
@RequestMapping("/travel-packages/{travelPackageId}/services")
public class TravelPackageServicesController {
	
	private TravelService travelService;

	public TravelPackageServicesController(TravelService travelService) {
		super();
		this.travelService = travelService;
	}
	
	@GetMapping
	public List<Service> retrieveAllServices(@PathVariable int travelPackageId) {
		return travelService.findAllByTravelPackageId(travelPackageId);
	}
	
//	@PutMapping
//	public List<TravelPackage> updateMultipleTravelPackages(@PathVariable int travelPackageId,
//			@RequestBody List<TravelPackage> travelPackageList) {
//		return travelPackageService.updateMultiple(travelPackageList);
//	}
	
	@PostMapping
	public List<Service> saveTravelPackages(@PathVariable int travelPackageId) {
		return travelService.saveAllByTravelPackageId(travelPackageId);
	}
	
	@DeleteMapping
	public void deleteMultipleTravelPackages(
			@RequestParam("serviceId") List<Integer> serviceIdList, 
			@PathVariable int travelPackageId) {
		travelService.deleteMultipleByTravelPackageId(serviceIdList, travelPackageId);
	}
}
