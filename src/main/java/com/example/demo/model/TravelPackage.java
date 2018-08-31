package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@JsonIdentityInfo(generator=PropertyGenerator.class, property="travelPackageId")
public class TravelPackage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int travelPackageId;
	private String packageName;
	
	@OneToMany(mappedBy="travelPackage", cascade=CascadeType.ALL)
	private List<Service> availableServiceList;
	
	@OneToMany(mappedBy="travelPackage", cascade=CascadeType.ALL)
	private List<Image> images;
	private String description;
	
	public int getTravelPackageId() {
		return travelPackageId;
	}
	public void setTravelPackageId(int travelPackageId) {
		this.travelPackageId = travelPackageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public List<Service> getAvailableServiceList() {
		return availableServiceList;
	}
	public void setAvailableServiceList(List<Service> availableServiceList) {
		this.availableServiceList = availableServiceList;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		if(this.availableServiceList != null) {
			for(Service service : this.availableServiceList) {
				service.setTravelPackage(this);
			}
		}
		if(this.images != null) {
			for(Image image : this.images) {
				image.setTravelPackage(this);
			}
		}
	}
}
