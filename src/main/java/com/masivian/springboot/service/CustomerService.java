package com.masivian.springboot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masivian.springboot.entity.Customer;
import com.masivian.springboot.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService implements ICustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer save(Customer customer) {
		String id = UUID.randomUUID().toString().replace("-", "");
		customer.setId(id);
		return customerRepository.save(customer);
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
