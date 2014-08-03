package com.tja.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tja.domain.Customer;
import com.tja.repository.CustomerRepository;

import exception.BusinessException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerRepository repository;
	
	public Customer createCustomer(Customer customer) {
		Customer c = repository.save(customer);
		return c;
	}

	public Customer getCustomerById(Long id) {
		Customer c = repository.findOne(id);  //(customer);
		return c;
	}

	public List<Customer> getAllCustomer() {
		List<Customer> cs = repository.findAll();
		return cs;
	}

	public void deleteCustomer(Long id) {
		repository.delete(id);
	}

	/**
	 * 此方法不灵活，尽量不用，在此处仅仅是测试
	 */
	public Customer updateCustomer(Customer customer) throws BusinessException {
		Customer c = repository.findOne(customer.getId());
		if(null == c) {
			throw new BusinessException("the Customer [id="+customer.getId()+"] is not exist!");
		}
		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		return c;
	}

}
