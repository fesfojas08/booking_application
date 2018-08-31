package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomersController {
	private CustomerService customerService;

	public CustomersController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping
	public Iterable<Customer> retrieveAllCustomers() {
		return customerService.findAll();
	}
	
	@PutMapping
	public Iterable<Customer> updateMultipleCustomers(@RequestBody List<Customer> customersList) {
		return customerService.updateMultiple(customersList);
	}
	
	@PostMapping
	public List<Customer> saveCustomers(@RequestBody List<Customer> customerList) {
		return customerService.saveAll(customerList);
	}
	
	@DeleteMapping
	public void deleteMultipleCustomers(@RequestParam("customerId") List<Integer> customerIdList) {
		customerService.deleteMultiple(customerIdList);
	}
}
