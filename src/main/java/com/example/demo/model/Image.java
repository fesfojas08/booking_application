package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@JsonIdentityInfo(generator=PropertyGenerator.class, property="imageId")
public class Image {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int imageId;
	private String description;
	private String imageUrl;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="service_id")
	private Service service;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="travel_package_id")
	private TravelPackage travelPackage;
	
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public TravelPackage getTravelPackage() {
		return travelPackage;
	}
	public void setTravelPackage(TravelPackage travelPackage) {
		this.travelPackage = travelPackage;
	}
}
