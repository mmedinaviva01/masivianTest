package com.masivian.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masivian.springboot.entity.Customer;

@Service
public interface ICustomerService {
	public Customer save(Customer customer);

	public List<Customer> findAll();

	public Customer findById(long id);

	public String delete(long id);
}
