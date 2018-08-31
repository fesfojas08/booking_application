package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@JsonIdentityInfo(generator=PropertyGenerator.class, property="serviceFeeId")
public class ServiceFee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int serviceFeeId;
	private double amount;
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;
	private String startDate;
	
	public int getServiceFeeId() {
		return serviceFeeId;
	}
	public void setServiceFeeId(int serviceFeeId) {
		this.serviceFeeId = serviceFeeId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
