package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

public class CustomerService {
	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	@Transactional(readOnly=true)
	public List<Customer> findAll() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Customer findById(int id) {
		return customerRepository.findById(id).get();
	}
	
	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	@Transactional
	public List<Customer> saveAll(List<Customer> customerList) {
		return (List<Customer>) customerRepository.saveAll(customerList);
	}
	
	@Transactional
	public List<Customer> update(List<Customer> customersList) {
		List<Customer> tempCustomersList = new ArrayList<Customer>();
		for(Customer customer : customersList) {
			tempCustomersList.add(updateById(customer, customer.getCustomerId()));
		}
		return tempCustomersList;
	}
	
	@Transactional
	public Customer updateById(Customer newDetails, int id) {
		Customer customer = findById(id);
		if(newDetails.getFirstName() != null) {
			customer.setFirstName(newDetails.getFirstName());
		}
		if(newDetails.getLastName() != null) {
			customer.setLastName(newDetails.getLastName());
		}
		return save(customer);
	}
	
	@Transactional
	public void deleteMultiple(List<Integer> customerIdList) {
		for(Integer id : customerIdList) {
			deleteById(id);
		}
	}
	
	@Transactional
	public void deleteById(int id) {
		customerRepository.deleteById(id);
	}
}
