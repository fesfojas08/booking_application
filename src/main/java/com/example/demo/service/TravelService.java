package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Service;
import com.example.demo.repository.ServiceRepository;

public class TravelService {
	private ServiceRepository serviceRepository;

	public TravelService(ServiceRepository serviceRepository) {
		super();
		this.serviceRepository = serviceRepository;
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
		return serviceRepository.save(service);
	}
	
	@Transactional
	public List<Service> saveMultiple(List<Service> serviceList) {
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
		serviceRepository.deleteById(id);
	}
	
	@Transactional
	public Service updateById(Service newDetails, int id) {
		Service travelService = findById(id);
		if(newDetails.getServiceName() != null) {
			travelService.setServiceName(newDetails.getServiceName());
		}
		if(newDetails.getImages() != null) {
			travelService.setImages(travelService.getImages());
		}
		if(newDetails.getDescription() != null) {
			travelService.setDescription(newDetails.getDescription());
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
