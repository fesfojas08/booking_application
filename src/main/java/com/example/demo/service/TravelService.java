package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Image;
import com.example.demo.model.Service;
import com.example.demo.repository.ServiceRepository;

public class TravelService {
	private ServiceRepository serviceRepository;
	private ImageService imageService;

	public TravelService(ServiceRepository serviceRepository, ImageService imageService) {
		super();
		this.serviceRepository = serviceRepository;
		this.imageService = imageService;
	}
	
	@Transactional(readOnly=true)
	public List<Service> findAll() {
		return (List<Service>) serviceRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Service findById(int id) {
		return serviceRepository.findById(id).get();
	}
	
	@Transactional
	public Service save(Service service) {
		saveServiceImage(service);
		return serviceRepository.save(service);
	}
	
	@Transactional
	public List<Service> saveMultiple(List<Service> serviceList) {
		for(Service service : serviceList) {
			saveServiceImage(service);
		}
		return (List<Service>) serviceRepository.saveAll(serviceList);
	}
	
	@Transactional
	public void deleteMultiple(List<Service> serviceList) {
		for(Service service : serviceList) {
			deleteById(service.getServiceId());
		}
	}
	
	@Transactional
	public void deleteById(int id) {
		Service service = findById(id);
		// delete images referenced by this ID
		imageService.deleteMultiple(service.getImages());
		serviceRepository.deleteById(id);
	}
	
	@Transactional
	public void saveServiceImage(Service service) {
		if(service.getImages() != null) {
			for(Image image : service.getImages()) {
				image.setService(service);
				imageService.save(image);
			}
		}
	}
	
	@Transactional
	public Service updateById(Service newDetails, int id) {
		Service travelService = null;
		if(serviceRepository.existsById(id)) {
			travelService = findById(id);
			imageService.updateMultiple(newDetails.getImages());
			if(newDetails.getServiceName() != null) {
				travelService.setServiceName(newDetails.getServiceName());
			}
			if(newDetails.getDescription() != null) {
				travelService.setDescription(newDetails.getDescription());
			}
		}
		return save(travelService);
	}
	
	@Transactional
	public List<Service> updateMultiple(List<Service> travelServiceList) {
		List<Service> tempTravelServiceList = new ArrayList<Service>();
		for(Service service : travelServiceList) {
			tempTravelServiceList.add(updateById(service, service.getServiceId()));
		}
		return tempTravelServiceList;
	}
}
