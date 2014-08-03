package com.tja.service;

import java.util.List;

import com.tja.domain.Customer;

import exception.BusinessException;

public interface CustomerService {

	Customer createCustomer(Customer customer);
	
	Customer getCustomerById(Long id);
	
	List<Customer> getAllCustomer();
	
	void deleteCustomer(Long id);
	
	Customer updateCustomer(Customer customer) throws BusinessException;
}
