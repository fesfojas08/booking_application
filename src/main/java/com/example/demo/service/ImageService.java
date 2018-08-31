package com.example.demo.service;

import java.util.ArrayList;
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
	public void deleteMultiple(List<Image> imageList) {
		for(Image image : imageList) {
			deleteById(image.getImageId());
		}
	}
	
	@Transactional
	public void deleteById(int id) {
		imageRepository.deleteById(id);
	}
	
	@Transactional
	public Image updateById(Image newDetails, int id) {
		Image image = null;
		if(imageRepository.existsById(id)) {
			image = findById(id);
			if(newDetails.getImageUrl() != null) {
				image.setImageUrl(newDetails.getImageUrl());
			}
			if(newDetails.getDescription() != null) {
				image.setDescription(newDetails.getDescription());
			}
		}
		return save(image);
	}
	
	@Transactional
	public List<Image> updateMultiple(List<Image> imageList) {
		List<Image> tempImageList = new ArrayList<Image>();
		for(Image image : imageList) {
			tempImageList.add(updateById(image, image.getImageId()));
		}
		return tempImageList;
	}
}
