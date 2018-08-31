package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Service;
import com.example.demo.model.TravelPackage;
import com.example.demo.repository.TravelPackageRepository;

public class TravelPackageService {
	private TravelPackageRepository travelPackageRepository;

	public TravelPackageService(TravelPackageRepository travelPackageRepository) {
		super();
		this.travelPackageRepository = travelPackageRepository;
	}
	
	@Transactional(readOnly=true)
	public List<TravelPackage> findAll() {
		return (List<TravelPackage>) travelPackageRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public TravelPackage findById(int id) {
		return travelPackageRepository.findById(id).get();
	}
	
	@Transactional
	public TravelPackage save(TravelPackage travelPackage) {
		return travelPackageRepository.save(travelPackage);
	}
	
	@Transactional
	public List<TravelPackage> saveAll(List<TravelPackage> travelPackageList) {
		return (List<TravelPackage>) travelPackageRepository.saveAll(travelPackageList);
	}
	
	@Transactional
	public List<TravelPackage> updateMultiple(List<TravelPackage> travelPackageList) {
		List<TravelPackage> tempTravelPackageList = new ArrayList<TravelPackage>();
		for(TravelPackage travelPackage : travelPackageList) {
			tempTravelPackageList.add(updateById(travelPackage, travelPackage.getTravelPackageId()));
		}
		return tempTravelPackageList;
	}
	
	@Transactional
	public TravelPackage updateById(TravelPackage newDetails, int id) {
		TravelPackage travelPackage = findById(id);
		if(newDetails.getPackageName() != null) {
			travelPackage.setPackageName(newDetails.getPackageName());
		}
		if(newDetails.getAvailableServiceList() != null) {
			travelPackage.setAvailableServiceList(newDetails.getAvailableServiceList());
		}
		if(newDetails.getImages() != null) {
			travelPackage.setImages(newDetails.getImages());
		}
		if(newDetails.getDescription() != null) {
			travelPackage.setDescription(newDetails.getDescription());
		}
		return save(travelPackage);
	}
	
	@Transactional
	public void deleteById(int id) {
		travelPackageRepository.deleteById(id);
	}
	
	
	@Transactional
	public void deleteMultiple(List<Integer> travelPackageIdList) {
		for(Integer id : travelPackageIdList) {
			deleteById(id);
		}
	}
	
	@Transactional
	public List<Integer> findAllServiceId(int travelPackageId) {
		List<Integer> serviceIdList = new ArrayList<Integer>();
		TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId).get();
		for(Service service : travelPackage.getAvailableServiceList()) {
			serviceIdList.add(service.getServiceId());
		}
		return serviceIdList;
	}
}
