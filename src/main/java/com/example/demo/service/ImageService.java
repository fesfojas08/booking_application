package com.example.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;

public class ImageService {
	private ImageRepository imageRepository;

	public ImageService(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}
	
	@Transactional(readOnly=true)
	public List<Image> findAll() {
		return (List<Image>) imageRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Image findById(int id) {
		return imageRepository.findById(id).get();
	}
	
	@Transactional
	public Image save(Image image) {
		return imageRepository.save(image);
	}
	
	@Transactional
	public List<Image> saveMultiple(List<Image> imageList) {
		return (List<Image>) imageRepository.saveAll(imageList);
	}
	
	@Transactional
	public void deleteMultiple(List<Integer> imageIdList) {
		for(Integer imageId : imageIdList) {
			deleteById(imageId);
		}
	}
	
	@Transactional
	public void deleteById(int id) {
		imageRepository.deleteById(id);
	}
}
