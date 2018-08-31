package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/customers/{customerId}")
public class CustomerController {
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping
	public Customer retrieveByCustomerId(@PathVariable("customerId") int customerId) {
		return customerService.findById(customerId);
	}
	
	@PutMapping
	public Customer updateByCustomerId(@PathVariable("customerId") int customerId, 
			@RequestBody Customer customer) {
		return customerService.updateById(customer, customerId);
	}
	
	@DeleteMapping
	public void deleteByCustomerId(@PathVariable("customerId") int customerId) {
		customerService.deleteById(customerId);
	}
}
