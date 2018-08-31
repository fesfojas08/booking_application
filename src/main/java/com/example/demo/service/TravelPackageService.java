package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Image;
import com.example.demo.model.Service;
import com.example.demo.model.TravelPackage;
import com.example.demo.repository.TravelPackageRepository;

public class TravelPackageService {
	private TravelPackageRepository travelPackageRepository;
	private TravelService travelService;
	private ImageService imageService;

	public TravelPackageService(TravelPackageRepository travelPackageRepository,
			TravelService travelService, ImageService imageService) {
		super();
		this.travelPackageRepository = travelPackageRepository;
		this.travelService = travelService;
		this.imageService = imageService;
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
		saveTravelPackageService(travelPackage);
		saveTravelPackageImage(travelPackage);
		return travelPackageRepository.save(travelPackage);
	}
	
	@Transactional
	public List<TravelPackage> saveAll(List<TravelPackage> travelPackageList) {
		for(TravelPackage travelPackage : travelPackageList) {
			saveTravelPackageService(travelPackage);
			saveTravelPackageImage(travelPackage);
		}
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
		travelPackage.setAvailableServiceList(
				travelService.updateMultiple(newDetails.getAvailableServiceList()));
		travelPackage.setImages(
				imageService.updateMultiple(newDetails.getImages())
		);
		if(newDetails.getPackageName() != null) {
			travelPackage.setPackageName(newDetails.getPackageName());
		}
		if(newDetails.getDescription() != null) {
			travelPackage.setDescription(newDetails.getDescription());
		}
		return save(travelPackage);
	}
	
	@Transactional
	public void deleteById(int id) {
		TravelPackage travelPackage = findById(id);
		// delete images referenced by this ID
		travelService.deleteMultiple(travelPackage.getAvailableServiceList());
		imageService.deleteMultiple(travelPackage.getImages());
		travelPackageRepository.deleteById(id);
	}
	
	
	@Transactional
	public void deleteMultiple(List<Integer> travelPackageIdList) {
		for(Integer id : travelPackageIdList) {
			deleteById(id);
		}
	}
	
	@Transactional
	public void saveTravelPackageService(TravelPackage travelPackage) {
		if(travelPackage.getAvailableServiceList() != null) {
			for(Service service : travelPackage.getAvailableServiceList()) {
				service.setTravelPackage(travelPackage);
				travelService.save(service);
			}
		}
	}
	
	@Transactional
	public void saveTravelPackageImage(TravelPackage travelPackage) {
		if(travelPackage.getImages() != null) {
			for(Image image : travelPackage.getImages()) {
				image.setTravelPackage(travelPackage);
				imageService.save(image);
			}
		}
	}
}
