package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@JsonIdentityInfo(generator=PropertyGenerator.class, property="reservationId")
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reservationId;
	@OneToMany(mappedBy="reservation")
	private List<Service> availedServiceList;
	private String departureDate;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@OneToMany(mappedBy="reservation")
	private List<Feedback> feedbacks;
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public List<Service> getAvailedServiceList() {
		return availedServiceList;
	}
	public void setAvailedServiceList(List<Service> availedServiceList) {
		this.availedServiceList = availedServiceList;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
}
