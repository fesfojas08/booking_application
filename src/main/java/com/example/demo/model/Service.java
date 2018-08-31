package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@JsonIdentityInfo(generator=PropertyGenerator.class, property="serviceId")
public class Service {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int serviceId;
	private String serviceName;
	private String description;
	
	@OneToMany(mappedBy="service", cascade=CascadeType.ALL)
	private List<Image> images;
	
	@ManyToOne
	@JoinColumn(name="reservation_id")
	@JsonIgnore
	private Reservation reservation;
	
	@OneToMany(mappedBy="service")
	@JsonIgnore
	private List<ServiceFee> serviceFees;
	
	@ManyToOne
	@JoinColumn(name="travel_package_id")
	@JsonIgnore
	private TravelPackage travelPackage;
	
	
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public List<ServiceFee> getServiceFees() {
		return serviceFees;
	}
	public void setServiceFees(List<ServiceFee> serviceFees) {
		this.serviceFees = serviceFees;
	}
	public TravelPackage getTravelPackage() {
		return travelPackage;
	}
	public void setTravelPackage(TravelPackage travelPackage) {
		this.travelPackage = travelPackage;
	}

	@PrePersist
	public void saveRelationships() {
		createRelationships();
	}
	
	@PreUpdate
	public void updateRelationships() {
		createRelationships();
	}
	
	private void createRelationships() {
		if(this.images != null) {
			for(Image image : this.images) {
				image.setService(this);
			}
		}
	}
}
