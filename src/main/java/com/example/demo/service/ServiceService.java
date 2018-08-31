package com.example.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Image;
import com.example.demo.model.Service;
import com.example.demo.repository.ServiceRepository;

public class ServiceService {
	private ServiceRepository serviceRepository;
	private ImageService imageService;

	public ServiceService(ServiceRepository serviceRepository, ImageService imageService) {
		super();
		this.serviceRepository = serviceRepository;
		this.imageService = imageService;
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
	public void deleteMultiple(List<Integer> serviceIdList) {
		imageService.deleteMultiple(serviceIdList);
		for(Integer serviceId : serviceIdList) {
			deleteById(serviceId);
		}
	}
	
	@Transactional
	public void deleteById(int id) {
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
}
